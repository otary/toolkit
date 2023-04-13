package cn.chenzw.toolkit.db.sql.parser;

import cn.chenzw.toolkit.db.sql.exception.SqlParseException;
import cn.chenzw.toolkit.db.sql.metadata.CreateTableSQL;

/**
 * @author chenzw
 */
public interface CreateTableSQLParser {

    CreateTableSQL parse(String createTableSql) throws SqlParseException;
}
