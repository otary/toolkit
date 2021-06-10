package cn.chenzw.toolkit.sql.core.meta;

import com.sun.org.apache.xpath.internal.operations.Bool;
import lombok.Data;

/**
 * 列元数据
 *
 * @author chenzw
 * @since 1.0.4
 */
public interface ColumnMetaData {

    /**
     * 字段名称
     */
    String getColumnName();

    /**
     * 字段类型名称
     */
    String getDataType();

    /**
     * 字段大小
     */
    Integer getColumnSize();

    /**
     * 备注
     */
    String getRemarks();


    /**
     * 是否为主键
     */
    Boolean getPrimaryKey();

    /**
     * 是否为外键
     */
    Boolean getForeignKey();


    /**
     * 是否允许为空
     */
    Boolean getNullable();

}
