package cn.chenzw.toolkit.http.entity;

import javax.servlet.http.HttpServletResponse;

/**
 * @author chenzw
 * @param <T>
 */
public class HttpResult<T> implements R<T> {

    public static final Integer SUCCESS_CODE = HttpServletResponse.SC_OK;
    public static final Integer ERROR_CODE = HttpServletResponse.SC_INTERNAL_SERVER_ERROR;

    private Integer code;
    private String msg;
    private T data;

    public HttpResult(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public HttpResult(Integer code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
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
    public String toString() {
        return "HttpResult{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }
}
