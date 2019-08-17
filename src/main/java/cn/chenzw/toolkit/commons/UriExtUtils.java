package cn.chenzw.toolkit.commons;

import org.apache.commons.lang3.StringUtils;

import java.util.*;

/**
 * 扩展UriUtils
 *
 * @author chenzw
 */
public class UriExtUtils {

    private UriExtUtils() {

    }


    /**
     * 构建url链接
     *
     * @param uri
     * @param params
     * @return
     */
    public static String buildParams(String uri, Map<String, String> params) {
        if (StringUtils.isBlank(uri)) {
            return "";
        } else {
            uri = uri.trim();
        }

        if (params == null || params.size() == 0) {
            return uri.trim();
        }

        List<String> list = new ArrayList<>();
        for (Map.Entry<String, String> map : params.entrySet()) {
            list.add(map.getKey() + "=" + map.getValue());
        }
        String sParam = StringUtils.join(list, "&");

        int index = uri.indexOf('?');
        if (index > -1) {
            // 最后一个字符为"?"
            if (index == (uri.length() - 1)) {
                return uri + sParam;
            } else {
                return uri + "&" + sParam;
            }
        } else {
            return uri + "?" + sParam;
        }
    }


    /**
     * 获取Uri参数
     *
     * @param uri
     * @return
     */
    public static Map<String, String> getUriParams(String uri) {

        // 判断非空并去除空格
        if (StringUtils.isBlank(uri)) {
            return Collections.emptyMap();
        }
        uri = uri.trim();

        // ?切割
        String[] aUri = uri.split("\\?");
        if (aUri.length > 1) {
            return parseUriParams(aUri[1]);
        }
        return Collections.emptyMap();
    }

    /**
     * 解析Uri参数
     *
     * @param paramString
     * @return
     */
    public static Map<String, String> parseUriParams(String paramString) {
        if (StringUtils.isBlank(paramString)) {
            return Collections.emptyMap();
        }
        paramString = paramString.trim();

        String[] paramPairs = paramString.split("&");
        Map<String, String> retParamMap = new HashMap<>();
        for (String paramPair : paramPairs) {
            String[] param = paramPair.split("=");
            if (param.length > 1) {
                retParamMap.put(param[0], param[1]);
            } else {
                retParamMap.put(param[0], "");
            }
        }
        return retParamMap;
    }


}
