package cn.chenzw.toolkit.http;

import org.apache.commons.lang3.ArrayUtils;

/**
 * HttpServletRequest工具类
 *
 * @author chenzw
 */
public class RequestUtils {

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

}
