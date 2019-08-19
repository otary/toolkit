package cn.chenzw.toolkit.datasource.entity;

import java.util.Collections;
import java.util.List;

/**
 * 表信息封装
 * @author chenzw
 */
public class TableDefinition {

    List<ColumnDefinition> columnDefinitions = Collections.emptyList();
    private String tableName;
    private String remarks;

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
}
