package cn.chenzw.toolkit.http;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;

/**
 * HttpServletRequest工具类
 *
 * @author chenzw
 */
public class RequestUtils {

    private static final String[] CLIENT_IP_HEADERS = {"x-forwarded-for", "Proxy-Client-IP", "WL-Proxy-Client-IP",
            "HTTP_CLIENT_IP", "HTTP_X_FORWARDED_FOR", "X-Real-IP"};

    private RequestUtils() {
    }

    /**
     * 获取请求参数数组的第一个值
     *
     * @param params
     * @return
     */
    public static String getFirstParamter(Object params) {
        if (params instanceof String[]) {
            if (params == null || ArrayUtils.isEmpty((String[]) params)) {
                return "";
            }
            return ((String[]) params)[0];
        }
        return (String) params;
    }


    /**
     * 获取请求的客户端IP
     *
     * @return
     */
    public static String getClientIp(HttpServletRequest request) {
        for (String clientIpHeader : CLIENT_IP_HEADERS) {
            String ip = request.getHeader(clientIpHeader);
            if (!StringUtils.isBlank(ip) && !"unknown".equalsIgnoreCase(ip)) {
                return ip;
            }
        }
        return request.getRemoteAddr();
    }
}
