package cn.chenzw.toolkit.wp.provider;

import cn.chenzw.toolkit.core.util.JSONKit;
import cn.chenzw.toolkit.wp.AbstractWpProvider;
import cn.chenzw.toolkit.wp.entity.WpShareInfo;
import cn.chenzw.toolkit.wp.enums.Wp;
import cn.chenzw.toolkit.wp.raw.response.QuarkShareInfoResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.hc.client5.http.classic.methods.HttpPost;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.apache.hc.core5.http.io.entity.StringEntity;

import java.time.LocalDateTime;
import java.util.regex.Pattern;

/**
 * @author chenzw
 */
@Slf4j
public class QuarkWpProvider extends AbstractWpProvider {

    private static Pattern SHARE_URL_PATTERN = Pattern.compile("https://pan\\.quark\\.cn/s/([A-Za-z0-9]+)", Pattern.MULTILINE);

    @Override
    public WpShareInfo fetchShareInfo(String shareUrl, String code) throws Exception {
        String shareId = this.extractShareId(shareUrl);
        if (StringUtils.isEmpty(shareId)) {
            return WpShareInfo.builder()
                    .valid(false)
                    .errMsg("shareId is null!")
                    .build();
        }
        HttpPost httpPost = new HttpPost("https://drive-pc.quark.cn/1/clouddrive/share/sharepage/token");
        httpPost.setHeader("Content-Type", "application/json");
        httpPost.setEntity(new StringEntity("{\"pwd_id\":\"" + shareId + "\",\"passcode\":\"" + code + "\"}"));
        try (CloseableHttpClient httpclient = HttpClients.createDefault()) {
            try (CloseableHttpResponse response = httpclient.execute(httpPost)) {
                String content = EntityUtils.toString(
                        response.getEntity()
                );

                log.debug("QuarkWp[{}] => {} - {}", shareUrl, response.getCode(), content);

                QuarkShareInfoResponse shareInfoResponse = JSONKit.readValue(
                        content, QuarkShareInfoResponse.class
                );
                if (shareInfoResponse.getCode() != 0) {
                    // 41006 - 分享不存在
                    // 41007 - 需要分享码
                    return WpShareInfo.builder()
                            .valid(false)
                            .errMsg(shareInfoResponse.getMessage())
                            .build();
                }

                return WpShareInfo.builder()
                        .shareId(shareId)
                        .shareTitle(shareInfoResponse.getData().getTitle())
                        .needPassCode(!StringUtils.isEmpty(code))
                        .passCode(code)
                        .creatorName(shareInfoResponse.getAuthor().getNick_name())
                        .creatorAvatar(shareInfoResponse.getAuthor().getAvatar_url())
                        .expiration(
                                this.buildExpiration(
                                        shareInfoResponse.getData().getExpired_type(),
                                        shareInfoResponse.getData().getExpired_days()
                                )
                        )
                        .build();

            }
        }
    }


    @Override
    public Pattern getShareUrlPattern() {
        return SHARE_URL_PATTERN;
    }

    @Override
    public String extractPassCodeFromShareUrl(String shareUrl) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Wp getType() {
        return Wp.QUARK;
    }

    private LocalDateTime buildExpiration(Integer expiredType, Long expiredDays) {
        if (expiredType == 3) {
            LocalDateTime now = LocalDateTime.now();
            now.plusDays(expiredDays);
            return now;
        }
        return null;
    }
}
