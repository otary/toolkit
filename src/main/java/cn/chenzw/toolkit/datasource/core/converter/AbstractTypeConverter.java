package cn.chenzw.toolkit.datasource.core.converter;


import java.util.List;
import java.util.Optional;

/**
 * @author chenzw
 */
public abstract class AbstractTypeConverter implements TypeConverter {

    @Override
    public Class<?> toJavaType(String jdbcType, Integer size, Integer digits) {
        List<TypeMapping> types = getTypes();
        Optional<TypeMapping> typeMappingOptional = types.stream().filter((typeMapping) -> {
            if (typeMapping.getJdbcType().equals(jdbcType)) {
                if (typeMapping.getTypeFilter() != null && typeMapping.getTypeFilter()
                        .isMatch(jdbcType, size, digits)) {
                    return true;
                }
            }
            return false;
        }).findFirst();
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
