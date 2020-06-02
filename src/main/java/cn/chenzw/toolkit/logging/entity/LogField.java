package cn.chenzw.toolkit.logging.entity;

/**
 * @author chenzw
 * @since 1.0.3
 */
public enum LogField {

    ALL("all"),
    HTTP_METHOD("httpMethod"), URI("uri"), QUERY_STRING("queryString"), BODY_STRING("bodyString"),
    CLASS_NAME("className"), METHOD_NAME("methodName"), METHOD_ARGS("methodArgs"),
    CLIENT_IP("clientIp"), THREAD_ID("threadId"), THREAD_NAME("threadName");


    private String name;

    LogField(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
