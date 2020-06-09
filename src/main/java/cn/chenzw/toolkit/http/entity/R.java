package cn.chenzw.toolkit.http.entity;

import java.io.Serializable;

/**
 * @author chenzw
 * @param <T>
 */
public interface R<T> extends Serializable {

    Integer getCode();

    String getMsg();

    T getData();

}
