package cn.chenzw.toolkit.wp.provider;

import cn.chenzw.toolkit.core.util.JSONKit;
import cn.chenzw.toolkit.wp.AbstractWpProvider;
import cn.chenzw.toolkit.wp.WpProvider;
import cn.chenzw.toolkit.wp.entity.WpShareInfo;
import cn.chenzw.toolkit.wp.raw.response.AliYunShareInfoResponse;
import cn.chenzw.toolkit.wp.raw.response.exp.AliYunShareExpResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.hc.client5.http.classic.methods.HttpPost;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.HttpEntity;
import org.apache.hc.core5.http.ParseException;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.apache.hc.core5.http.io.entity.StringEntity;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author chenzw
 */
@Slf4j
public class AliYunWpProvider extends AbstractWpProvider {

    private static Pattern SHARE_URL_PATTERN = Pattern.compile("https://www\\.aliyundrive\\.com/s/([A-Za-z0-9]+)$", Pattern.MULTILINE);

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
        HttpPost httpPost = new HttpPost("https://api.aliyundrive.com/adrive/v3/share_link/get_share_by_anonymous?share_id=" + shareId);
        httpPost.setHeader("Content-Type", "application/json");
        httpPost.setEntity(new StringEntity("{\"share_id\": \"" + shareId + "\"}"));
        try (CloseableHttpClient httpclient = HttpClients.createDefault()) {
            try (CloseableHttpResponse response = httpclient.execute(httpPost)) {
                HttpEntity entity = response.getEntity();
                String content = EntityUtils.toString(entity);

                log.debug("AliYunWp[{}] => {} - {}", shareUrl, response.getCode(), content);

                if (response.getCode() == 200) {
                    AliYunShareInfoResponse shareInfoResponse = JSONKit.readValue(content, AliYunShareInfoResponse.class);

                    return WpShareInfo.builder()
                            .shareId(shareId)
                            .shareTitle(shareInfoResponse.getShare_title())
                            .fileCount(shareInfoResponse.getFile_count())
                            .creatorId(shareInfoResponse.getCreator_id())
                            .creatorAvatar(shareInfoResponse.getAvatar())
                            .creatorPhone(shareInfoResponse.getCreator_phone())
                            .creatorName(shareInfoResponse.getCreator_name())
                            .expiration(StringUtils.isEmpty(shareInfoResponse.getExpiration()) ? null : LocalDateTime.parse(shareInfoResponse.getExpiration(), DateTimeFormatter.ISO_DATE_TIME))
                            .needPassCode(shareInfoResponse.getHas_pwd())
                            .passCode(code)
                            .lastUpdateTime(shareInfoResponse.getUpdated_at())
                            .build();

                } else if (response.getCode() == 400) {
                    AliYunShareExpResponse shareExpResponse = JSONKit.readValue(content, AliYunShareExpResponse.class);
                    return WpShareInfo.builder()
                            .shareId(shareId)
                            .valid(false)
                            .errMsg(shareExpResponse.getMessage())
                            .build();
                }
            }
        }
        return null;
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
}
