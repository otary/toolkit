package cn.chenzw.toolkit.core.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * 分页参数
 * @author chenzw
 */
@Data
public class PageParam implements Serializable {

    public PageParam() {

    }

    public PageParam(Integer pageNum, Integer pageSize) {
        this.pageNum = pageNum;
        this.pageSize = pageSize;
    }

    public PageParam(Integer pageNum, Integer pageSize, String orderBy) {
        this(pageNum, pageSize);
        this.orderBy = orderBy;
    }

    public void withDefault(int pageNum, int pageSize) {
        if(this.pageNum == null) {
            this.pageNum = pageNum;
        }
        if (this.pageSize == null) {
            this.pageSize = pageSize;
        }
    }

    private static final long serialVersionId = 1L;

    private Integer pageNum;

    private Integer pageSize;

    private String orderBy;


}
