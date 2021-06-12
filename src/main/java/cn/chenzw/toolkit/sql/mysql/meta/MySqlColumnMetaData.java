package cn.chenzw.toolkit.sql.mysql.meta;

import cn.chenzw.toolkit.sql.core.meta.ColumnMetaData;
import lombok.Data;

import java.util.Map;

/**
 * MySql列元数据
 *
 * @author chenzw
 * @since 1.0.3
 */
@Data
public class MySqlColumnMetaData implements ColumnMetaData {

    private String columnName;

    private String dataType;

    private Integer columnSize;

    private Integer decimalDigits;

    private String remarks;

    private String defaultValue;

    private Boolean primaryKey;

    private Boolean foreignKey;

    private Boolean nullable;

    private Boolean autoIncrement;

    /**
     * 默认值
     */
    private String columnDef;

    /**
     * 扩展属性
     */
    private Map<String, Object> ext;


    @Override
    public String getColumnName() {
        return columnName;
    }

    @Override
    public String getDataType() {
        return dataType;
    }

    @Override
    public Integer getColumnSize() {
        return columnSize;
    }

    @Override
    public Integer getDecimalDigits() {
        return decimalDigits;
    }

    @Override
    public String getRemarks() {
        return remarks;
    }

    @Override
    public Boolean getPrimaryKey() {
        return primaryKey;
    }

    @Override
    public Boolean getForeignKey() {
        return foreignKey;
    }

    @Override
    public Boolean getNullable() {
        return nullable;
    }

    @Override
    public Map<String, Object> getExt() {
        return ext;
    }

    @Override
    public Boolean getAutoIncrement() {
        return autoIncrement;
    }
}
