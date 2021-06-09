package cn.chenzw.toolkit.datasource.entity;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 表信息封装
 *
 * @author chenzw
 */
public class TableDefinition {

    private String tableName;
    private String remarks;
    private String pascalName;

    private Set<String> javaTypes = new HashSet<>();
    private List<ColumnDefinition> columnDefinitions;

    public TableDefinition(String tableName, String pascalName, String remarks, List<ColumnDefinition> columnDefinitions) {
        this.columnDefinitions = columnDefinitions;
        this.tableName = tableName;
        this.pascalName = pascalName;
        this.remarks = remarks;

        for (ColumnDefinition columnDefinition : columnDefinitions) {
            javaTypes.add(columnDefinition.getJavaType().getCanonicalName());
        }
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public List<ColumnDefinition> getColumnDefinitions() {
        return columnDefinitions;
    }

    public void setColumnDefinitions(List<ColumnDefinition> columnDefinitions) {
        this.columnDefinitions = columnDefinitions;
    }

    public String getPascalName() {
        return pascalName;
    }

    public void setPascalName(String pascalName) {
        this.pascalName = pascalName;
    }

    public Set<String> getJavaTypes() {
        return javaTypes;
    }

    public void setJavaTypes(Set<String> javaTypes) {
        this.javaTypes = javaTypes;
    }

    @Override
    public String toString() {
        return "TableDefinition{" +
                "tableName='" + tableName + '\'' +
                ", remarks='" + remarks + '\'' +
                ", pascalName='" + pascalName + '\'' +
                ", javaTypes=" + javaTypes +
                ", columnDefinitions=" + columnDefinitions +
                '}';
    }
}
