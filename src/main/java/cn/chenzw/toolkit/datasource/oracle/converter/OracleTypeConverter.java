package cn.chenzw.toolkit.datasource.oracle.converter;

import cn.chenzw.toolkit.datasource.core.converter.AbstractTypeConverter;
import cn.chenzw.toolkit.datasource.core.converter.TypeMapping;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author chenzw
 */
public class OracleTypeConverter extends AbstractTypeConverter {

    private static OracleTypeConverter oracleTypeConverter = new OracleTypeConverter();
    private static List<TypeMapping> typeMappings = new ArrayList<>();

    static {

        typeMappings.add(new TypeMapping("CHAR", String.class));
        typeMappings.add(new TypeMapping("VARCHAR", String.class));
        typeMappings.add(new TypeMapping("VARCHAR2", String.class));
        typeMappings.add(new TypeMapping("LONG", String.class));
        typeMappings.add(new TypeMapping("CLOB", String.class));
        typeMappings.add(new TypeMapping("RAW", String.class));

        typeMappings.add(new TypeMapping("NUMBER", Long.class));
        typeMappings.add(new TypeMapping("LONGRAW", Byte[].class));

        typeMappings.add(new TypeMapping("DATE", Date.class));
        typeMappings.add(new TypeMapping("TIMESTAMP", Date.class));
        typeMappings.add(new TypeMapping("TIMESTAMP WITH LOCAL TIME ZONE", Date.class));
        typeMappings.add(new TypeMapping("TIMESTAMP WITH TIME ZONE", Date.class));

    }

    private OracleTypeConverter() {
    }

    public static OracleTypeConverter getInstance() {
        return oracleTypeConverter;
    }


    @Override
    public List<TypeMapping> getTypeMappings() {
        return typeMappings;
    }
}
