package cn.chenzw.toolkit.core.net;

import cn.chenzw.toolkit.core.net.entity.HTMLMetadata;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.net.URISyntaxException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * HTML处理工具类
 *
 * @author chenzw
 * @since 1.0.3
 */
public final class HTMLKit {


    /**
     * 提取HTML中的文本
     *
     * @param html
     * @return
     */
    public static String getText(String html) {
        return removeHtmlTags(html);
    }

    /**
     * 解析元数据
     *
     * @param html
     */
    public static HTMLMetadata parseAsMetadata(String html) {
        HTMLMetadata metadata = new HTMLMetadata();
        Document doc = Jsoup.parse(html);
        metadata.setDoc(doc);

        Elements titleElements = doc.select("title");
        if (titleElements.size() > 0) {
            metadata.setTitle(
                    titleElements.get(0).text()
            );
        }

        Elements keywordsElements = doc.select("meta[name=keywords]");
        if (keywordsElements.size() > 0) {
            metadata.setKeywords(
                    keywordsElements.get(0).attr("content")
            );
        }

        Elements descElements = doc.select("meta[name=description]");
        if (descElements.size() > 0) {
            metadata.setDescription(
                    descElements.get(0).attr("content")
            );
        }

        Elements appTitleElements = doc.select("meta[name=apple-mobile-web-app-title]");
        if (appTitleElements.size() > 0) {
            metadata.setAppName(
                    appTitleElements.get(0).attr("content")
            );
        }

        Elements rssXmlElements = doc.select("link[type=application/rss+xml]");
        for (Element rssXmlElement : rssXmlElements) {
            HTMLMetadata.RSS rss = new HTMLMetadata.RSS();
            rss.setTitle(rssXmlElement.attr("title"));
            rss.setUrl(rssXmlElement.attr("href"));
            metadata.getRssXMLs().add(rss);
        }

        Elements atomXmlElements = doc.select("link[type=application/atom+xml]");
        for (Element atomXmlElement : atomXmlElements) {
            HTMLMetadata.RSS rss = new HTMLMetadata.RSS();
            rss.setTitle(atomXmlElement.attr("title"));
            rss.setUrl(atomXmlElement.attr("href"));
            metadata.getAtomXMLs().add(rss);
        }

        Elements appleIconElements = doc.select("link[rel=apple-touch-icon-precomposed], link[rel=apple-touch-icon]");
        if (appleIconElements.size() > 0) {
            List<HTMLMetadata.Icon> appleIcons = metadata.getAppleIcons();
            for (Element appleIconEl : appleIconElements) {
                HTMLMetadata.Icon icon = new HTMLMetadata.Icon();
                icon.setSizes(appleIconEl.attr("sizes"));
                icon.setUrl(appleIconEl.attr("href"));
                appleIcons.add(icon);
            }
        }

        Elements iconElements = doc.select("link[rel=shortcut icon], link[rel=icon]");
        if (iconElements.size() > 0) {
            List<HTMLMetadata.Icon> icons = metadata.getIcons();
            for (Element iconEl : iconElements) {
                HTMLMetadata.Icon icon = new HTMLMetadata.Icon();
                icon.setSizes(iconEl.attr("sizes"));
                icon.setUrl(iconEl.attr("href"));
                icons.add(icon);
            }
        }
        return metadata;
    }

    /**
     * 生成完整的图标地址
     *
     * @param iconURI
     * @param siteUrl
     * @return
     * @throws URISyntaxException
     */
    public static String buildRealIconUrl(String iconURI, String siteUrl) throws URISyntaxException {
        if (StringUtils.isEmpty(siteUrl)) {
            return iconURI;
        }
        if (StringUtils.startsWithAny(iconURI, "http", "https", "//")) {
            return iconURI;
        }
        if (StringUtils.startsWith(iconURI, "/")) {
            return URLKit.getSchemeHost(siteUrl) + iconURI;
        }
        if (StringUtils.startsWith(iconURI, "data:image")) {
            return iconURI;
        }
        return URLKit.getSchemeHost(siteUrl) + "/" + iconURI;
    }


    /**
     * 删除所有HTML标签
     *
     * @param html
     * @return
     */
    private static String removeHtmlTags(String html) {
        //定义script的正则表达式，去除js可以防止注入
        String scriptRegex = "<script[^>]*?>[\\s\\S]*?<\\/script>";
        //定义style的正则表达式，去除style样式，防止css代码过多时只截取到css样式代码
        String styleRegex = "<style[^>]*?>[\\s\\S]*?<\\/style>";
        //定义HTML标签的正则表达式，去除标签，只提取文字内容
        String htmlRegex = "<[^>]+>";
        //定义空格,回车,换行符,制表符
        String spaceRegex = "\\s*|\t|\r|\n";

        // 过滤script标签
        html = html.replaceAll(scriptRegex, "");
        // 过滤style标签
        html = html.replaceAll(styleRegex, "");
        // 过滤html标签
        html = html.replaceAll(htmlRegex, "");
        // 过滤空格等
        html = html.replaceAll(spaceRegex, "");
        return html.trim();
    }


}
