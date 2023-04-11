package cn.chenzw.toolkit.core.net;

import cn.chenzw.toolkit.core.lang.ReflectKit;
import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.Field;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.*;
import java.util.function.Predicate;

/**
 * 扩展URL
 *
 * @author chenzw
 */
public final class URLKit {

    private URLKit() {

    }


    /**
     * 构建url链接
     * <pre>
     * Map<String, String> params = new HashMap<String, String>() {
     *    {
     *       put("a", "111");
     *       put("b", "222");
     *    }
     *  };
     *  URLKit.buildParams("http://www.baidu.com", params) = "http://www.baidu.com?a=111&b=222"
     *  URLKit.buildParams("http://www.baidu.com?k=1", params) = "http://www.baidu.com?k=1&a=111&b=222"
     *  URLKit.buildParams("http://www.baidu.com?", params) = "http://www.baidu.com?a=111&b=222"
     * </pre>
     * @param url
     * @param params
     * @return
     */
    public static String buildParams(String url, Map<String, String> params) {
        return buildParams(url, params, entry -> true);
    }

    public static String buildParams(String url, Map<String, String> params, Predicate<Map.Entry<String, String>> filter) {
        if (StringUtils.isBlank(url)) {
            return "";
        } else {
            url = url.trim();
        }

        if (params == null || params.size() == 0) {
            return url.trim();
        }

        String sParam = buildParams(params, filter);

        int index = url.indexOf('?');
        if (index > -1) {
            // 最后一个字符为"?"
            if (index == (url.length() - 1)) {
                return url + sParam;
            } else {
                return url + "&" + sParam;
            }
        } else {
            return url + "?" + sParam;
        }
    }

    /**
     * 构建url链接
     * <pre>
     * Book book = new Book();
     * book.setId(1L);
     * book.setIsbn("ISN12345");
     * book.setName("hello");
     *
     * URLKit.buildParams("http://www.baidu.com", book) = "http://www.baidu.com?id=1&isbn=ISN12345&name=hello"
     * </pre>
     * @param url
     * @param paramObject 参数对象
     * @return
     * @throws IllegalAccessException
     */
    public static String buildParams(String url, Object paramObject) throws IllegalAccessException {
        return buildParams(url, paramObject, entry -> true);
    }

    public static String buildParams(String url, Object paramObject, Predicate<Map.Entry<String, String>> filter) throws IllegalAccessException {
        Map<String, String> paramsMap = new TreeMap<>();
        Field[] fields = ReflectKit.getFields(paramObject.getClass());
        for (Field field : fields) {
            Object fieldValue = ReflectKit.getFieldValue(paramObject, field);
            paramsMap.put(field.getName(), String.valueOf(fieldValue));
        }
        if (StringUtils.isEmpty(url)) {
            return buildParams(paramsMap, filter);
        }
        return buildParams(url, paramsMap, filter);
    }

    /**
     * 将Map对象转换成URL字符串
     *
     * @param params
     * @return
     */
    public static String buildParams(Map<String, String> params) {
        return buildParams(params, entry -> true);
    }

    public static String buildParams(Map<String, String> params, Predicate<Map.Entry<String, String>> predicate) {
        List<String> list = new ArrayList<>();
        for (Map.Entry<String, String> map : params.entrySet()) {
            if (predicate.test(map)) {
                list.add(map.getKey() + "=" + map.getValue());
            }
        }
        return StringUtils.join(list, "&");
    }

    /**
     * 将Java对象转换成URL字符串
     * <pre>
     * Book book = new Book();
     * book.setId(1L);
     * book.setIsbn("ISN12345");
     * book.setName("hello");
     *
     * URLKit.buildParams(book) = "id=1&isbn=ISN12345&name=hello"
     * </pre>
     * @param paramObject
     * @return
     * @throws IllegalAccessException
     */
    public static String buildParams(Object paramObject) throws IllegalAccessException {
        return buildParams(null, paramObject);
    }


    /**
     * 获取URL参数
     *
     * @param url
     * @return
     */
    public static Map<String, String> getParams(String url) {
        // 判断非空并去除空格
        if (StringUtils.isBlank(url)) {
            return Collections.emptyMap();
        }
        url = url.trim();

        // ?切割
        String[] aUri = url.split("\\?");
        if (aUri.length > 1) {
            return parseParams(aUri[1]);
        }
        return Collections.emptyMap();
    }

    /**
     * 解析Url参数
     *
     * @param paramString
     * @return
     */
    public static Map<String, String> parseParams(String paramString) {
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

    /**
     * 去除url指定参数
     *
     * @param url
     * @param names
     * @return
     */
    public static String removeParams(String url, String... names) {
        for (String name : names) {
            url = url.replaceAll("&?" + name + "=[^&]*", "");
        }
        return url;
    }

    /**
     * 去除所有参数
     *
     * @param url
     * @return
     */
    public static String removeAllParams(String url) {
        return url.substring(0, url.indexOf("?"));
    }

    /**
     * <pre>
     * URLKit.getSchemeHost("https://www.baidu.com/s?q=xx") = "https://www.baidu.com"
     * </pre>
     * @param url
     * @return
     * @throws URISyntaxException
     */
    public static String getSchemeHost(String url) throws URISyntaxException {
        URI uri = URI.create(url);
        return new URI(uri.getScheme(), uri.getUserInfo(), uri.getHost(), uri.getPort(), null, null, null).toString();
    }

    /**
     *
     * <pre>
     * URLKit.getSchemeHostPath("https://www.baidu.com/s?q=xx") = "https://www.baidu.com/s"
     * </pre>
     * @param url
     * @return
     * @throws URISyntaxException
     */
    public static String getSchemeHostPath(String url) throws URISyntaxException {
        URI uri = URI.create(url);
        return new URI(uri.getScheme(), uri.getUserInfo(), uri.getHost(), uri.getPort(), uri.getPath(), null, null).toString();
    }

}
