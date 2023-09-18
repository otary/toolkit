package cn.chenzw.toolkit.wp.provider;

import cn.chenzw.toolkit.core.util.JSONKit;
import cn.chenzw.toolkit.wp.AbstractWpProvider;
import cn.chenzw.toolkit.wp.entity.WpShareInfo;
import cn.chenzw.toolkit.wp.enums.Wp;
import cn.chenzw.toolkit.wp.raw.response.Cloud189ShareInfoResponse;
import cn.chenzw.toolkit.wp.raw.response.exp.Cloud189ShareExpResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.io.entity.EntityUtils;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.regex.Pattern;

/**
 * @author chenzw
 */
@Slf4j
public class Cloud189WpProvider extends AbstractWpProvider {

    private static Pattern SHARE_URL_PATTERN = Pattern.compile("https://cloud\\.189\\.cn/web/share\\?code=([A-Za-z0-9]+)$", Pattern.MULTILINE);

    @Override
    public WpShareInfo fetchShareInfo(String shareUrl, String code) throws Exception {
        String shareId = this.extractShareId(shareUrl);
        if (StringUtils.isEmpty(shareId)) {
            return WpShareInfo.builder()
                    .valid(false)
                    .errMsg("shareId is null!")
                    .build();
        }
        HttpGet httpGet = new HttpGet("https://cloud.189.cn/api/open/share/getShareInfoByCodeV2.action?shareCode=" + shareId);
        httpGet.setHeader("Accept", "application/json;charset=UTF-8");
        try (CloseableHttpClient httpclient = HttpClients.createDefault()) {
            try (CloseableHttpResponse response = httpclient.execute(httpGet)) {
                String content = EntityUtils.toString(
                        response.getEntity()
                );

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
                }
                // 异常处理
                Cloud189ShareExpResponse shareExpResponse = JSONKit.readValue(content, Cloud189ShareExpResponse.class);
                return WpShareInfo.builder()
                        .valid(false)
                        .errMsg(shareExpResponse.getRes_message())
                        .build();
            }
        }
    }

    @Override
    public Pattern getShareUrlPattern() {
        return SHARE_URL_PATTERN;
    }

    @Override
    public boolean support(Wp wp) {
        return wp == Wp.CLOUD_189;
    }

    @Override
    public String extractPassCodeFromShareUrl(String shareUrl) {
        // 不支持
        throw new UnsupportedOperationException();
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
