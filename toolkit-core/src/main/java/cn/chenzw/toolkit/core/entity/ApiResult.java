package cn.chenzw.toolkit.core.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;

/**
 * API响应对象
 *
 * @param <T>
 * @author chenzw
 */
@Data
@SuperBuilder(toBuilder = true)
@NoArgsConstructor
public class ApiResult<T> implements AR<T> {

    public static final Long serialVersionUID = 1L;

    private boolean success = Boolean.FALSE;

    private String msg;

    private T payload;

    private String desc;

    private Object ext;

    public static ApiResult success() {
        return ApiResult
                .builder()
                .success(true)
                .build();
    }

    public static <T> ApiResult success(T payload) {
        return ApiResult
                .builder()
                .success(true)
                .payload(payload)
                .build();
    }

    public static ApiResult fail(String msg) {
        return ApiResult
                .builder()
                .success(false)
                .msg(msg)
                .build();
    }

}
