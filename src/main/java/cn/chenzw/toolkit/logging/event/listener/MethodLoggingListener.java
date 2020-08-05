package cn.chenzw.toolkit.logging.event.listener;

import cn.chenzw.toolkit.logging.core.LogField;
import cn.chenzw.toolkit.logging.event.MethodLoggingEvent;
import cn.chenzw.toolkit.logging.handler.MethodLoggingHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;

import java.util.Map;

/**
 * @author chenzw
 */
public class MethodLoggingListener {

    @Autowired
    MethodLoggingHandler methodLoggingHandler;

    @Async
    @EventListener(MethodLoggingEvent.class)
    public void execute(MethodLoggingEvent event) {
        Map<LogField, Object> methodLogMap = (Map<LogField, Object>) event.getSource();
        Throwable throwable = event.getThrowable();
        if (throwable == null) {
            methodLoggingHandler.process(methodLogMap);
        } else {
            methodLoggingHandler.processWithException(methodLogMap, throwable);
        }
    }
}
