package cn.chenzw.toolkit.logging.handler;

import cn.chenzw.toolkit.logging.core.LogField;

import java.util.Map;

/**
 * @author chenzw
 */
public interface MethodLoggingHandler {

    void process(Map<LogField, Object> methodLog);

    void processWithException(Map<LogField, Object> methodLog, Throwable throwable);
}
