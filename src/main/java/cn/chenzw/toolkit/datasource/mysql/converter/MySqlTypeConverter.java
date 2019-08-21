package cn.chenzw.toolkit.datasource.mysql.converter;

import cn.chenzw.toolkit.datasource.core.converter.AbstractTypeConverter;
import cn.chenzw.toolkit.datasource.core.converter.TypeMapping;

import java.util.*;

/**
 * @author chenzw
 */
public class MySqlTypeConverter extends AbstractTypeConverter {

    private static MySqlTypeConverter mySqlTypeConverter = new MySqlTypeConverter();
    private static List<TypeMapping> typeMappings = new ArrayList<>();

    static {
        typeMappings.add(new TypeMapping("CHAR", String.class));
        typeMappings.add(new TypeMapping("VARCHAR", String.class));
        typeMappings.add(new TypeMapping("TEXT", String.class));

        typeMappings.add(new TypeMapping("BIGINT", Long.class));
        typeMappings.add(new TypeMapping("INT", Integer.class));
        typeMappings.add(new TypeMapping("INT UNSIGNED", Integer.class));
        typeMappings.add(new TypeMapping("BIGINT UNSIGNED", Long.class));
        typeMappings.add(new TypeMapping("TINYINT UNSIGNED", Long.class));

        typeMappings.add(new TypeMapping("DECIMAL", Double.class));
        typeMappings.add(new TypeMapping("DECIMAL UNSIGNED", Double.class));

        typeMappings.add(new TypeMapping("DATETIME", Date.class));
        typeMappings.add(new TypeMapping("TIMESTAMP", Date.class));

        typeMappings.add(new TypeMapping("TINYINT", Short.class));
        typeMappings.add(new TypeMapping("SMALLINT", Short.class));
        typeMappings.add(new TypeMapping("BIT", Boolean.class));
    }

    private MySqlTypeConverter() {
    }

    public static MySqlTypeConverter getInstance() {
        return mySqlTypeConverter;
    }


    @Override
    public List<TypeMapping> getTypeMappings() {
        return typeMappings;
    }
}
