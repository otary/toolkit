package cn.chenzw.toolkit.logging.handler;

import cn.chenzw.toolkit.logging.core.LogField;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

/**
 * @author chenzw
 */
public class Log4jMethodLoggingHandler implements MethodLoggingHandler {

    private Logger log = LoggerFactory.getLogger(getClass());

    @Override
    public void process(Map<LogField, Object> methodLog) {
        // @TODO to debug level
        log.info("URI invoke: {}", methodLog);
    }

    @Override
    public void processWithException(Map<LogField, Object> methodLog, Throwable throwable) {
        log.error("URI invoke with error: {} => {}", methodLog, throwable);
    }
}
