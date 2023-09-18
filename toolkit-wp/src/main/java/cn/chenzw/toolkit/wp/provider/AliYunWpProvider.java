package cn.chenzw.toolkit.wp.provider;

import cn.chenzw.toolkit.core.util.JSONKit;
import cn.chenzw.toolkit.wp.AbstractWpProvider;
import cn.chenzw.toolkit.wp.entity.WpShareInfo;
import cn.chenzw.toolkit.wp.enums.Wp;
import cn.chenzw.toolkit.wp.raw.response.AliYunShareInfoResponse;
import cn.chenzw.toolkit.wp.raw.response.exp.AliYunShareExpResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.hc.client5.http.classic.methods.HttpPost;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.apache.hc.core5.http.io.entity.StringEntity;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.regex.Pattern;

/**
 * @author chenzw
 */
@Slf4j
public class AliYunWpProvider extends AbstractWpProvider {

    private static Pattern SHARE_URL_PATTERN = Pattern.compile("https://www\\.aliyundrive\\.com/s/([A-Za-z0-9]+)$", Pattern.MULTILINE);


    @Override
    public WpShareInfo fetchShareInfo(String shareUrl, String code) throws Exception {
        String shareId = this.extractShareId(shareUrl);
        HttpPost httpPost = new HttpPost("https://api.aliyundrive.com/adrive/v3/share_link/get_share_by_anonymous?share_id=" + shareId);
        httpPost.setHeader("Content-Type", "application/json");
        httpPost.setEntity(new StringEntity("{\"share_id\": \"" + shareId + "\"}"));
        try (CloseableHttpClient httpclient = HttpClients.createDefault()) {
            try (CloseableHttpResponse response = httpclient.execute(httpPost)) {
                String content = EntityUtils.toString(
                        response.getEntity()
                );

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

                }

                // 异常处理
                AliYunShareExpResponse shareExpResponse = JSONKit.readValue(content, AliYunShareExpResponse.class);
                return WpShareInfo.builder()
                        .shareId(shareId)
                        .valid(false)
                        .errMsg(shareExpResponse.getMessage())
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
        // 阿里网盘不支持
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean support(Wp wp) {
        return wp == Wp.ALI_YUN;
    }
}
