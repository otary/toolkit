package cn.chenzw.toolkit.datasource.mysql.builder;

import cn.chenzw.toolkit.datasource.core.builder.AbstractColumnDefinitionBuilder;
import cn.chenzw.toolkit.datasource.core.builder.AbstractTableDefinitionBuilder;

import java.sql.Connection;

public class MySqlTableDefinitionBuilder extends AbstractTableDefinitionBuilder {

    public MySqlTableDefinitionBuilder(Connection connection, String tableName) {
        super(connection, tableName);
    }

    @Override
    protected AbstractColumnDefinitionBuilder getColumnDefinitionBuilder(Connection connection, String tableName) {
        return new MySqlColumnDefinitionBuilder(connection, tableName);
    }

}
