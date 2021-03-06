package cn.chenzw.toolkit.datasource.oracle.builder;

import cn.chenzw.toolkit.datasource.constants.DbConstants;
import cn.chenzw.toolkit.datasource.core.builder.AbstractColumnDefinitionBuilder;
import cn.chenzw.toolkit.datasource.core.converter.JdbcTypeConverter;
import cn.chenzw.toolkit.datasource.oracle.converter.OracleJdbcTypeConverter;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author chenzw
 */
public class OracleColumnDefinitionBuilder extends AbstractColumnDefinitionBuilder {

    public OracleColumnDefinitionBuilder(Connection connection, String tableName) {
        super(connection, tableName);
    }

    @Override
    protected Boolean isNullable(ResultSet rs) throws SQLException {
        return rs.getBoolean(DbConstants.RS_NULLABLE);
    }

    @Override
    protected JdbcTypeConverter getTypeConverter() {
        return OracleJdbcTypeConverter.getInstance();
    }
}
