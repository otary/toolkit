package cn.chenzw.toolkit.db.sql.metadata;

import java.util.Map;

/**
 * 列元数据
 * @author chenzw
 * @since 1.0.3
 */
public interface Column {

    /**
     * 字段名称
     */
    String getName();

    /**
     * 字段类型名称
     */
    String getDataType();

    /**
     * 字段大小
     */
    Integer getSize();

    /**
     * 小数位
     *
     * @return
     */
    Integer getDecimalDigits();

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

    /**
     * 是否自增
     *
     * @return
     */
    Boolean getAutoIncrement();

    /**
     * 默认值
     *
     * @return
     */
    String getDefaultValue();


    /**
     * 扩展属性
     *
     * @return
     */
    Map<String, Object> getExt();

    void setExt(Map<String, Object> extMap);
}
