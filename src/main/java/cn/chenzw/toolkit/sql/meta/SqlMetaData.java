package cn.chenzw.toolkit.sql.meta;

import lombok.Data;

import java.util.List;

/**
 * @author chenzw
 * @since 1.0.4
 */
@Data
public class SqlMetaData {

    private TableMetaData tableMetaData;

    private List<ColumnMetaData> columnMetaDatas;
}
