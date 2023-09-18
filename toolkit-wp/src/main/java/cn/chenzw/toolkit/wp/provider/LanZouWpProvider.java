package cn.chenzw.toolkit.wp.provider;

import cn.chenzw.toolkit.core.util.JSONKit;
import cn.chenzw.toolkit.wp.AbstractWpProvider;
import cn.chenzw.toolkit.wp.entity.WpShareInfo;
import cn.chenzw.toolkit.wp.enums.Wp;
import cn.chenzw.toolkit.wp.raw.response.A115ShareInfoResponse;
import cn.chenzw.toolkit.wp.raw.response.LanZouShareInfoResponse;
import cn.chenzw.toolkit.wp.raw.response.QuarkShareInfoResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.classic.methods.HttpPost;
import org.apache.hc.client5.http.entity.UrlEncodedFormEntity;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.io.entity.BasicHttpEntity;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.apache.hc.core5.http.io.entity.StringEntity;
import org.apache.hc.core5.http.message.BasicNameValuePair;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.util.unit.DataSize;

import java.text.ParseException;
import java.util.Arrays;
import java.util.Date;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author chenzw
 */
@Slf4j
public class LanZouWpProvider extends AbstractWpProvider {

    private static final Pattern SHARE_URL_PATTERN = Pattern.compile("https://wwgl\\.lanzout\\.com/([A-Za-z0-9]+)", Pattern.MULTILINE);

    private static final Pattern SIGN_PATTERN = Pattern.compile("var(?:\\s)+skdklds(?:\\s)+=(?:\\s)+'(.*)';");

    private static final Pattern META_PATTERN = Pattern.compile("<span class=\"p7\">(.*?)：</span>(.*?)<br>");

    @Override
    public WpShareInfo fetchShareInfo(String shareUrl, String code) throws Exception {
        String shareId = this.extractShareId(shareUrl);
        HttpGet httpGet = new HttpGet(shareUrl);
        httpGet.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/108.0.5359.125 Safari/537.36");
        try (CloseableHttpClient httpclient = HttpClients.createDefault()) {
            try (CloseableHttpResponse response = httpclient.execute(httpGet)) {
                String content = EntityUtils.toString(
                        response.getEntity()
                );
                Document doc = Jsoup.parse(content);
                // 分享取消
                if (!doc.select("div.off0").isEmpty()) {
                    return WpShareInfo.builder()
                            .shareId(shareId)
                            .valid(false)
                            .errMsg(doc.select("div.off").text())
                            .build();
                }
                // 需要分享码
                if (!doc.select("div.passwddiv-input").isEmpty()) {
                    return this.parseWithShareCode(doc, content, code);
                }
                return this.parseWithDirectLink(doc, content, code);
            }
        }
    }

    @Override
    public Pattern getShareUrlPattern() {
        return SHARE_URL_PATTERN;
    }

    @Override
    public boolean support(Wp wp) {
        return wp == Wp.LAN_ZOU;
    }

    @Override
    public String extractPassCodeFromShareUrl(String shareUrl) {
        throw new UnsupportedOperationException();
    }

    private WpShareInfo parseWithDirectLink(Document doc, String content, String code) throws ParseException {
        WpShareInfo wpShareInfo = new WpShareInfo();
        Matcher matcher = META_PATTERN.matcher(content);
        while (matcher.find()) {
            String label = matcher.group(1);
            String value = matcher.group(2);

            switch (label) {
                case "文件大小":
                    // wpShareInfo.setFileSize();
                    break;
                case "上传时间":
                    wpShareInfo.setLastUpdateTime(
                            DateUtils.parseDate(value, "yyyy-MM-dd")
                    );
                    break;
                case "分享用户":
                    wpShareInfo.setCreatorName(
                            value.replace("<font>", "").replace("</font>", "")
                    );
                    break;
            }
        }
        wpShareInfo.setShareTitle(
                StringUtils.substringBefore(doc.title(), " - 蓝奏云")
        );
        return wpShareInfo;
    }

    private WpShareInfo parseWithShareCode(Document doc, String content, String code) throws Exception {
        WpShareInfo wpShareInfo = new WpShareInfo();
        wpShareInfo.setLastUpdateTime(
                DateUtils.parseDate(
                        doc.select("span.n_file_infos").get(0).text(), "yyy-MM-dd"
                )
        );
        wpShareInfo.setCreatorName(
                doc.select("span.user-name").text()
        );
        wpShareInfo.setNeedPassCode(true);
      /*  wpShareInfo.setFileSize(
                doc.select("span.n_filesize").text().replace("大小：", "")
        );*/

        Matcher matcher = SIGN_PATTERN.matcher(content);
        String sign = matcher.find() ? matcher.group(1) : "";
        if (StringUtils.isEmpty(sign)) {
            return WpShareInfo.builder()
                    .valid(false)
                    .errMsg("Sign is null!")
                    .build();
        }
        HttpPost httpPost = new HttpPost("https://wwgl.lanzout.com/ajaxm.php");
        httpPost.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/108.0.5359.125 Safari/537.36");
        httpPost.setEntity(
                new UrlEncodedFormEntity(
                        Arrays.asList(
                                new BasicNameValuePair("action", "downprocess"),
                                new BasicNameValuePair("sign", sign),
                                new BasicNameValuePair("p", code)
                        )
                )
        );
        try (CloseableHttpClient httpclient = HttpClients.createDefault()) {
            try (CloseableHttpResponse response = httpclient.execute(httpPost)) {
                String body = EntityUtils.toString(
                        response.getEntity()
                );
                LanZouShareInfoResponse shareInfoResponse = JSONKit.readValue(
                        body, LanZouShareInfoResponse.class
                );
                wpShareInfo.setShareTitle(shareInfoResponse.getInf());
                wpShareInfo.setPassCode(code);
            }
        }
        return wpShareInfo;
    }
}
