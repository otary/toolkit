package cn.chenzw.toolkit.http;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletMapping;
import javax.servlet.http.HttpServletRequest;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

/**
 * HttpServletRequest克隆体
 *
 * @author chenzw
 * @since 1.0.3
 */
public class HttpServletRequestClone extends ContentCachingRequestWrapper {

    private Map<String, String> headerMap = new HashMap<>();
    private String authType;
    private Cookie[] cookies;
    private HttpServletMapping httpServletMapping;
    private String method;
    private String pathInfo;
    private String pathTranslated;
    private String contextPath;
    private String requestURI;
    private StringBuffer requestURL;
    private String servletPath;


    public HttpServletRequestClone(HttpServletRequest request) {
        super(request);

        Enumeration<String> headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String headerName = headerNames.nextElement();
            headerMap.put(headerName, request.getHeader(headerName));
        }
        this.authType = request.getAuthType();
        this.cookies = request.getCookies();
        this.httpServletMapping = request.getHttpServletMapping();
        this.method = request.getMethod();
        this.pathInfo = request.getPathInfo();
        this.pathTranslated = request.getPathTranslated();
        this.contextPath = request.getContextPath();
        this.requestURI = request.getRequestURI();
        this.requestURL = request.getRequestURL();
        this.servletPath = request.getServletPath();

    }

    @Override
    public String getHeader(String name) {
        return headerMap.get(name);
    }

    @Override
    public Enumeration<String> getHeaderNames() {
        return Collections.enumeration(headerMap.keySet());
    }

    @Override
    public int getIntHeader(String name) {
        return Integer.valueOf(headerMap.get(name));
    }

    @Override
    public long getDateHeader(String name) {
        return Long.valueOf(headerMap.get(name));
    }

    @Override
    public String getAuthType() {
        return authType;
    }

    @Override
    public Cookie[] getCookies() {
        return cookies;
    }


    @Override
    public HttpServletMapping getHttpServletMapping() {
        return httpServletMapping;
    }

    @Override
    public String getMethod() {
        return method;
    }

    @Override
    public String getPathInfo() {
        return pathInfo;
    }

    @Override
    public String getPathTranslated() {
        return pathTranslated;
    }

    @Override
    public String getContextPath() {
        return contextPath;
    }

    @Override
    public String getQueryString() {
        return contextPath;
    }

    @Override
    public String getRequestURI() {
        return requestURI;
    }

    @Override
    public StringBuffer getRequestURL() {
        return requestURL;
    }

    @Override
    public String getServletPath() {
        return servletPath;
    }


}
