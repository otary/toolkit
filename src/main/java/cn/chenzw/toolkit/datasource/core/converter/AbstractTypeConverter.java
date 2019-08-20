package cn.chenzw.toolkit.datasource.core.converter;


import java.util.Collection;
import java.util.Map;

/**
 * @author chenzw
 */
public abstract class AbstractTypeConverter implements TypeConverter {

    @Override
    public Class<?> toJavaType(String jdbcType) {
        Map<String, Class<?>> types = getTypes();
        if (types.containsKey(jdbcType)) {
            return types.get(jdbcType);
        }
        return null;
    }

    @Override
    public String toJdbcType(Class<?> clazz) {
        Map<String, Class<?>> types = getTypes();
        for (Map.Entry<String, Class<?>> typeEntity : types.entrySet()) {
            if (typeEntity.getValue() == clazz) {
                return typeEntity.getKey();
            }
        }
        return null;
    }
}
