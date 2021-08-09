package cn.chenzw.toolkit.sql.mysql.meta;

import cn.chenzw.toolkit.sql.core.meta.ColumnMetaData;
import cn.chenzw.toolkit.sql.core.meta.SqlMetaData;
import cn.chenzw.toolkit.sql.core.meta.TableMetaData;

import java.util.List;

/**
 * @author chenzw
 * @since 1.0.3
 */
public class MySqlSqlMetaData implements SqlMetaData {

    private MySqlTableMetaData tableMetaData;

    private List<MySqlColumnMetaData> columnMetaDatas;


    @Override
    public TableMetaData getTableMetaData() {
        return tableMetaData;
    }

    @Override
    public List<? extends ColumnMetaData> getColumnMetaDatas() {
        return columnMetaDatas;
    }

    @Override
    public void setTableMetaData(TableMetaData tableMetaData) {
        this.tableMetaData = (MySqlTableMetaData) tableMetaData;
    }

    @Override
    public void setColumnMetaDatas(List<? extends ColumnMetaData> columnMetaDatas) {
        this.columnMetaDatas = (List<MySqlColumnMetaData>) columnMetaDatas;
    }

    @Override
    public String toString() {
        return "MySqlSqlMetaData{" +
                "tableMetaData=" + tableMetaData +
                ", columnMetaDatas=" + columnMetaDatas +
                '}';
    }
}
