package cn.chenzw.toolkit.sql.parser;

import cn.chenzw.toolkit.sql.meta.ColumnMetaData;
import cn.chenzw.toolkit.sql.meta.SqlMetaData;
import cn.chenzw.toolkit.sql.meta.TableMetaData;


/**
 * @author chenzw
 * @since 1.0.3
 */
public class MySQLSqlParser extends AbstractSqlParser {




    @Override
    public void preProcess() {

    }

    @Override
    public SqlMetaData parse() {
        return null;
    }

    @Override
    public TableMetaData parseTableMeta() {
        return null;
    }

    @Override
    public ColumnMetaData parseColumnMeta() {
        return null;
    }

    @Override
    public void postProcess() {

    }
}
