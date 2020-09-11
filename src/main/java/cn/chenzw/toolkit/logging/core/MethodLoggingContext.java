package cn.chenzw.toolkit.logging.core;

import org.springframework.core.NamedThreadLocal;

/**
 * @author chenzw
 */
public class MethodLoggingContext {

    private static final ThreadLocal<String> logIdHolder = new NamedThreadLocal<>("logid-threadlocal");

    public static String getLogId() {
        return logIdHolder.get();
    }

    public static void setLogId(String logId) {
        logIdHolder.set(logId);
    }
}
