package cn.chenzw.toolkit.dial.core.support;

/**
 * 异常回调
 *
 * @author chenzw
 * @since 1.0.3
 */
public interface ExceptionCallback {

    void call(DialRequest request, DialResponse response, Exception e);
}
