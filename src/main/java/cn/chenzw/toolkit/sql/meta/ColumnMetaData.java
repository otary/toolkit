package cn.chenzw.toolkit.sql.meta;

import lombok.Data;

/**
 * 列元数据
 *
 * @author chenzw
 * @since 1.0.4
 */
@Data
public class ColumnMetaData {

    /**
     * 字段名称
     */
    private String columnName;

    /**
     * 字段类型名称
     */
    private String dataType;

    /**
     * 字段大小
     */
    private Integer columnSize;


    /**
     * 备注
     */
    private String remarks;


    /**
     * 是否为主键
     */
    private Boolean primaryKey = false;

    /**
     * 是否为外键
     */
    private Boolean foreignKey = false;

    /**
     * 是否允许为空
     */
    private Boolean nullable = true;
}
