package cn.chenzw.toolkit.datasource.entity;

/**
 * 列信息封装
 *
 * @author chenzw
 */
public class ColumnDefinition {

    /**
     * 字段名称
     */
    private String columnName;

    /**
     * 驼峰命名
     */
    private String camelCaseName;

    /**
     * 字段类型名称
     */
    private String typeName;

    /**
     * 字段大小
     */
    private Integer columnSize;

    /**
     * 字段备注
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
    private Boolean nullable = false;

    /**
     * 默认值
     */
    private String columnDef;

    /**
     * 小数部分的位数
     */
    private Integer decimalDigits;

    /**
     * 对应的Java类
     */
    private Class<?> javaType;

    public ColumnDefinition(String columnName, String camelCaseName, String typeName, Integer columnSize, Integer decimalDigits,
                            String remarks, Boolean primaryKey, Boolean foreignKey, Boolean nullable, String columnDef, Class<?> javaType) {
        this.columnName = columnName;
        this.camelCaseName = camelCaseName;
        this.typeName = typeName;
        this.columnSize = columnSize;
        this.remarks = remarks;
        this.primaryKey = primaryKey;
        this.foreignKey = foreignKey;
        this.nullable = nullable;
        this.columnDef = columnDef;
        this.decimalDigits = decimalDigits;
        this.javaType = javaType;
    }

    public Boolean getPrimaryKey() {
        return primaryKey;
    }

    public void setPrimaryKey(Boolean primaryKey) {
        this.primaryKey = primaryKey;
    }

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

    public Integer getColumnSize() {
        return columnSize;
    }

    public void setColumnSize(Integer columnSize) {
        this.columnSize = columnSize;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
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

    public Integer getDecimalDigits() {
        return decimalDigits;
    }

    public void setDecimalDigits(Integer decimalDigits) {
        this.decimalDigits = decimalDigits;
    }

    public Class<?> getJavaType() {
        return javaType;
    }

    public void setJavaType(Class<?> javaType) {
        this.javaType = javaType;
    }

    public String getCamelCaseName() {
        return camelCaseName;
    }

    public void setCamelCaseName(String camelCaseName) {
        this.camelCaseName = camelCaseName;
    }

    @Override
    public String toString() {
        return "ColumnDefinition{" +
                "columnName='" + columnName + '\'' +
                ", camelCaseName='" + camelCaseName + '\'' +
                ", typeName='" + typeName + '\'' +
                ", columnSize=" + columnSize +
                ", remarks='" + remarks + '\'' +
                ", primaryKey=" + primaryKey +
                ", foreignKey=" + foreignKey +
                ", nullable=" + nullable +
                ", columnDef='" + columnDef + '\'' +
                ", decimalDigits=" + decimalDigits +
                ", javaType=" + javaType +
                '}';
    }
}
