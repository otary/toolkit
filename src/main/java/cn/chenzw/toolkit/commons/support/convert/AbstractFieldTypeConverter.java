package cn.chenzw.toolkit.commons.support.convert;

import org.apache.commons.lang3.ArrayUtils;

import java.io.Serializable;

/**
 * @param <T>
 * @author chenzw
 */
public abstract class AbstractFieldTypeConverter<T> implements FieldTypeConverter<T>, Serializable {

    @Override
    public T convert(Object value, T defaultValue) {

        // @TODO
        return convertInternal(value);
    }

    protected abstract T convertInternal(Object value);

    protected String convertToStr(Object value) {
        if (value == null) {
            return null;
        }

        if (value instanceof CharSequence) {
            return value.toString();
        } else if (value.getClass().isArray()) {
            // 数组转换为逗号间隔的字符串
            return ArrayUtils.toString(value);
        }
        return value.toString();
    }
}
