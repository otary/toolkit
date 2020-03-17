package cn.chenzw.toolkit.dial.http;

import cn.chenzw.toolkit.dial.core.support.DialRequest;

import java.util.Map;

/**
 * 拨测Http请求对象
 *
 * @author chenzw
 * @since 1.0.3
 */
public interface DialHttpRequest extends DialRequest {

    /**
     * 获取请求的url
     *
     * @return 请求的url
     */
    String getUrl();


    /**
     * 获取请求的方法
     *
     * @return 请求的方法
     */
    String getMethod();

    /**
     * 获取请求的头部
     *
     * @return 请求的头部
     */
    Map<String, String> getHeaders();
}
