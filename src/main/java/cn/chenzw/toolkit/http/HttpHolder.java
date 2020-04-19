package cn.chenzw.toolkit.http;


import cn.chenzw.toolkit.spring.util.SpringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
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
        if (SpringUtils.SPRING_WEB_FRAME_PRESENT) {
            return ((org.springframework.web.context.request.ServletRequestAttributes) org.springframework.web.context.request.RequestContextHolder
                    .getRequestAttributes()).getRequest();
        }
        return null;
    }

    private static HttpServletResponse getResponseInternal() {
        if (SpringUtils.SPRING_WEB_FRAME_PRESENT) {
            return ((org.springframework.web.context.request.ServletRequestAttributes) org.springframework.web.context.request.RequestContextHolder.getRequestAttributes()).getResponse();
        }
        return null;
    }


}
