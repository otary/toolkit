package cn.chenzw.toolkit.datasource.core.converter;

import java.util.Optional;

/**
 * jdbc类型与java类型转换器
 *
 * @author chenzw
 */
public abstract class AbstractJdbcTypeConverter implements JdbcTypeConverter {

    @Override
    public Class<?> toJavaType(String jdbcType, Integer size, Integer digits) {
        Optional<JdbcTypeMapping> typeMappingOptional = getTypeMappings().stream().filter((jdbcTypeMapping) -> {
            if (jdbcTypeMapping.getJdbcType().equals(jdbcType)) {
                if (jdbcTypeMapping.getJavaTypeFilter() == null) {
                    return true;
                }
                if (jdbcTypeMapping.getJavaTypeFilter().isMatch(jdbcType, size, digits)) {
                    return true;
                }
            }
            return false;
        }).findFirst();

        if (!typeMappingOptional.isPresent()) {
            throw new IllegalArgumentException("JdbcType [" + jdbcType + "] not supported!");
        }

        return typeMappingOptional.get().getJavaType();
    }

    @Override
    public String toJdbcType(Class<?> clazz) {
       /* Map<String, Class<?>> types = getTypes();
        for (Map.Entry<String, Class<?>> typeEntity : types.entrySet()) {
            if (typeEntity.getValue() == clazz) {
                return typeEntity.getKey();
            }
        }*/
        return null;
    }
}
