package cn.chenzw.toolkit.dial.core.support;

/**
 * 拨测响应对象
 *
 * @author chenzw
 * @since 1.0.3
 */
public interface DialResponse {

    DialResponseResolver getResponseResolver();

    boolean isSuccessful();

    void close();

}
