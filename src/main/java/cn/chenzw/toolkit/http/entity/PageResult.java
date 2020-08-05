package cn.chenzw.toolkit.http.entity;

import com.github.pagehelper.Page;

import java.util.List;

/**
 * 分页响应对象
 *
 * @param <T>
 * @author chenzw
 */
public class PageResult<T> implements PR<T> {

    private Long total;
    private Integer pages;
    private Integer limit;
    private Integer offset;
    private List<T> data;

    public PageResult(Long total, Integer offset, Integer limit, Integer pages, List<T> data) {
        this.total = total;
        this.offset = offset;
        this.limit = limit;
        this.pages = pages;
        this.data = data;
    }

    public PageResult(Page page) {
        this.total = page.getTotal();
        this.limit = page.getPageSize();
        this.offset = page.getPageNum();
        this.pages = page.getPages();
        this.data = (List<T>) page.getResult();
    }

    public static <T> PageResult<T> of(Page page) {
        return new PageResult(page.getTotal(), page.getPageNum(), page.getPageSize(), page.getPages(), page.getResult());
    }

    public void setTotal(Long total) {
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
    public Long getTotal() {
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

    @Override
    public String toString() {
        return "PageResult{" +
                "total=" + total +
                ", pages=" + pages +
                ", limit=" + limit +
                ", offset=" + offset +
                ", data=" + data +
                '}';
    }
}
