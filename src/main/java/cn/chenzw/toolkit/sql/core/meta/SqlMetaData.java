package cn.chenzw.toolkit.sql.core.meta;

import java.util.List;

/**
 * @author chenzw
 * @since 1.0.3
 */
public interface SqlMetaData {

    TableMetaData getTableMetaData();

    List<? extends ColumnMetaData> getColumnMetaDatas();

    void setTableMetaData(TableMetaData tableMetaData);

    void setColumnMetaDatas(List<? extends ColumnMetaData> columnMetaDatas);
}
