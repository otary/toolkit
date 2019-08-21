package cn.chenzw.toolkit.datasource.core.converter;

import java.util.List;

public interface TypeConverter {

    List<TypeMapping> getTypeMappings();

    Class<?> toJavaType(String jdbcType, Integer size, Integer digits);

    String toJdbcType(Class<?> clazz);

}
