package cn.chenzw.toolkit.db.sql.parser.mysql.metadata;

import cn.chenzw.toolkit.db.sql.metadata.Column;
import lombok.Setter;

import java.util.Map;

/**
 * MySql列元数据
 *
 * @author chenzw
 * @since 1.0.3
 */
@Setter
public class MySqlColumn implements Column {

    private String name;

    private String dataType;

    private Integer size;

    private Integer decimalDigits;

    private String remarks;

    private String defaultValue;

    private Boolean primaryKey = Boolean.FALSE;

    private Boolean foreignKey;

    private Boolean nullable = Boolean.TRUE;

    private Boolean autoIncrement = Boolean.FALSE;

    /**
     * 扩展属性
     */
    private Map<String, Object> ext;


    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getDataType() {
        return dataType;
    }

    @Override
    public Integer getSize() {
        return size;
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

    @Override
    public String getDefaultValue() {
        return defaultValue;
    }
}
