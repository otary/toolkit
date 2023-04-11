package cn.chenzw.toolkit.spring.interceptor;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 跨域支持
 *
 * @author chenzw
 */
public class CorsInterceptor implements HandlerInterceptor {

    private String allowOrigin = "*";
    private String allowMethods;
    private Long maxAage;
    private String allowHeaders;
    private Boolean allowCredentials;

    public CorsInterceptor(String allowOrigin, String allowMethods, Long maxAage, String allowHeaders, Boolean allowCredentials) {
        this.allowOrigin = allowOrigin;
        this.allowMethods = allowMethods;
        this.maxAage = maxAage;
        this.allowHeaders = allowHeaders;
        this.allowCredentials = allowCredentials;
    }

    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object object,
                                Exception exception) {
        // nothing to do
    }

    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object object,
                           ModelAndView modelAndView) {
        // nothing to do
    }

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object object) throws Exception {
        response.addHeader("Access-Control-Allow-Origin", allowOrigin);
        if (!StringUtils.isEmpty(allowMethods)) {
            response.setHeader("Access-Control-Allow-Methods", allowMethods);
        }

        if (!StringUtils.isEmpty(allowHeaders)) {
            response.setHeader("Access-Control-Allow-Headers", allowHeaders);
        }

        if (maxAage != null) {
            response.setHeader("Access-Control-Max-Age", String.valueOf(maxAage));
        }

        if (allowCredentials != null) {
            response.setHeader("Access-Control-Allow-Credentials", allowCredentials.toString());
        }
        return true;
    }

}
