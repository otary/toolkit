package cn.chenzw.toolkit.core.entity;

import java.io.Serializable;

/**
 * @author chenzw
 * @param <T>
 */
public interface R<T> extends Serializable {

    String getMsgId();

    Integer getCode();

    String getMsg();

    T getData();

    String getDetail();

    Object getExt();

}