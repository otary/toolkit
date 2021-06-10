package cn.chenzw.toolkit.sql.core.parser;


import cn.chenzw.toolkit.sql.core.context.SqlParserContext;
import cn.chenzw.toolkit.sql.core.meta.SqlMetaData;
import cn.chenzw.toolkit.sql.exception.SqlParseException;

/**
 * SQL解析
 *
 * @author chenzw
 */
public interface SqlParser {

    /**
     * @param parserContext
     * @return
     * @throws SqlParseException
     */
    SqlMetaData parse(SqlParserContext parserContext) throws SqlParseException;

}
