package cn.chenzw.toolkit.http.entity;

import javax.servlet.http.HttpServletResponse;

/**
 * HTTP响应结果对象
 *
 * @param <T>
 * @author chenzw
 */
public class HttpResult<T> implements R<T> {

    public static final Integer SUCCESS_CODE = HttpServletResponse.SC_OK;
    public static final Integer ERROR_CODE = HttpServletResponse.SC_INTERNAL_SERVER_ERROR;

    private String msgId;
    private Integer code;
    private String msg;
    private T data;

    public HttpResult(Integer code, String msg) {
        this(code, msg, null, null);
    }

    public HttpResult(Integer code, String msg, T data) {
        this(code, msg, null, data);
    }

    public HttpResult(Integer code, String msg, String msgId, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
        this.msgId = msgId;
    }

    /**
     * 成功
     *
     * @return
     */
    public static HttpResult ok() {
        return new HttpResult<>(SUCCESS_CODE, null);
    }

    /**
     * 成功
     *
     * @param data
     * @param <T>
     * @return
     */
    public static <T> HttpResult ok(T data) {
        return new HttpResult<T>(SUCCESS_CODE, null, data);
    }

    /**
     * 失败
     *
     * @param msg
     * @return
     */
    public static HttpResult error(String msg) {
        return new HttpResult<>(ERROR_CODE, msg);
    }

    public static HttpResult error(Integer code, String msg) {
        return new HttpResult<>(code, msg);
    }


    public void setCode(Integer code) {
        this.code = code;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public Integer getCode() {
        return code;
    }

    @Override
    public String getMsg() {
        return msg;
    }

    @Override
    public T getData() {
        return data;
    }

    @Override
    public String getMsgId() {
        return msgId;
    }

    public void setMsgId(String msgId) {
        this.msgId = msgId;
    }

    @Override
    public String toString() {
        return "HttpResult{" +
                "msgId='" + msgId + '\'' +
                ", code=" + code +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }

    public static HttpResultBuilder builder() {
        return new HttpResultBuilder();
    }

    public static class HttpResultBuilder {

        private String msgId;
        private Integer code;
        private String msg;
        private Object data;

        public HttpResultBuilder msgId(String msgId) {
            this.msgId = msgId;
            return this;
        }

        public HttpResultBuilder code(Integer code) {
            this.code = code;
            return this;
        }

        public HttpResultBuilder msg(String msg) {
            this.msg = msg;
            return this;
        }

        public HttpResultBuilder data(Object data) {
            this.data = data;
            return this;
        }

        public HttpResultBuilder ok() {
            this.code = SUCCESS_CODE;
            return this;
        }

        public HttpResultBuilder error() {
            this.code = ERROR_CODE;
            return this;
        }

        public HttpResult build() {
            return new HttpResult(code, msg, msgId, data);
        }
    }
}
