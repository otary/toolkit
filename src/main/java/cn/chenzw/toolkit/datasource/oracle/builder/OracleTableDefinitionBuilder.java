package cn.chenzw.toolkit.datasource.oracle.builder;

import cn.chenzw.toolkit.commons.ClassExtUtils;
import cn.chenzw.toolkit.datasource.core.builder.AbstractColumnDefinitionBuilder;
import cn.chenzw.toolkit.datasource.core.builder.AbstractTableDefinitionBuilder;
import oracle.jdbc.driver.OracleConnection;
import org.apache.commons.dbcp2.DelegatingConnection;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * @author chenzw
 */
public class OracleTableDefinitionBuilder extends AbstractTableDefinitionBuilder {

    private static boolean isCommonsDbcp2Present = ClassExtUtils
            .isPresent("org.apache.commons.dbcp2.DelegatingConnection");

    public OracleTableDefinitionBuilder(Connection connection, String tableName) throws SQLException {
        super(connection, tableName);

        if (isCommonsDbcp2Present) {
            if (connection.isWrapperFor(DelegatingConnection.class)) {
                ((OracleConnection) (((DelegatingConnection) connection).getInnermostDelegate()))
                        .setRemarksReporting(true);
            }
        }
    }

    @Override
    protected AbstractColumnDefinitionBuilder getColumnDefinitionBuilder(Connection connection, String tableName) {
        return new OracleColumnDefinitionBuilder(connection, tableName);
    }

}
