package cn.chenzw.toolkit.logging.event.listener;

import cn.chenzw.toolkit.logging.annotation.MethodLogging;
import cn.chenzw.toolkit.logging.consts.LogField;
import cn.chenzw.toolkit.logging.event.MethodLoggingEvent;
import cn.chenzw.toolkit.logging.handler.MethodLoggingHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * @author chenzw
 */
public class MethodLoggingListener {

    @Autowired
    List<MethodLoggingHandler> methodLoggingHandlers;

    @Async
    @EventListener(MethodLoggingEvent.class)
    public void execute(MethodLoggingEvent event) {
        Map<LogField, Object> methodLogMap = (Map<LogField, Object>) event.getSource();
        Throwable throwable = event.getThrowable();
        HttpServletRequest request = event.getRequest();
        MethodLogging methodLogging = event.getMethodLogging();
        for (MethodLoggingHandler methodLoggingHandler : methodLoggingHandlers) {
            if (methodLoggingHandler.support(methodLogging)) {
                if (throwable == null) {
                    methodLoggingHandler.process(methodLogMap, request);
                } else {
                    methodLoggingHandler.processWithException(methodLogMap, request, throwable);
                }
            }
        }
    }
}
