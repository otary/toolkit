package cn.chenzw.toolkit.core.entity;

import java.io.Serializable;

/**
 * API响应抽象对象
 *
 * @author chenzw
 */
public interface AR<T> extends Serializable {

    boolean isSuccess();

    String getMsg();

    String getDesc();

    Object getExt();


}
