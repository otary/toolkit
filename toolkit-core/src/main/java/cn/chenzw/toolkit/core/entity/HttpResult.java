package cn.chenzw.toolkit.core.entity;


/**
 * HTTP响应对象
 *
 * @param <T>
 * @author chenzw
 */
public class HttpResult<T> implements R<T> {

    public static final Integer SUCCESS_CODE = 200;
    public static final Integer ERROR_CODE = 500;

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

    public HttpResult(HttpResultBuilder builder) {
        this.code = builder.code;
        this.msg = builder.msg;
        this.detail = builder.detail;
        this.data = (T) builder.data;
        this.ext = builder.ext;
        this.msgId = builder.msgId;
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

    public static <T> HttpResult ok(T data) {
        return new HttpResultBuilder().code(SUCCESS_CODE).data(data).build();
    }

    public static HttpResultBuilder error() {
        return new HttpResultBuilder(ERROR_CODE);
    }

    public static HttpResult error(String msg) {
        return new HttpResultBuilder().code(ERROR_CODE).msg(msg).build();
    }

    public static HttpResult error(Integer code, String msg) {
        return new HttpResultBuilder().code(code).msg(msg).build();
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
