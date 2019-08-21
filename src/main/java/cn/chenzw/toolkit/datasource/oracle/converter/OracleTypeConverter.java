package cn.chenzw.toolkit.datasource.oracle.converter;

import cn.chenzw.toolkit.datasource.core.converter.AbstractTypeConverter;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author chenzw
 */
public class OracleTypeConverter extends AbstractTypeConverter {

    private static OracleTypeConverter oracleTypeConverter = new OracleTypeConverter();
    private static Map<String, Class<?>> types = new HashMap<>();

    static {
        types.put("CHAR", String.class);
        types.put("VARCHAR", String.class);
        types.put("VARCHAR2", String.class);
        types.put("LONG", String.class);

        types.put("NUMBER", Long.class);
        types.put("LONGRAW", Byte[].class);

        types.put("DATE", Date.class);
        types.put("TIMESTAMP", Date.class);
        types.put("TIMESTAMP WITH LOCAL TIME ZONE", Date.class);
        types.put("TIMESTAMP WITH TIME ZONE", Date.class);
    }

    private OracleTypeConverter() {
    }

    public static OracleTypeConverter getInstance() {
        return oracleTypeConverter;
    }

    @Override
    public Map<String, Class<?>> getTypes() {
        return types;
    }
}
