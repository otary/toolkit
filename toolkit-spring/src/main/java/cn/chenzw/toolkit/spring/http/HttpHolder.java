package cn.chenzw.toolkit.spring.http;

import cn.chenzw.toolkit.spring.util.SpringKit;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Http请求/响应对象容器
 *
 * @author chenzw
 */
public class HttpHolder {

    private static final ThreadLocal<HttpServletRequest> REQUEST = new ThreadLocal<>();
    private static final ThreadLocal<HttpServletResponse> RESPONSE = new ThreadLocal<>();

    private HttpHolder() {
    }

    public static void init(HttpServletRequest request, HttpServletResponse response) {
        REQUEST.set(request);
        RESPONSE.set(response);
    }

    public static HttpServletRequest getRequest() {
        if (REQUEST.get() == null) {
            return getRequestInternal();
        }
        return REQUEST.get();
    }

    public static HttpServletResponse getResponse() {
        if (RESPONSE.get() == null) {
            return getResponseInternal();
        }
        return RESPONSE.get();
    }

    /**
     * @since 1.0.3
     */
    public static void clear() {
        REQUEST.remove();
        RESPONSE.remove();
    }

    private static HttpServletRequest getRequestInternal() {
        if (SpringKit.SPRING_WEB_FRAME_PRESENT) {
            org.springframework.web.context.request.RequestAttributes requestAttributes = org.springframework.web.context.request.RequestContextHolder
                    .getRequestAttributes();
            if (requestAttributes != null) {
                return ((org.springframework.web.context.request.ServletRequestAttributes) requestAttributes).getRequest();
            }
        }
        return null;
    }

    private static HttpServletResponse getResponseInternal() {
        if (SpringKit.SPRING_WEB_FRAME_PRESENT) {
            org.springframework.web.context.request.RequestAttributes requestAttributes = org.springframework.web.context.request.RequestContextHolder.getRequestAttributes();
            if (requestAttributes != null) {
                return ((org.springframework.web.context.request.ServletRequestAttributes) requestAttributes).getResponse();
            }
        }
        return null;
    }

}
