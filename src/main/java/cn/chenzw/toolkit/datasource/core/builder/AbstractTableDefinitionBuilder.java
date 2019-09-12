package cn.chenzw.toolkit.datasource.core.builder;

import cn.chenzw.toolkit.commons.StringExtUtils;
import cn.chenzw.toolkit.datasource.constants.DbConstants;
import cn.chenzw.toolkit.datasource.entity.TableDefinition;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 表定义构建器
 *
 * @author chenzw
 */
public abstract class AbstractTableDefinitionBuilder {

    private Connection connection;
    private String tableName;

    public AbstractTableDefinitionBuilder(Connection connection, String tableName) {
        this.connection = connection;
        this.tableName = tableName;
    }

    protected String getTableName(ResultSet rs) throws SQLException {
        return rs.getString(DbConstants.RS_TABLE_NAME);
    }

    protected String getRemarks(ResultSet rs) throws SQLException {
        return rs.getString(DbConstants.RS_TABLE_NAME);
    }

    protected abstract AbstractColumnDefinitionBuilder getColumnDefinitionBuilder(Connection connection,
            String tableName);

    public TableDefinition build() throws SQLException {
        ResultSet tableRs = connection.getMetaData().getTables(null, null, tableName, new String[]{"TABLE"});

        if (tableRs.next()) {
            String _tableName = getTableName(tableRs);
            return new TableDefinition(_tableName, StringExtUtils.toPascal(_tableName), getRemarks(tableRs),
                    getColumnDefinitionBuilder(connection, this.tableName).build());
        }
        return null;
    }
}
