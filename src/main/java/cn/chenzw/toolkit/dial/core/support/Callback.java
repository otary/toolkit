package cn.chenzw.toolkit.dial.core.support;

/**
 * 回调函数
 *
 * @author chenzw
 * @since 1.0.3
 */
public interface Callback {

    void call(DialRequest request, DialResponse response);

}
