package cn.chenzw.toolkit.datasource.oracle.builder;

import cn.chenzw.toolkit.commons.ClassExtUtils;
import cn.chenzw.toolkit.datasource.core.builder.AbstractColumnDefinitionBuilder;
import cn.chenzw.toolkit.datasource.core.builder.AbstractTableDefinitionBuilder;
import com.alibaba.druid.pool.DruidPooledConnection;
import oracle.jdbc.driver.OracleConnection;
import org.apache.commons.dbcp2.DelegatingConnection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * @author chenzw
 */
public class OracleTableDefinitionBuilder extends AbstractTableDefinitionBuilder {

    private static Logger logger = LoggerFactory.getLogger(OracleTableDefinitionBuilder.class);

    private static boolean commonsDbcp2Present = ClassExtUtils
            .isPresent("org.apache.commons.dbcp2.DelegatingConnection");

    private static boolean druidPresent = ClassExtUtils.isPresent("com.alibaba.druid.pool.DruidPooledConnection");

    public OracleTableDefinitionBuilder(Connection connection, String tableName) throws SQLException {
        super(connection, tableName);

        if (commonsDbcp2Present && connection.isWrapperFor(DelegatingConnection.class)) {
            ((OracleConnection) (((DelegatingConnection) connection).getInnermostDelegate())).setRemarksReporting(true);
        } else if (druidPresent && connection.isWrapperFor(DruidPooledConnection.class)) {
            ((OracleConnection) (((DruidPooledConnection) connection).getConnection())).setRemarksReporting(true);
        } else {
            // @TODO
            logger.warn("Connection [" + connection + "] can't case to OracleConnection!");
        }
    }

    @Override
    protected AbstractColumnDefinitionBuilder getColumnDefinitionBuilder(Connection connection, String tableName) {
        return new OracleColumnDefinitionBuilder(connection, tableName);
    }

}
