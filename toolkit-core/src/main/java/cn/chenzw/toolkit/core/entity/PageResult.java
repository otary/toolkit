package cn.chenzw.toolkit.core.entity;

import java.util.List;

/**
 * 分页响应对象
 *
 * @param <T>
 * @author chenzw
 */
public class PageResult<T> implements PR<T> {

    private final static long serialVersionId = 1L;

    /**
     * 总数
     */
    private Long total;

    /**
     * 总页数
     */
    private Integer pages;

    /**
     * 页面大小
     */
    private Integer pageSize;

    /**
     * 页码，从1开始
     */
    private Integer pageNum;

    /**
     * 数据
     */
    private List<T> data;

    public PageResult(Long total, Integer pageNum, Integer pageSize, Integer pages, List<T> data) {
        this.total = total;
        this.pageNum = pageNum;
        this.pageSize = pageSize;
        this.pages = pages;
        this.data = data;
    }

    public PageResult(com.github.pagehelper.Page page) {
        this.total = page.getTotal();
        this.pageSize = page.getPageSize();
        this.pageNum = page.getPageNum();
        this.pages = page.getPages();
        this.data = (List<T>) page.getResult();
    }

    public static <T> PageResult<T> of(com.github.pagehelper.Page page) {
        return new PageResult(page.getTotal(), page.getPageNum(), page.getPageSize(), page.getPages(), page.getResult());
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public void setPages(Integer pages) {
        this.pages = pages;
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
    public List<T> getData() {
        return data;
    }

    @Override
    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    @Override
    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("PageResult{");
        sb.append("total=").append(total);
        sb.append(", pages=").append(pages);
        sb.append(", pageSize=").append(pageSize);
        sb.append(", pageNum=").append(pageNum);
        sb.append(", data=").append(data);
        sb.append('}');
        return sb.toString();
    }
}
