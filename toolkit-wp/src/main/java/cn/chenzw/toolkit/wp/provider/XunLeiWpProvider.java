package cn.chenzw.toolkit.wp.provider;

import cn.chenzw.toolkit.core.util.JSONKit;
import cn.chenzw.toolkit.wp.AbstractWpProvider;
import cn.chenzw.toolkit.wp.entity.WpShareInfo;
import cn.chenzw.toolkit.wp.enums.Wp;
import cn.chenzw.toolkit.wp.raw.response.XunLeiCaptchaTokenResponse;
import cn.chenzw.toolkit.wp.raw.response.XunLeiShareInfoResponse;
import cn.chenzw.toolkit.wp.raw.response.exp.XunLeiShareExpResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.classic.methods.HttpPost;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.ParseException;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.apache.hc.core5.http.io.entity.StringEntity;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;
import java.util.regex.Pattern;

/**
 * @author chenzw
 */
@Slf4j
public class XunLeiWpProvider extends AbstractWpProvider {

    private static final Pattern SHARE_URL_PATTERN = Pattern.compile("https://pan\\.xunlei\\.com/s/([A-Za-z0-9-]+)(?:\\?pwd=([A-Za-z0-9]*)#?)?", Pattern.MULTILINE);

    private static final String X_CLIENT_ID = "Xqp0kJBXWhwaTpB6";

    private static final String X_DEVICE_ID = "925b7631473a13716b791d7f28289cad";

    @Override
    public WpShareInfo fetchShareInfo(String shareUrl, String code) throws Exception {
        String shareId = this.extractShareId(shareUrl);
        if (StringUtils.isEmpty(code)) {
            code = this.extractPassCodeFromShareUrl(shareUrl);
        }
        HttpGet httpGet = new HttpGet("https://api-pan.xunlei.com/drive/v1/share?share_id=" + shareId + "&pass_code=" + code + "&limit=100");
        httpGet.setHeader("Accept", "application/json;charset=UTF-8");
        httpGet.setHeader("x-client-id", X_CLIENT_ID);
        httpGet.setHeader("x-device-id", X_DEVICE_ID);
        this.setTokenHeader(httpGet);
        try (CloseableHttpClient httpclient = HttpClients.createDefault()) {
            try (CloseableHttpResponse response = httpclient.execute(httpGet)) {
                String content = EntityUtils.toString(
                        response.getEntity()
                );

                log.debug("XunLeiWp[{}] => {} - {}", shareUrl, response.getCode(), content);

                if (response.getCode() == 200) {
                    XunLeiShareInfoResponse shareInfoResponse = JSONKit.readValue(content, XunLeiShareInfoResponse.class);
                    return WpShareInfo.builder()
                            .shareId(shareId)
                            .shareTitle(shareInfoResponse.getTitle())
                            .creatorId(shareInfoResponse.getUser_info().getUser_id())
                            .creatorAvatar(shareInfoResponse.getUser_info().getAvatar())
                            .creatorName(shareInfoResponse.getUser_info().getNickname())
                            .expiration(
                                    Objects.equals(shareInfoResponse.getExpiration_at(), "-1") ? null : LocalDateTime.parse(shareInfoResponse.getExpiration_at(), DateTimeFormatter.ISO_ZONED_DATE_TIME)
                            )
                            .needPassCode(true)
                            .passCode(code)
                            .build();
                }
                XunLeiShareExpResponse shareExpResponse = JSONKit.readValue(content, XunLeiShareExpResponse.class);
                return WpShareInfo.builder()
                        .valid(false)
                        .errMsg(shareExpResponse.getError_description())
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
        return wp == Wp.XUN_LEI;
    }

    private void setTokenHeader(HttpGet httpGet) throws IOException, ParseException {
        HttpPost httpPost = new HttpPost("https://xluser-ssl.xunlei.com/v1/shield/captcha/init");
        httpPost.setEntity(new StringEntity("{\"client_id\":\"" + X_CLIENT_ID + "\",\"device_id\":\"" + X_DEVICE_ID + "\",\"action\":\"get:/drive/v1/share\",\"meta\":{\"package_name\":\"pan.xunlei.com\",\"client_version\":\"1.45.0\",\"captcha_sign\":\"1.fe2108ad808a74c9ac0243309242726c\",\"timestamp\":\"1645241033384\"}}"));
        try (CloseableHttpClient httpclient = HttpClients.createDefault()) {
            try (CloseableHttpResponse response = httpclient.execute(httpPost)) {
                String content = EntityUtils.toString(response.getEntity());
                XunLeiCaptchaTokenResponse captchaTokenResponse = JSONKit.readValue(
                        content, XunLeiCaptchaTokenResponse.class
                );
                httpGet.setHeader("x-captcha-token", captchaTokenResponse.getCaptcha_token());
            }
        }
    }
}
