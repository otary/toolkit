package cn.chenzw.toolkit.spring.http;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

/**
 * HttpServletResponse工具类
 *
 * @author chenzw
 */
public class ResponseKit {

    private static final String OCTET_STREAM_CONTENT_TYPE = "application/octet-stream";


    private ResponseKit() {
    }


    /**
     * 下载文件
     *
     * @param fileName    下载的文件名
     * @param inputStream 输入流
     * @throws IOException
     */
    public static void download(String fileName, InputStream inputStream) throws IOException {
        download(fileName, inputStream, Charset.defaultCharset());
    }

    public static void download(String fileName, InputStream inputStream, Charset charset) throws IOException {
        download(null, fileName, inputStream, charset);
    }

    public static void download(HttpServletResponse response, String fileName, InputStream inputStream, Charset charset) throws IOException {
        if (response == null) {
            response = HttpHolder.getResponse();
        }

        response.setContentType(OCTET_STREAM_CONTENT_TYPE + (charset != null ? ";charset=" + charset.name() : ""));
        response.setHeader("Content-Disposition",
                "attachment; filename=\"" + fileName + "\"; filename*=utf-8''" + fileName);

        IOUtils.copy(inputStream, response.getOutputStream());
    }


    /**
     * 生成html提示消息
     *
     * @param title
     * @param msg
     * @return
     */
    public static String buildHtmlMsg(String title, String msg) {
        return buildHtmlMsg(title, null, msg);
    }

    public static String buildHtmlMsg(String title, String tip, String msg) {
        return "<div style=\"position: relative; width: 100%; height: 500px; text-align:center;\">"
                + "<div style=\" width: 300px; margin: 100px auto; padding: 10px; border: 1px solid #ccc; border-radius: 5px; overflow: hidden;\">"
                + "<div style=\"padding: 10px 3px; border-bottom: 1px solid #ccc; font-weight: bold;\">" + title
                + "</div>" + "<div style=\"padding: 10px 3px;\">" + (StringUtils.isEmpty(tip) ? "" : "【" + tip + "】: ") + msg + "</div></div></div>";
    }

    /**
     * 输出html消息提示
     *
     * @param response
     * @param title
     * @param tip
     * @param msg
     */
    public static void printHtmlMsg(HttpServletResponse response, String title, String tip, String msg) {
        response.setContentType("text/html");
        response.setCharacterEncoding(StandardCharsets.UTF_8.name());

        try {
            response.getWriter().write(buildHtmlMsg(title, tip, msg));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
