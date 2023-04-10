package cn.chenzw.toolkit.core.lang;

import cn.chenzw.toolkit.core.convert.TypeConverter;
import cn.chenzw.toolkit.core.convert.impl.*;
import cn.chenzw.toolkit.core.convert.impl.primitive.*;
import cn.chenzw.toolkit.core.convert.impl.wrapper.*;

import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

/**
 * 转换器工具类
 *
 * @author chenzw
 */
public class ConvertKit {

    private ConvertKit() {

    }


    private static Map<Type, TypeConverter<?>> defaultTypeConverterMap;

    static {
        defaultTypeConverterMap = new HashMap<>();

        defaultTypeConverterMap.put(int.class, new IntegerPrimitiveTypeConverter());
        defaultTypeConverterMap.put(long.class, new LongPrimitiveTypeConverter());
        defaultTypeConverterMap.put(byte.class, new BytePrimitiveTypeConverter());
        defaultTypeConverterMap.put(short.class, new ShortPrimitiveTypeConverter());
        defaultTypeConverterMap.put(float.class, new FloatPrimitiveTypeConverter());
        defaultTypeConverterMap.put(double.class, new DoublePrimitiveTypeConverter());
        defaultTypeConverterMap.put(char.class, new CharPrimitiveTypeConverter());
        defaultTypeConverterMap.put(boolean.class, new BooleanPrimitiveTypeConverter());

        defaultTypeConverterMap.put(Integer.class, new IntegerWrapperTypeConverter());
        defaultTypeConverterMap.put(Long.class, new LongWrapperTypeConverter());
        defaultTypeConverterMap.put(Byte.class, new ByteWrapperTypeConverter());
        defaultTypeConverterMap.put(Short.class, new ShortWrapperTypeConverter());
        defaultTypeConverterMap.put(Float.class, new FloatWrapperTypeConverter());
        defaultTypeConverterMap.put(Double.class, new DoubleWrapperTypeConverter());
        defaultTypeConverterMap.put(Boolean.class, new BooleanWrapperTypeConverter());
        defaultTypeConverterMap.put(Character.class, new CharPrimitiveTypeConverter());

        defaultTypeConverterMap.put(java.util.Date.class, new DateTypeConverter());

        defaultTypeConverterMap.put(String.class, new StringTypeConverter());
        defaultTypeConverterMap.put(AtomicInteger.class, new AtomicIntegerTypeConverter());
        defaultTypeConverterMap.put(AtomicLong.class, new AtomicLongTypeConverter());
        defaultTypeConverterMap.put(BigDecimal.class, new BigDecimalTypeConverter());
        defaultTypeConverterMap.put(BigInteger.class, new BigIntegerTypeConverter());
        defaultTypeConverterMap.put(Number.class, new NumberTypeConverter());
    }


    public static <T> T convert(Class<T> type, Object value) {
        return convert((Type) type, value);
    }

    public static <T> T convert(Type type, Object value) {
        return (T) getConverter(type).convert(value, null);
    }

    private static <T> TypeConverter<T> getConverter(Type type) {
        TypeConverter<?> typeConverter = defaultTypeConverterMap.get(type);
        if (typeConverter != null) {
            return (TypeConverter<T>) typeConverter;
        }

        throw new UnsupportedOperationException("No Converter for type [" + type.getTypeName() + "]");
    }

}
