package cn.chenzw.toolkit.http;

import org.apache.commons.io.IOUtils;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

/**
 * @author chenzw
 */
public class ResponseUtils {

    private static final String OCTET_STREAM_CONTENT_TYPE = "application/octet-stream";


    private ResponseUtils() {
    }


    /**
     * 下载
     *
     * @param fileName
     * @param inputStream
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
}
