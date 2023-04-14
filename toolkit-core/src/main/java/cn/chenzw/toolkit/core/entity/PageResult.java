package cn.chenzw.toolkit.core.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.List;

/**
 * 分页响应对象
 *
 * @param <T>
 * @author chenzw
 */
@Data
@SuperBuilder
@NoArgsConstructor
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
    private List<T> payload;


    public PageResult(com.github.pagehelper.Page page) {
        this.total = page.getTotal();
        this.pageSize = page.getPageSize();
        this.pageNum = page.getPageNum();
        this.pages = page.getPages();
        this.payload = (List<T>) page.getResult();
    }

    public static <T> PageResult<T> of(com.github.pagehelper.Page page) {
        return new PageResult<T>(page);
    }

}
