package cn.chenzw.toolkit.datasource.core.converter;

import java.util.List;

/**
 * jdbc类型与java类型转换器
 *
 * @author chenzw
 */
public interface JdbcTypeConverter {

    List<JdbcTypeMapping> getTypeMappings();

    Class<?> toJavaType(String jdbcType, Integer size, Integer digits);

    String toJdbcType(Class<?> clazz);

}
