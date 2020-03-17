package cn.chenzw.toolkit.dial.core.support;


/**
 * 拨测处理器
 *
 * @author chenzw
 * @since 1.0.3
 */
public interface DialProcessor {


    DialResponse execute(DialRequest request, DialResponseResolver dialResponseResolver) throws Exception;
}
