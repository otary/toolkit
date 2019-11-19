package cn.chenzw.toolkit.commons;

import cn.chenzw.toolkit.commons.support.convert.FieldTypeConverter;
import cn.chenzw.toolkit.commons.support.convert.impl.primitive.*;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

/**
 * 转换器工具类
 *
 * @author chenzw
 */
public class ConvertExtUtils {

    private static Map<Type, FieldTypeConverter<?>> defaultConverterMap;

    static {
        defaultConverterMap = new HashMap<>();

        defaultConverterMap.put(int.class, new IntegerFieldTypeConverter());
        defaultConverterMap.put(long.class, new LongFieldTypeConverter());
        defaultConverterMap.put(byte.class, new ByteFieldTypeConverter());
        defaultConverterMap.put(short.class, new ShortFieldTypeConverter());
        defaultConverterMap.put(float.class, new FloatFieldTypeConverter());
        defaultConverterMap.put(double.class, new DoubleFieldTypeConverter());

        defaultConverterMap.put(Integer.class, new IntegerFieldTypeConverter());
        defaultConverterMap.put(Long.class, new LongFieldTypeConverter());
        defaultConverterMap.put(Byte.class, new ByteFieldTypeConverter());
        defaultConverterMap.put(Short.class, new ShortFieldTypeConverter());
        defaultConverterMap.put(Float.class, new FloatFieldTypeConverter());
        defaultConverterMap.put(Double.class, new DoubleFieldTypeConverter());

    }

    private ConvertExtUtils() {

    }


    public static <T> T convert(Class<T> type, Object value) {
        return convert((Type) type, value);
    }

    public static <T> T convert(Type type, Object value) {
        return (T) getConverter(type).convert(value, null);
    }

    private static <T> FieldTypeConverter<T> getConverter(Type type) {
        FieldTypeConverter<?> fieldTypeConverter = defaultConverterMap.get(type);
        if (fieldTypeConverter != null) {
            return (FieldTypeConverter<T>) fieldTypeConverter;
        }

        throw new RuntimeException("No Converter for type [" + type.getTypeName() + "]");
    }

}
