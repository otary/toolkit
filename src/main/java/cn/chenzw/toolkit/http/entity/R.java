package cn.chenzw.toolkit.http.entity;

import java.io.Serializable;

public interface R<T> extends Serializable {

    Integer getCode();

    String getMsg();

    T getData();

}
