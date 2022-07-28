package cn.chenzw.toolkit.http.entity;

import javax.servlet.http.HttpServletResponse;

/**
 * HTTP响应对象
 *
 * @param <T>
 * @author chenzw
 */
public class HttpResult<T> implements R<T> {

    public static final Integer SUCCESS_CODE = HttpServletResponse.SC_OK;
    public static final Integer ERROR_CODE = HttpServletResponse.SC_INTERNAL_SERVER_ERROR;

    private String msgId;

    /**
     * 异常编码
     */
    private Integer code;
    /**
     * 简短信息
     */
    private String msg;
    /**
     * 详细信息
     */
    private String detail;

    /**
     * 扩展字段
     */
    private Object ext;

    /**
     * 数据
     */
    private T data;

    /**
     * @param code
     * @param msg
     * @deprecated use {@link #builder()} instead
     */
    @Deprecated
    public HttpResult(Integer code, String msg) {
        this(code, msg, null, null);
    }

    /**
     * @param code
     * @param msg
     * @param data
     * @deprecated use {@link #builder()} instead
     */
    @Deprecated
    public HttpResult(Integer code, String msg, T data) {
        this(code, msg, null, data);
    }

    /**
     * @param code
     * @param msg
     * @param msgId
     * @param data
     * @deprecated use {@link #builder()} instead
     */
    @Deprecated
    public HttpResult(Integer code, String msg, String msgId, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
        this.msgId = msgId;
    }


    public HttpResult(HttpResultBuilder builder) {
        this.code = builder.code;
        this.msg = builder.msg;
        this.detail = builder.detail;
        this.data = (T) builder.data;
        this.ext = builder.ext;
        this.msgId = builder.msgId;
    }


    /**
     * 成功
     *
     * @param data
     * @param <T>
     * @return
     * @deprecated use {@link #builder()} instead
     */
    @Deprecated
    public static <T> HttpResult ok(T data) {
        return new HttpResult<T>(SUCCESS_CODE, null, data);
    }

    /**
     * 失败
     *
     * @param msg
     * @return
     * @deprecated use {@link #builder()} instead
     */
    @Deprecated
    public static HttpResult error(String msg) {
        return new HttpResult<>(ERROR_CODE, msg);
    }

    /**
     * @param code
     * @param msg
     * @return
     * @deprecated use {@link #builder()} instead
     */
    @Deprecated
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

    public void setExt(Object ext) {
        this.ext = ext;
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
    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    @Override
    public Object getExt() {
        return ext;
    }

    @Override
    public String toString() {
        return "HttpResult{" +
                "msgId='" + msgId + '\'' +
                ", code=" + code +
                ", msg='" + msg + '\'' +
                ", detail='" + detail + '\'' +
                ", ext='" + ext + '\'' +
                ", data=" + data +
                '}';
    }

    public static HttpResultBuilder builder() {
        return new HttpResultBuilder();
    }

    public static HttpResultBuilder ok() {
        return new HttpResultBuilder(SUCCESS_CODE);
    }

    public static HttpResultBuilder error() {
        return new HttpResultBuilder(ERROR_CODE);
    }

    /**
     * HttpResult构建器
     */
    public static class HttpResultBuilder {

        private String msgId;
        private Integer code;
        private String msg;
        private String detail;
        private Object ext;
        private Object data;

        public HttpResultBuilder() {
        }

        public HttpResultBuilder(Integer code) {
            this.code = code;
        }

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

        public HttpResultBuilder detail(String detail) {
            this.detail = detail;
            return this;
        }

        public HttpResultBuilder ext(Object ext) {
            this.ext = ext;
            return this;
        }

        public HttpResultBuilder data(Object data) {
            this.data = data;
            return this;
        }

        public HttpResult build() {
            return new HttpResult(this);
        }
    }
}
