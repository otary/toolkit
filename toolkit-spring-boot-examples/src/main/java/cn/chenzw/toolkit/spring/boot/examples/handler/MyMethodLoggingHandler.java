package cn.chenzw.toolkit.spring.boot.examples.handler;

import cn.chenzw.toolkit.logging.annotation.MethodLogging;
import cn.chenzw.toolkit.logging.consts.LogField;
import cn.chenzw.toolkit.logging.handler.MethodLoggingHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * 自定义日志打印格式
 *
 * @author chenzw
 */
@Slf4j
@Component
public class MyMethodLoggingHandler implements MethodLoggingHandler {

    public boolean support(MethodLogging methodLogging) {
        return true;
    }

    public void process(Map<LogField, Object> methodLog, HttpServletRequest request) {
        log.info("request => {}", methodLog);
    }

    public void processWithException(Map<LogField, Object> methodLog, HttpServletRequest request, Throwable throwable) {
        log.error("request error => {}", methodLog);
    }
}
