package cn.chenzw.toolkit.logging.event;

import cn.chenzw.toolkit.logging.core.LogField;
import org.springframework.context.ApplicationEvent;

import java.util.Map;

/**
 * @author chenzw
 */
public class MethodLoggingEvent extends ApplicationEvent {

    private Throwable throwable;

    public MethodLoggingEvent(Map<LogField, Object> methodLogMap, Throwable throwable) {
        super(methodLogMap);
        this.throwable = throwable;
    }

    public Throwable getThrowable() {
        return throwable;
    }
}
