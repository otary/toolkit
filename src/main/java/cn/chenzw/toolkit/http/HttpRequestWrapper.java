package cn.chenzw.toolkit.http;

import cn.chenzw.toolkit.constants.CharsetConstatns;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Locale;

/**
 * HttpRequest包装类
 *
 * @author chenzw
 */
public class HttpRequestWrapper {

    private static final String POST_METHOD = "POST";
    private static final String MULTIPART = "multipart/";
    private static final String X_REQUESTED_WIDTH_HEADER = "X-Requested-With";
    private static final String AJAX_HEADER = "XMLHttpRequest";

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
     * <p>
     * eg. /users/list
     *
     * @return
     */
    public String getURI() {
        return this.request.getRequestURI();
    }

    /**
     * 获取请求参数
     * <p>
     * eg. id=1&name=zhansan
     *
     * @return
     */
    public String getQueryString() {
        return this.request.getQueryString();
    }


    /**
     * 获取URL连接
     * <p>
     * eg.http://localhost:8080/users/list?id=1&name=zhansan
     *
     * @return
     */
    public String getURL() {
        return this.request.getRequestURL().toString();
    }

    /**
     * 获取请求的客户端IP
     *
     * @return
     */
    public String getClientIp() {
        return RequestUtils.getClientIp(request);
    }

    /**
     * 获取请求线程ID
     *
     * @return
     */
    public long getThreadId() {
        return Thread.currentThread().getId();
    }

    /**
     * 获取请求线程名称
     *
     * @return
     */
    public String getThreadName() {
        return Thread.currentThread().getName();
    }

    /**
     * 获取HTTP Body内容
     *
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
     *
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

        return contentType.toLowerCase(Locale.ENGLISH).startsWith(MULTIPART);
    }

    /**
     * 是否Ajax请求
     *
     * @return
     */
    public boolean isAjax() {
        return AJAX_HEADER.equalsIgnoreCase(request.getHeader(X_REQUESTED_WIDTH_HEADER));
    }

    /**
     * 获取domain值
     *
     * @return
     * @since 1.0.3
     */
    public String getDomain() {
        StringBuffer url = request.getRequestURL();
        return url.delete(url.length() - request.getRequestURI().length(), url.length()).toString();
    }

    /**
     * 获取浏览器agent
     *
     * @return
     * @since 1.0.3
     */
    public String getUserAgent() {
        return request.getHeader("user-agent");
    }
}
