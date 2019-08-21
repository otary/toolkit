package cn.chenzw.toolkit.datasource.mysql.converter;

import cn.chenzw.toolkit.datasource.core.converter.AbstractTypeConverter;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author chenzw
 */
public class MySqlTypeConverter extends AbstractTypeConverter {

    private static MySqlTypeConverter mySqlTypeConverter = new MySqlTypeConverter();
    private static Map<String, Class<?>> types = new HashMap<>();

    static {
        types.put("CHAR", String.class);
        types.put("VARCHAR", String.class);

        types.put("BIGINT", Long.class);
        types.put("INT", Integer.class);
        types.put("INT UNSIGNED", Integer.class);
        types.put("BIGINT UNSIGNED", Long.class);
        types.put("TINYINT UNSIGNED", Long.class);
        types.put("DECIMAL", Double.class);
        types.put("TEXT", String.class);

        types.put("DATETIME", Date.class);
        types.put("TIMESTAMP", Date.class);

        types.put("TINYINT", Short.class);
        types.put("DECIMAL UNSIGNED", Double.class);
        types.put("SMALLINT", Short.class);
        types.put("BIT", Short.class);

    }

    private MySqlTypeConverter() {
    }

    public static MySqlTypeConverter getInstance() {
        return mySqlTypeConverter;
    }

    @Override
    public Map<String, Class<?>> getTypes() {
        return types;
    }


}
