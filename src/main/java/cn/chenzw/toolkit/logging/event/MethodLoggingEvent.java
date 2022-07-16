package cn.chenzw.toolkit.logging.event;

import cn.chenzw.toolkit.logging.annotation.MethodLogging;
import cn.chenzw.toolkit.logging.core.LogField;
import org.springframework.context.ApplicationEvent;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @author chenzw
 */
public class MethodLoggingEvent extends ApplicationEvent {

    private HttpServletRequest request;

    private Throwable throwable;

    private MethodLogging methodLogging;

    public MethodLoggingEvent(Map<LogField, Object> methodLogMap,
                              HttpServletRequest request,
                              MethodLogging methodLogging,
                              Throwable throwable) {
        super(methodLogMap);
        this.request = request;
        this.methodLogging = methodLogging;
        this.throwable = throwable;
    }

    public Throwable getThrowable() {
        return throwable;
    }

    public HttpServletRequest getRequest() {
        return request;
    }

    public MethodLogging getMethodLogging() {
        return methodLogging;
    }
}
