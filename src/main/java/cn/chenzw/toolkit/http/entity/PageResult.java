package cn.chenzw.toolkit.http.entity;

import java.util.List;

/**
 * 分页响应对象
 *
 * @param <T>
 * @author chenzw
 */
public class PageResult<T> implements PR<T> {

    private Integer total;
    private Integer pages;
    private Integer limit;
    private Integer offset;
    private List<T> data;

    public PageResult(Integer total, Integer offset, Integer limit, List<T> data) {
        this.total = total;
        this.offset = offset;
        this.limit = limit;
        this.data = data;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public void setPages(Integer pages) {
        this.pages = pages;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public void setOffset(Integer offset) {
        this.offset = offset;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

    @Override
    public Integer getTotal() {
        return total;
    }

    @Override
    public Integer getPages() {
        return pages;
    }

    @Override
    public Integer getLimit() {
        return limit;
    }

    @Override
    public Integer getOffset() {
        return offset;
    }

    @Override
    public List<T> getData() {
        return data;
    }
}
