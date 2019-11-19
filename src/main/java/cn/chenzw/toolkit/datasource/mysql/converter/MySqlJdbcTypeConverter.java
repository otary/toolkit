package cn.chenzw.toolkit.datasource.mysql.converter;

import cn.chenzw.toolkit.datasource.core.converter.AbstractJdbcTypeConverter;
import cn.chenzw.toolkit.datasource.core.converter.JdbcTypeMapping;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author chenzw
 */
public class MySqlJdbcTypeConverter extends AbstractJdbcTypeConverter {

    private static MySqlJdbcTypeConverter mySqlTypeConverter = new MySqlJdbcTypeConverter();
    private static List<JdbcTypeMapping> jdbcTypeMappings = new ArrayList<>();

    static {
        jdbcTypeMappings.add(new JdbcTypeMapping("CHAR", String.class));
        jdbcTypeMappings.add(new JdbcTypeMapping("VARCHAR", String.class));
        jdbcTypeMappings.add(new JdbcTypeMapping("TEXT", String.class));
        jdbcTypeMappings.add(new JdbcTypeMapping("NVARCHAR", String.class));
        jdbcTypeMappings.add(new JdbcTypeMapping("NCHAR", String.class));

        jdbcTypeMappings.add(new JdbcTypeMapping("BIGINT", Long.class));
        jdbcTypeMappings.add(new JdbcTypeMapping("INT", Integer.class));
        jdbcTypeMappings.add(new JdbcTypeMapping("INT UNSIGNED", Integer.class));
        jdbcTypeMappings.add(new JdbcTypeMapping("BIGINT UNSIGNED", Long.class));
        jdbcTypeMappings.add(new JdbcTypeMapping("TINYINT UNSIGNED", Long.class));
        jdbcTypeMappings.add(new JdbcTypeMapping("SMALL INT", Integer.class));


        jdbcTypeMappings.add(new JdbcTypeMapping("DECIMAL", Double.class));
        jdbcTypeMappings.add(new JdbcTypeMapping("DECIMAL", BigDecimal.class));
        jdbcTypeMappings.add(new JdbcTypeMapping("DECIMAL UNSIGNED", Double.class));
        jdbcTypeMappings.add(new JdbcTypeMapping("DOUBLE", Double.class));
        jdbcTypeMappings.add(new JdbcTypeMapping("FLOAT", Float.class));
        jdbcTypeMappings.add(new JdbcTypeMapping("REAL", Float.class));
        jdbcTypeMappings.add(new JdbcTypeMapping("MONEY", BigDecimal.class));
        jdbcTypeMappings.add(new JdbcTypeMapping("SMALL MONEY", BigDecimal.class));
        jdbcTypeMappings.add(new JdbcTypeMapping("NUMERIC", BigDecimal.class));


        jdbcTypeMappings.add(new JdbcTypeMapping("DATETIME", Date.class));
        jdbcTypeMappings.add(new JdbcTypeMapping("TIMESTAMP", Date.class));
        jdbcTypeMappings.add(new JdbcTypeMapping("SMALL DATETIME", Date.class));

        jdbcTypeMappings.add(new JdbcTypeMapping("TINYINT", Short.class));
        jdbcTypeMappings.add(new JdbcTypeMapping("SMALLINT", Short.class));
        jdbcTypeMappings.add(new JdbcTypeMapping("BIT", Boolean.class));

        jdbcTypeMappings.add(new JdbcTypeMapping("BINARY", Byte[].class));
        jdbcTypeMappings.add(new JdbcTypeMapping("BLOB", Byte[].class));
        jdbcTypeMappings.add(new JdbcTypeMapping("IMAGE", Byte[].class));
    }

    private MySqlJdbcTypeConverter() {
    }

    public static MySqlJdbcTypeConverter getInstance() {
        return mySqlTypeConverter;
    }


    @Override
    public List<JdbcTypeMapping> getTypeMappings() {
        return jdbcTypeMappings;
    }
}
