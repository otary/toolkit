package cn.chenzw.toolkit.datasource.mysql.builder;

import cn.chenzw.toolkit.datasource.core.builder.AbstractColumnDefinitionBuilder;
import cn.chenzw.toolkit.datasource.core.converter.JdbcTypeConverter;
import cn.chenzw.toolkit.datasource.mysql.converter.MySqlJdbcTypeConverter;

import java.sql.Connection;

/**
 * @author chenzw
 */
public class MySqlColumnDefinitionBuilder extends AbstractColumnDefinitionBuilder {

    public MySqlColumnDefinitionBuilder(Connection connection, String tableName) {
        super(connection, tableName);
    }

    @Override
    protected JdbcTypeConverter getTypeConverter() {
        return MySqlJdbcTypeConverter.getInstance();
    }
}
