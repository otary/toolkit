package cn.chenzw.toolkit.core.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;
import java.util.List;

@Data
@SuperBuilder(toBuilder = true)
@NoArgsConstructor
public class ApiPageResult<T> implements PR<T> {

    public static final Long serialVersionUID = 1L;

    private boolean success = Boolean.FALSE;

    private String msg;

    private Long total;

    private Integer pages;

    private Integer pageSize;

    private Integer pageNum;

    private List<T> payload;

    private Object ext;

}
