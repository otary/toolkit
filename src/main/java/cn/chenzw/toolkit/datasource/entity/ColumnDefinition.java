package cn.chenzw.toolkit.datasource.entity;

/**
 * 列信息封装
 * @author chenzw
 */
public class ColumnDefinition {

    /**
     *字段名称
     */
    private String columnName;

    /**
     *字段类型名称
     */
    private String typeName;

    /**
     *字段大小
     */
    private String columnSize;

    /**
     *字段备注
     */
    private String remarks;

    /**
     *是否为主键
     */
    private Boolean parmaryKey = false;

    /**
     * 是否为外键
     */
    private Boolean foreignKey = false;

    /**
     * 是否允许为空
     */
    private Boolean nullable = false;

    /**
     * 默认值
     */
    private String columnDef;

    /**
     *小数部分的位数
     */
    private String decimalDigits;

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getColumnSize() {
        return columnSize;
    }

    public void setColumnSize(String columnSize) {
        this.columnSize = columnSize;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public Boolean getParmaryKey() {
        return parmaryKey;
    }

    public void setParmaryKey(Boolean parmaryKey) {
        this.parmaryKey = parmaryKey;
    }

    public Boolean getForeignKey() {
        return foreignKey;
    }

    public void setForeignKey(Boolean foreignKey) {
        this.foreignKey = foreignKey;
    }

    public Boolean getNullable() {
        return nullable;
    }

    public void setNullable(Boolean nullable) {
        this.nullable = nullable;
    }

    public String getColumnDef() {
        return columnDef;
    }

    public void setColumnDef(String columnDef) {
        this.columnDef = columnDef;
    }

    public String getDecimalDigits() {
        return decimalDigits;
    }

    public void setDecimalDigits(String decimalDigits) {
        this.decimalDigits = decimalDigits;
    }
}
