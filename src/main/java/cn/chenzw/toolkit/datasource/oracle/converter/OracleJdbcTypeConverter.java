package cn.chenzw.toolkit.datasource.oracle.converter;

import cn.chenzw.toolkit.datasource.core.converter.AbstractJdbcTypeConverter;
import cn.chenzw.toolkit.datasource.core.converter.JdbcTypeMapping;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author chenzw
 */
public class OracleJdbcTypeConverter extends AbstractJdbcTypeConverter {

    private static OracleJdbcTypeConverter oracleTypeConverter = new OracleJdbcTypeConverter();
    private static List<JdbcTypeMapping> jdbcTypeMappings = new ArrayList<>();

    static {

        jdbcTypeMappings.add(new JdbcTypeMapping("CHAR", String.class));
        jdbcTypeMappings.add(new JdbcTypeMapping("VARCHAR", String.class));
        jdbcTypeMappings.add(new JdbcTypeMapping("VARCHAR2", String.class));
        jdbcTypeMappings.add(new JdbcTypeMapping("LONG", String.class));
        jdbcTypeMappings.add(new JdbcTypeMapping("CLOB", String.class));
        jdbcTypeMappings.add(new JdbcTypeMapping("RAW", String.class));

        jdbcTypeMappings.add(new JdbcTypeMapping("NUMBER", Long.class));
        jdbcTypeMappings.add(new JdbcTypeMapping("LONGRAW", Byte[].class));

        jdbcTypeMappings.add(new JdbcTypeMapping("DATE", Date.class));
        jdbcTypeMappings.add(new JdbcTypeMapping("TIMESTAMP", Date.class));
        jdbcTypeMappings.add(new JdbcTypeMapping("TIMESTAMP WITH LOCAL TIME ZONE", Date.class));
        jdbcTypeMappings.add(new JdbcTypeMapping("TIMESTAMP WITH TIME ZONE", Date.class));

    }

    private OracleJdbcTypeConverter() {
    }

    public static OracleJdbcTypeConverter getInstance() {
        return oracleTypeConverter;
    }


    @Override
    public List<JdbcTypeMapping> getTypeMappings() {
        return jdbcTypeMappings;
    }
}
