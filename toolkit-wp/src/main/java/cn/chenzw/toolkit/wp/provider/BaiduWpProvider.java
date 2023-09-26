package cn.chenzw.toolkit.wp.provider;

import cn.chenzw.toolkit.core.util.JSONKit;
import cn.chenzw.toolkit.wp.AbstractWpProvider;
import cn.chenzw.toolkit.wp.entity.WpShareInfo;
import cn.chenzw.toolkit.wp.enums.Wp;
import cn.chenzw.toolkit.wp.raw.content.BaiduLocalsInfo;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.text.MessageFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author chenzw
 */
@Slf4j
public class BaiduWpProvider extends AbstractWpProvider {

    private static Pattern SHARE_URL_PATTERN = Pattern.compile("https://pan\\.baidu\\.com/s/([A-Za-z0-9-]+)(?:\\?pwd=([A-Za-z0-9]*))?", Pattern.MULTILINE);

    private static Pattern LOCALS_PATTERN = Pattern.compile("locals\\.mset\\((.*?)\\);");

    private static Map<Integer, String> ERROR_TYPE = new HashMap<Integer, String>() {
        {
            put(0, "啊哦，你来晚了，分享的文件已经被删除了，下次要早点哟。");
            put(1, "啊哦，你来晚了，分享的文件已经被取消了，下次要早点哟。");
            put(2, "此链接分享内容暂时不可访问");
            put(3, "此链接分享内容可能因为涉及侵权、色情、反动、低俗等信息，无法访问！");
            put(5, "啊哦！链接错误没找到文件，请打开正确的分享链接!");
            put(10, "啊哦，来晚了，该分享文件已过期");
            put(11, "由于访问次数过多，该分享链接已失效");
            put(12, "因该分享含有自动备份文件夹，暂无法查看");
            put(15, "系统升级，链接暂时无法查看，升级完成后恢复正常。");
            put(17, "该链接访问范围受限，请使用正常的访问方式");
            put(123, "该链接已超过访问人数上限，可联系分享者重新分享");
            put(124, "您访问的链接已被冻结，可联系分享者进行激活");
        }
    };


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
        HttpGet httpGet = new HttpGet(shareUrl);
        httpGet.setHeader("Cookie", "BDCLND=irwBzZjz%2BtASxKJY2O8OJUCKBGbz4wwRRIhz0Lo33%2Fs%3D");
        try (CloseableHttpClient httpclient = HttpClients.createDefault()) {
            try (CloseableHttpResponse response = httpclient.execute(httpGet)) {
                String content = EntityUtils.toString(
                        response.getEntity()
                );
                BaiduLocalsInfo localsInfo = this.extractLocals(content);

                log.debug("BaiduWp[{}] => {} - {}", shareUrl, response.getCode(), localsInfo);

                if (response.getCode() == 200) {
                    Document doc = Jsoup.parse(content);
                    Integer errorType = localsInfo.getErrortype();
                    if (errorType == -1) { // 正常
                        return WpShareInfo.builder()
                                .shareId(shareId)
                                .shareTitle(StringUtils.substringBefore(doc.title(), "_免费高速下载"))
                                .creatorName(localsInfo.getLinkusername())
                                .creatorAvatar(localsInfo.getShare_photo())
                                .creatorId(localsInfo.getShare_uk())
                                .needPassCode(localsInfo.getPublic2() == 0)
                                .passCode(code)
                                .expiration(this.buildExpirationDate(localsInfo.getExpiredType()))
                                .passCode(code)
                                .lastUpdateTime(new Date(localsInfo.getCtime()))
                                .build();
                    }
                    return WpShareInfo.builder()
                            .shareId(shareId)
                            .passCode(code)
                            .valid(false)
                            .errMsg(ERROR_TYPE.getOrDefault(errorType, "分享的文件不存在"))
                            .build();
                }

                return WpShareInfo.builder()
                        .shareId(shareId)
                        .passCode(code)
                        .valid(false)
                        .errMsg(MessageFormat.format("request with error code=%s!", response.getCode()))
                        .build();
            }
        }
    }

    @Override
    public Pattern getShareUrlPattern() {
        return SHARE_URL_PATTERN;
    }

    @Override
    public Wp getType() {
        return Wp.BAIDU;
    }

    private BaiduLocalsInfo extractLocals(String html) throws JsonProcessingException {
        Matcher matcher = LOCALS_PATTERN.matcher(html);
        if (matcher.find()) {
            return JSONKit.readValue(
                    matcher.group(1), BaiduLocalsInfo.class
            );
        }
        return null;
    }


    private LocalDateTime buildExpirationDate(Integer expiredSeconds) {
        if (expiredSeconds == 0) {
            return null;
        }
        LocalDateTime dateTime = LocalDateTime.now();
        dateTime.plusSeconds(expiredSeconds);
        return dateTime;
    }
}
