package cn.chenzw.toolkit.commons;

/**
 * @author chenzw
 * @since 1.0.3
 */
public class HtmlUtils {

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
