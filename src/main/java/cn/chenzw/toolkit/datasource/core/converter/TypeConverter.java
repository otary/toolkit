package cn.chenzw.toolkit.datasource.core.converter;

import java.util.Map;

public interface TypeConverter {

    Map<String, Class<?>> getTypes();

    Class<?> toJavaType(String jdbcType);

    String toJdbcType(Class<?> clazz);

}
