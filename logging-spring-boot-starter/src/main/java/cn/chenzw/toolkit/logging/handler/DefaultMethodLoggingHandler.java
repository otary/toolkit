package cn.chenzw.toolkit.logging.handler;

import cn.chenzw.toolkit.logging.annotation.MethodLogging;
import cn.chenzw.toolkit.logging.consts.LogField;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ArrayUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @author chenzw
 */
@Slf4j
public class DefaultMethodLoggingHandler implements MethodLoggingHandler {

    @Override
    public boolean support(MethodLogging methodLogging) {
        return ArrayUtils.contains(methodLogging.support(), "DEFAULT");
    }

    @Override
    public void process(Map<LogField, Object> methodLog, HttpServletRequest request) {
        log.info("request invoke => {}", methodLog);
    }

    @Override
    public void processWithException(Map<LogField, Object> methodLog, HttpServletRequest request, Throwable throwable) {
        log.error("request invoke with error => {} => {}", methodLog, throwable);
    }
}
