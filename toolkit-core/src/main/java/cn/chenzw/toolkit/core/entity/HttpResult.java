package cn.chenzw.toolkit.core.entity;


import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * Http响应对象
 *
 * @param <T>
 * @author chenzw
 */
@Data
@SuperBuilder(toBuilder = true)
@NoArgsConstructor
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
    private T payload;

    public static <T> HttpResult ok(T payload) {
        return HttpResult.builder().code(SUCCESS_CODE).payload(payload).build();
    }

    public static HttpResult error(String msg) {
        return HttpResult.builder().code(ERROR_CODE).msg(msg).build();
    }

    public static HttpResult error(Integer code, String msg) {
        return HttpResult.builder().code(code).msg(msg).build();
    }

}
