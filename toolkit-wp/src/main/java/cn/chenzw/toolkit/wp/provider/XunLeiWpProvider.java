package cn.chenzw.toolkit.wp.provider;

import cn.chenzw.toolkit.core.util.JSONKit;
import cn.chenzw.toolkit.wp.AbstractWpProvider;
import cn.chenzw.toolkit.wp.WpProvider;
import cn.chenzw.toolkit.wp.entity.WpShareInfo;
import cn.chenzw.toolkit.wp.raw.response.Cloud189ShareInfoResponse;
import cn.chenzw.toolkit.wp.raw.response.XunLeiShareInfoResponse;
import cn.chenzw.toolkit.wp.raw.response.exp.Cloud189ShareExpResponse;
import cn.chenzw.toolkit.wp.raw.response.exp.XunLeiShareExpResponse;
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
public class XunLeiWpProvider extends AbstractWpProvider {

    private static Pattern SHARE_URL_PATTERN = Pattern.compile("https://pan\\.xunlei\\.com/s/([A-Za-z0-9]+)", Pattern.MULTILINE);

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
        HttpGet httpGet = new HttpGet("https://api-pan.xunlei.com/drive/v1/share?share_id=" + shareId + "&pass_code=" + code + "&limit=100");
        httpGet.setHeader("Accept", "application/json;charset=UTF-8");
        httpGet.setHeader("x-captcha-token", "ck0.SZgVf5BVETXukUfudbcbCWuPetWlk1PP5vd9ivIH5aNOq7obNSRyWFodxq-RCqQ02leJX4rqZ7ZluqZqoZa3bsivhO_pevpbviRYOti6ZObB1UnAx4sh6copgL-nNS_K-2vKO8EYRFK5Git20aQ0KSskz5ZX8LXuGcB4gckselDJ1_wUs73rh0STSkTcdMpyz6IiQoFANr5xW1epo8k6tbt3g-pAu1W-3jdT0sE9gAO5yrMDZ2OeuNXOqvXoKcTb55R8KYiZUEoNVCEdiqPBD3UIVlQt7FWFNP1W7XFztQoI47Lpl2O5yneWjdadksq4vBuJBJpWRBT76ivitdcTc-MDLVa3cRuH64hoBKxNAkaJzmtfMPhUzTC2dywZEFUJ.ClQI-LSOlaoxEhBYcXAwa0pCWFdod2FUcEI2GgcxLjg2LjEzIg5wYW4ueHVubGVpLmNvbSogYTAzMGYxN2YwYmQxOTMyNmJjM2VlNDY0M2I2OTg0NTQSgAEgk1HfYdomUzYdG0XrwfnKnvAuZ4hxGtLT06WWHsn5plIlmkE-r7WjVKt8tQmfQ-gfWDSunCOZpAK3l0VS21oM7Yt1GKXhHX812KstVJbGK7GTYCGYu8YEaIo5MkHQPp9zEimnKSD5P3WAYUC7jwtT03J6TayXMykYMgs0WBP9IQ");
        httpGet.setHeader("x-client-id", "Xqp0kJBXWhwaTpB6");
        httpGet.setHeader("x-device-id", "a030f17f0bd19326bc3ee4643b698454");
        try (CloseableHttpClient httpclient = HttpClients.createDefault()) {
            try (CloseableHttpResponse response = httpclient.execute(httpGet)) {
                HttpEntity entity = response.getEntity();
                String content = EntityUtils.toString(entity);

                log.debug("XunLeiWp[{}] => {} - {}", shareUrl, response.getCode(), content);

                if (response.getCode() == 200) {
                    XunLeiShareInfoResponse shareInfoResponse = JSONKit.readValue(content, XunLeiShareInfoResponse.class);

                    return WpShareInfo.builder()
                            .shareId(shareId)
                            .shareTitle(shareInfoResponse.getTitle())
                            .creatorId(shareInfoResponse.getUser_info().getUser_id())
                            .creatorAvatar(shareInfoResponse.getUser_info().getAvatar())
                            .creatorName(shareInfoResponse.getUser_info().getNickname())
                            /*.expiration(
                                    this.buildExpiration(shareInfoResponse.getExpireType(), shareInfoResponse.getExpireTime())
                            )*/
                            .needPassCode(true)
                            .passCode(code)
                            .build();
                } else {
                    XunLeiShareExpResponse shareExpResponse = JSONKit.readValue(content, XunLeiShareExpResponse.class);
                    return WpShareInfo.builder()
                            .valid(false)
                            .errMsg(shareExpResponse.getError_description())
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

    private LocalDateTime buildExpiration(Integer expiredType, Long expiredDays) {
        if (expiredType == 3) {
            LocalDateTime now = LocalDateTime.now();
            now.plusDays(expiredDays);
            return now;
        }
        return null;
    }
}
