package cn.chenzw.toolkit.http;

import cn.chenzw.toolkit.constants.CharsetConstatns;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Locale;

public class HttpRequestWrapper {

    private final static String POST_METHOD = "POST";
    private static final String MULTIPART = "multipart/";
    private static final String[] CLIENT_IP_HEADERS = {"x-forwarded-for", "Proxy-Client-IP", "WL-Proxy-Client-IP",
            "HTTP_CLIENT_IP", "HTTP_X_FORWARDED_FOR", "X-Real-IP"};
    private HttpServletRequest request;

    public HttpRequestWrapper() {
        this.request = HttpHolder.getRequest();

        if (this.request == null) {
            throw new IllegalArgumentException("HttpServletRequest must not be null");
        }
    }

    public HttpRequestWrapper(HttpServletRequest request) {
        if (request == null) {
            throw new IllegalArgumentException("HttpServletRequest must not be null");
        }
        this.request = request;
    }

    /**
     * 获取HTTP方法
     *
     * @return
     */
    public String getMethod() {
        return this.request.getMethod();
    }

    /**
     * 获取URI链接
     *
     * @return
     */
    public String getURI() {
        return this.request.getRequestURI();
    }

    /**
     * 获取请求参数
     *
     * @return
     */
    public String getQueryString() {
        return this.request.getQueryString();
    }

    /**
     * 获取请求的客户端IP
     *
     * @return
     */
    public String getClientIp() {
        for (String clientIpHeader : CLIENT_IP_HEADERS) {
            String ip = request.getHeader(clientIpHeader);
            if (!StringUtils.isBlank(ip) && !"unknown".equalsIgnoreCase(ip)) {
                return ip;
            }
        }
        return this.request.getRemoteAddr();
    }


    public long getThreadId() {
        return Thread.currentThread().getId();
    }

    public String getThreadName() {
        return Thread.currentThread().getName();
    }

    /**
     * 获取HTTP Body内容
     * @return
     * @throws IOException
     */
    public String getBodyString() throws IOException {
        if (isMultipart(request)) {
            return "";
        }
        return IOUtils.toString(this.request.getInputStream(), CharsetConstatns.DEFAULT_CHARSET);
    }

    /**
     * 是否上传文件
     * @param request
     * @return
     */
    private boolean isMultipart(HttpServletRequest request) {
        if (!POST_METHOD.equalsIgnoreCase(request.getMethod())) {
            return false;
        }

        String contentType = request.getContentType();
        if (StringUtils.isBlank(contentType)) {
            return false;
        }

        if (contentType.toLowerCase(Locale.ENGLISH).startsWith(MULTIPART)) {
            return true;
        }
        return false;
    }

}
