package cn.chenzw.toolkit.datasource.core.factory;

import cn.chenzw.toolkit.datasource.core.builder.AbstractTableDefinitionBuilder;
import cn.chenzw.toolkit.datasource.entity.TableDefinition;
import cn.chenzw.toolkit.datasource.oracle.builder.OracleTableDefinitionBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.sql.DataSource;
import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author chenzw
 */
public class TableDefinitionFactory {

    private static final Logger logger = LoggerFactory.getLogger(TableDefinitionFactory.class);

    private static Map<String, Class<?>> DRIVER_MAPPING = new HashMap<>();

    static {
        DRIVER_MAPPING.put("Oracle JDBC driver", OracleTableDefinitionBuilder.class);
    }


    private TableDefinitionFactory() {
    }


    public static TableDefinition create(DataSource dataSource, String tableName)
            throws SQLException, InstantiationException {
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
            String driverName = connection.getMetaData().getDriverName();
            if (DRIVER_MAPPING.containsKey(driverName)) {
                Class<?> builderClass = DRIVER_MAPPING.get(driverName);
                try {
                    AbstractTableDefinitionBuilder tableDefinitionBuilder = (AbstractTableDefinitionBuilder) builderClass
                            .getConstructor(Connection.class, String.class).newInstance(connection, tableName);
                    return tableDefinitionBuilder.build();
                } catch (IllegalAccessException | NoSuchMethodException | InstantiationException | InvocationTargetException e) {
                    throw new InstantiationException("Instantiation TableDefinitionBuilder fail!");
                }
            } else {
                throw new IllegalArgumentException("Missing found driver mapping of [" + driverName + "]");
            }
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
    }

}
