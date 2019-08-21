package cn.chenzw.toolkit.datasource.entity;

import java.util.Collections;
import java.util.List;

/**
 * 表信息封装
 * @author chenzw
 */
public class TableDefinition {

    private String tableName;
    private String remarks;

    private List<ColumnDefinition> columnDefinitions = Collections.emptyList();

    public TableDefinition(String tableName, String remarks, List<ColumnDefinition> columnDefinitions) {
        this.columnDefinitions = columnDefinitions;
        this.tableName = tableName;
        this.remarks = remarks;
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

    @Override
    public String toString() {
        return "TableDefinition{" + "tableName='" + tableName + '\'' + ", remarks='" + remarks + '\''
                + ", columnDefinitions=" + columnDefinitions + '}';
    }
}