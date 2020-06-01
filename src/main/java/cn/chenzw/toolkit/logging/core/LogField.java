package cn.chenzw.toolkit.logging.core;

/**
 * @author chenzw
 * @since 1.0.3
 */
public enum LogField {

    ALL,
    HTTP_METHOD, URI, QUERY_STRING, BODY_STRING,
    CLASS_NAME, METHOD_NAME, METHOD_ARGS,
    CLIENT_IP, THREAD_ID, THREAD_NAME;
}
