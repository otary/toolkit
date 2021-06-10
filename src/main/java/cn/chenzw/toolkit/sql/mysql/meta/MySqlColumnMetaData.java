package cn.chenzw.toolkit.sql.mysql.meta;

import cn.chenzw.toolkit.sql.core.meta.ColumnMetaData;
import lombok.Data;

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

    private String remarks;

    private String defaultValue;

    private Boolean primaryKey;

    private Boolean foreignKey;

    private Boolean nullable;

    /**
     * 默认值
     */
    private String columnDef;


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


}
