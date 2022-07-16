package cn.chenzw.toolkit.logging.handler;

import cn.chenzw.toolkit.logging.annotation.MethodLogging;
import cn.chenzw.toolkit.logging.core.LogField;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Map;

/**
 * @author chenzw
 */
public class Log4jMethodLoggingHandler implements MethodLoggingHandler {

    private Logger log = LoggerFactory.getLogger(getClass());

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
