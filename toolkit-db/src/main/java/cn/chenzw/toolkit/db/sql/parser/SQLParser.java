package cn.chenzw.toolkit.db.sql.parser;

import cn.chenzw.toolkit.db.sql.exception.SqlParseException;
import cn.chenzw.toolkit.db.sql.metadata.CreateTableSQL;

/**
 * SQL解析
 *
 * @author chenzw
 */
public interface SQLParser {

    CreateTableSQL parseCreateTableSql(String createTableSql) throws SqlParseException;

}
