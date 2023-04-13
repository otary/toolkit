package cn.chenzw.toolkit.db.sql.parser.mysql;

import cn.chenzw.toolkit.db.sql.exception.SqlParseException;
import cn.chenzw.toolkit.db.sql.metadata.CreateTableSQL;
import cn.chenzw.toolkit.db.sql.parser.CreateTableSQLParser;
import cn.chenzw.toolkit.db.sql.parser.SQLParser;

/**
 * Mysql SQL解析器
 *
 * @author chenzw
 */
public class MySqlSQLParser implements SQLParser {

    private CreateTableSQLParser createTableSQLParser;

    public MySqlSQLParser() {
        this.createTableSQLParser = new MySqlCreateTableSQLParser();
    }

    @Override
    public CreateTableSQL parseCreateTableSql(String createTableSql) throws SqlParseException {
        return this.createTableSQLParser.parse(createTableSql);
    }
}
