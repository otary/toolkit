package cn.chenzw.toolkit.wp.provider;

import cn.chenzw.toolkit.core.util.JSONKit;
import cn.chenzw.toolkit.wp.AbstractWpProvider;
import cn.chenzw.toolkit.wp.WpProvider;
import cn.chenzw.toolkit.wp.entity.WpShareInfo;
import cn.chenzw.toolkit.wp.raw.response.Cloud189ShareInfoResponse;
import cn.chenzw.toolkit.wp.raw.response.exp.Cloud189ShareExpResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.HttpEntity;
import org.apache.hc.core5.http.io.entity.EntityUtils;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author chenzw
 */
@Slf4j
public class Cloud189WpProvider extends AbstractWpProvider {

    private static Pattern SHARE_URL_PATTERN = Pattern.compile("https://cloud\\.189\\.cn/web/share\\?code=([A-Za-z0-9]+)$", Pattern.MULTILINE);

    @Override
    public boolean shareUrlMatches(String shareUrl) {
        return SHARE_URL_PATTERN.matcher(shareUrl.trim())
                .matches();
    }

    @Override
    public List<String> extractShareUrls(String content) {
        List<String> shareUrls = new ArrayList<>();
        Matcher matcher = SHARE_URL_PATTERN.matcher(content);
        while (matcher.find()) {
            shareUrls.add(matcher.group());
        }
        return shareUrls;
    }

    @Override
    public WpShareInfo fetchShareInfo(String shareUrl, String code) throws Exception {
        String shareId = this.extractShareId(shareUrl);
        HttpGet httpGet = new HttpGet("https://cloud.189.cn/api/open/share/getShareInfoByCodeV2.action?shareCode=" + shareId);
        httpGet.setHeader("Accept", "application/json;charset=UTF-8");
        try (CloseableHttpClient httpclient = HttpClients.createDefault()) {
            try (CloseableHttpResponse response = httpclient.execute(httpGet)) {
                HttpEntity entity = response.getEntity();
                String content = EntityUtils.toString(entity);

                log.debug("Cloud189Wp[{}] => {} - {}", shareUrl, response.getCode(), content);

                if (response.getCode() == 200) {
                    Cloud189ShareInfoResponse shareInfoResponse = JSONKit.readValue(content, Cloud189ShareInfoResponse.class);

                    return WpShareInfo.builder()
                            .shareId(shareId)
                            .shareTitle(shareInfoResponse.getFileName())
                            .creatorId(shareInfoResponse.getCreator().getOwnerAccount())
                            .creatorAvatar(shareInfoResponse.getCreator().getIconURL())
                            .creatorName(shareInfoResponse.getCreator().getNickName())
                            .expiration(
                                    this.buildExpiration(shareInfoResponse.getExpireType(), shareInfoResponse.getExpireTime())
                            )
                            .needPassCode(shareInfoResponse.getShareMode() == 1)
                            .passCode(code)
                            .lastUpdateTime(new Date(shareInfoResponse.getShareDate()))
                            .build();
                } else {
                    Cloud189ShareExpResponse shareExpResponse = JSONKit.readValue(content, Cloud189ShareExpResponse.class);
                    return WpShareInfo.builder()
                            .valid(false)
                            .errMsg(shareExpResponse.getRes_message())
                            .build();
                }
            }
        }
    }

    @Override
    public String extractShareId(String shareUrl) {
        Matcher matcher = SHARE_URL_PATTERN.matcher(shareUrl);
        if (matcher.find()) {
            return matcher.group(1);
        }
        return "";
    }

    @Override
    public Pattern getShareUrlPattern() {
        return SHARE_URL_PATTERN;
    }

    private LocalDateTime buildExpiration(Integer expireType, Integer expireTime) {
        if (expireType == 1) {
            LocalDateTime now = LocalDateTime.now();
            now.plusDays(expireTime);
            return now;
        }
        return null;
    }
}
