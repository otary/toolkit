package cn.chenzw.toolkit.http.entity;

import java.io.Serializable;
import java.util.List;

/**
 * 分页响应对象
 *
 * @param <T>
 */
public interface PR<T> extends Serializable {

    /**
     * 获取总记录数
     *
     * @return
     */
    Integer getTotal();

    /**
     * 获取总页数
     *
     * @return
     */
    Integer getPages();

    /**
     * 获取每页条数
     *
     * @return
     */
    Integer getLimit();

    /**
     * 获取当前页码
     *
     * @return
     */
    Integer getOffset();

    /**
     * 获取数据列表
     *
     * @return
     */
    List<T> getData();
}
