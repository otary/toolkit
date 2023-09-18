package cn.chenzw.toolkit.wp.provider;

import cn.chenzw.toolkit.core.util.JSONKit;
import cn.chenzw.toolkit.wp.AbstractWpProvider;
import cn.chenzw.toolkit.wp.entity.WpShareInfo;
import cn.chenzw.toolkit.wp.enums.Wp;
import cn.chenzw.toolkit.wp.raw.response.A115ShareInfoResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.HttpEntity;
import org.apache.hc.core5.http.io.entity.EntityUtils;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author chenzw
 */
@Slf4j
public class A115WpProvider extends AbstractWpProvider {

    private static Pattern SHARE_URL_PATTERN = Pattern.compile("https://115\\.com/s/([A-Za-z0-9]+)(?:\\?password=([A-Za-z0-9]*)&?)?", Pattern.MULTILINE);


    @Override
    public WpShareInfo fetchShareInfo(String shareUrl, String code) throws Exception {
        String shareId = this.extractShareId(shareUrl);
        if (StringUtils.isEmpty(shareId)) {
            return WpShareInfo.builder()
                    .valid(false)
                    .errMsg("shareId is null!")
                    .build();
        }
        if (StringUtils.isEmpty(code)) {
            code = this.extractPassCodeFromShareUrl(shareUrl);
        }
        HttpGet httpGet = new HttpGet("https://webapi.115.com/share/snap?share_code=" + shareId + "&offset=0&limit=20&receive_code=" + code);
        try (CloseableHttpClient httpclient = HttpClients.createDefault()) {
            try (CloseableHttpResponse response = httpclient.execute(httpGet)) {
                String content = EntityUtils.toString(
                        response.getEntity()
                );

                log.debug("115Wp[{}] => {} - {}", shareUrl, response.getCode(), content);

                A115ShareInfoResponse shareInfoResponse = JSONKit.readValue(content, A115ShareInfoResponse.class);
                if (shareInfoResponse.getState()) {
                    return WpShareInfo.builder()
                            .shareId(shareId)
                            .shareTitle(shareInfoResponse.getData().getShareinfo().getShare_title())
                            .creatorId(shareInfoResponse.getData().getUserinfo().getUser_id())
                            .creatorAvatar(shareInfoResponse.getData().getUserinfo().getFace())
                            .creatorName(shareInfoResponse.getData().getUserinfo().getUser_name())
                            .fileSize(shareInfoResponse.getData().getShareinfo().getFile_size())
                            .fileCount(shareInfoResponse.getData().getCount())
                            .expiration(this.buildExpiration(shareInfoResponse.getData().getShareinfo().getExpire_time()))
                            .needPassCode(true)  // 全部需要分享码
                            .passCode(code)
                            .lastUpdateTime(new Date(shareInfoResponse.getData().getShareinfo().getCreate_time()))
                            .build();
                } else {
                    return WpShareInfo.builder()
                            .shareId(shareId)
                            .valid(false)
                            .errMsg(shareInfoResponse.getError())
                            .build();
                }
            }
        }
    }

    @Override
    public Pattern getShareUrlPattern() {
        return SHARE_URL_PATTERN;
    }


    @Override
    public boolean support(Wp wp) {
        return wp == Wp.A115;
    }

    private LocalDateTime buildExpiration(Long expireTime) {
        if (expireTime > -1) {
            return Instant.ofEpochSecond(expireTime).atZone(ZoneId.of("Asia/Shanghai")).toLocalDateTime();
        }
        return null;
    }
}
