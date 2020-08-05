package cn.chenzw.toolkit.logging.core;

/**
 * @author chenzw
 */
public class MethodLoggingContext {

    private static final ThreadLocal<String> LOG_ID_TPL = new ThreadLocal<>();

    public static String getLogId() {
        return LOG_ID_TPL.get();
    }

    public static void setLogId(String logId) {
        LOG_ID_TPL.set(logId);
    }
}
