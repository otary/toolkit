package cn.chenzw.toolkit.wp;

import cn.chenzw.toolkit.wp.entity.WpShareInfo;
import cn.chenzw.toolkit.wp.enums.Wp;

import java.util.List;
import java.util.regex.Pattern;

/**
 * @author chenzw
 */
public interface WpProvider {

    /**
     * 判断是否分享链接
     *
     * @param shareUrl
     * @return
     */
    boolean shareUrlMatches(String shareUrl);

    /**
     * 提取分享链接
     *
     * @param content
     * @return
     */
    List<String> extractShareUrls(String content);

    /**
     * 获取分享信息
     *
     * @param shareUrl
     * @param code
     * @return
     * @throws Exception
     */
    WpShareInfo fetchShareInfo(String shareUrl, String code) throws Exception;

    /**
     * 提取分享ID
     *
     * @param shareUrl
     * @return
     */
    String extractShareId(String shareUrl);

    /**
     * 获取分享链接正则表达式
     *
     * @return
     */
    Pattern getShareUrlPattern();

    /**
     * 从分享链接中提取分享码
     *
     * @param shareUrl
     * @return
     */
    String extractPassCodeFromShareUrl(String shareUrl);

    boolean support(Wp wp);

    Wp getType();
}
