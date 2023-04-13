package cn.chenzw.toolkit.db.sql.parser.mysql.metadata;

import cn.chenzw.toolkit.db.sql.metadata.Column;
import cn.chenzw.toolkit.db.sql.metadata.CreateTableSQL;
import cn.chenzw.toolkit.db.sql.metadata.Table;

import java.util.List;

/**
 * @author chenzw
 */
public class MySqlCreateTableSQL implements CreateTableSQL {

    private Table table;

    private List<? extends Column> columns;

    public MySqlCreateTableSQL(Table table, List<? extends Column> columns) {
        this.table = table;
        this.columns = columns;
    }

    @Override
    public Table getTableMetadata() {
        return this.table;
    }

    @Override
    public List<? extends Column> getColumnsMetadata() {
        return this.columns;
    }
}
