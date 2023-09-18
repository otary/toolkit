package cn.chenzw.toolkit.wp.provider;

import cn.chenzw.toolkit.core.util.JSONKit;
import cn.chenzw.toolkit.wp.AbstractWpProvider;
import cn.chenzw.toolkit.wp.WpProvider;
import cn.chenzw.toolkit.wp.entity.WpShareInfo;
import cn.chenzw.toolkit.wp.raw.response.QuarkShareInfoResponse;
import cn.chenzw.toolkit.wp.raw.response.UcShareInfoResponse;
import org.apache.hc.client5.http.classic.methods.HttpPost;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.HttpEntity;
import org.apache.hc.core5.http.ParseException;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.apache.hc.core5.http.io.entity.StringEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author chenzw
 */
public class UcWpProvider extends AbstractWpProvider {

    private static Pattern SHARE_URL_PATTERN = Pattern.compile("https://drive\\.uc\\.cn/s/[A-Za-z0-9]+$", Pattern.MULTILINE);

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
        String shareId = "d4443ee46957";
        HttpPost httpPost = new HttpPost("https://pc-api.uc.cn/1/clouddrive/share/sharepage/token");
        httpPost.setHeader("Content-Type", "application/json");
        String jsonPayload = "{\"pwd_id\":\"930e5f32a60f4\",\"passcode\":\"\"}";
        httpPost.setEntity(new StringEntity(jsonPayload));
        try (CloseableHttpClient httpclient = HttpClients.createDefault()) {
            try (CloseableHttpResponse response = httpclient.execute(httpPost)) {
                // 获取状态码
                System.out.println(response.getCode()); // 200
                System.out.println(response.getReasonPhrase()); // OK
                HttpEntity entity = response.getEntity();
                // 获取响应信息
                String resultContent = EntityUtils.toString(entity);

                if (response.getCode() == 200) {
                    UcShareInfoResponse shareInfoResponse = JSONKit.readValue(resultContent, UcShareInfoResponse.class);
                    //  shareInfoResponse.getData().getExpired_type();

                    return WpShareInfo.builder()
                            .shareTitle(shareInfoResponse.getData().getTitle())
                            .creatorAvatar(shareInfoResponse.getData().getAuthor().getAvatar_url())
                            .creatorName(shareInfoResponse.getData().getAuthor().getNick_name())
                            // .expiration()
                            // .needPwd(shareInfoResponse.getHas_pwd())
                            //.lastUpdateTime(shareInfoResponse.getUpdated_at())
                            .build();
                }

            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
        }
        return null;
    }

    @Override
    public String extractShareId(String shareUrl) {
        return null;
    }

    @Override
    public Pattern getShareUrlPattern() {
        return SHARE_URL_PATTERN;
    }
}
