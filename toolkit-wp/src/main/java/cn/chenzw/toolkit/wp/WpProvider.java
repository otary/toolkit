package cn.chenzw.toolkit.wp;

import cn.chenzw.toolkit.wp.entity.WpShareInfo;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;
import java.util.regex.Pattern;

/**
 * @author chenzw
 */
public interface WpProvider {

    /**
     * 判断是否分享链接
     * @param shareUrl
     * @return
     */
    boolean shareUrlMatches(String shareUrl);

    List<String> extractShareUrls(String content);

    WpShareInfo fetchShareInfo(String shareUrl, String code) throws Exception;

    String extractShareId(String shareUrl);

    Pattern getShareUrlPattern();

    String extractPassCodeFromShareUrl(String shareUrl);
}
