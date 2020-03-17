package cn.chenzw.toolkit.dial.http;

import cn.chenzw.toolkit.dial.core.support.DialResponse;

import java.util.Map;

/**
 * 拨测Http响应对象
 *
 * @author chenzw
 * @since 1.0.3
 */
public interface DialHttpResponse extends DialResponse {

    /**
     * 获取响应状态码
     *
     * @return
     */
    int code();

    /**
     * 获取响应简短消息
     *
     * @return
     */
    String message();

    /**
     * 获取响应内容
     *
     * @return
     */
    String body();

    /**
     * 获取响应头部
     *
     * @return
     */
    Map<String, String> headers();

}
