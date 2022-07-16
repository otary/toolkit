package cn.chenzw.toolkit.logging.handler;

import cn.chenzw.toolkit.logging.annotation.MethodLogging;
import cn.chenzw.toolkit.logging.core.LogField;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @author chenzw
 */
public interface MethodLoggingHandler {

    boolean support(MethodLogging methodLogging);

    void process(Map<LogField, Object> methodLog, HttpServletRequest request);

    void processWithException(Map<LogField, Object> methodLog, HttpServletRequest request, Throwable throwable);
}
