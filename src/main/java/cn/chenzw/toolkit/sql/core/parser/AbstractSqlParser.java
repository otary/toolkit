package cn.chenzw.toolkit.sql.core.parser;

import cn.chenzw.toolkit.sql.core.context.SqlParserContext;
import cn.chenzw.toolkit.sql.core.meta.ColumnMetaData;
import cn.chenzw.toolkit.sql.core.meta.SqlMetaData;
import cn.chenzw.toolkit.sql.core.meta.TableMetaData;
import cn.chenzw.toolkit.sql.exception.SqlParseException;

import java.util.List;

/**
 * @author chenzw
 * @since 1.0.3
 */
public abstract class AbstractSqlParser implements SqlParser {


    public SqlMetaData parse(SqlParserContext parserContext) throws SqlParseException {
        SqlMetaData sqlMetaData = this.createSqlMetaData();
        sqlMetaData.setTableMetaData(this.parseTableMeta(parserContext));
        sqlMetaData.setColumnMetaDatas(this.parseColumnMeta(parserContext));
        return sqlMetaData;
    }


    protected abstract TableMetaData parseTableMeta(SqlParserContext parserContext) throws SqlParseException;

    protected abstract List<? extends ColumnMetaData> parseColumnMeta(SqlParserContext parserContext) throws SqlParseException;

    protected abstract SqlMetaData createSqlMetaData();


}
