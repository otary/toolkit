package cn.chenzw.toolkit.datasource.oracle.builder;

import cn.chenzw.toolkit.datasource.core.AbstractColumnDefinitionBuilder;
import cn.chenzw.toolkit.datasource.core.AbstractTableDefinitionBuilder;
import oracle.jdbc.driver.OracleConnection;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * @author chenzw
 */
public class OracleTableDefinitionBuilder extends AbstractTableDefinitionBuilder {

    private Connection connection;
    private String tableName;

    public OracleTableDefinitionBuilder(Connection connection, String tableName) throws SQLException {
        super(connection, tableName);

        this.connection = connection;
        this.tableName = tableName;



        org.apache.commons.dbcp2.DelegatingConnection del = new org.apache.commons.dbcp2.DelegatingConnection(connection);
        connection = del.getInnermostDelegate();
        //if (connection instanceof OracleConnection) {
        System.out.println(connection.getClass());
        ((OracleConnection) connection).setRemarksReporting(true);
        //  }
       /* if (connection.isWrapperFor(OracleConnection.class)) {
            System.out.println(connection);
            this.connection = connection.unwrap(OracleConnection.class);
            System.out.println(connection);

            ((OracleConnection) connection).setRemarksReporting(true);
        }*/
    }

    @Override
    protected AbstractColumnDefinitionBuilder getColumnDefinitionBuilder() {
        return new OracleColumnDefinitionBuilder(connection, tableName);
    }
}
