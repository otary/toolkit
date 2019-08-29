package cn.chenzw.toolkit.http;

import org.apache.commons.io.IOUtils;

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
public class ResponseUtils {

    private static final String OCTET_STREAM_CONTENT_TYPE = "application/octet-stream";


    private ResponseUtils() {
    }


    /**
     * 下载文件
     *
     * @param fileName    下载的文件名
     * @param inputStream 输入流
     * @throws IOException
     */
    public static void download(String fileName, InputStream inputStream) throws IOException {
        download(fileName, inputStream, StandardCharsets.UTF_8);
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


    public static String buildHtmlMsg(String title, String msg) {
        return "<div style=\"position: relative; width: 100%; height: 500px; text-align:center;\">"
                + "<div style=\" width: 300px; margin: 100px auto; padding: 10px; border: 1px solid #ccc; border-radius: 5px; overflow: hidden;\">"
                + "<div style=\"padding: 10px 3px; border-bottom: 1px solid #ccc; font-weight: bold;\">" + title
                + "</div>" + "<div style=\"padding: 10px 3px;\">" + "【错误提示】: " + msg + "</div></div></div>";
    }

}
