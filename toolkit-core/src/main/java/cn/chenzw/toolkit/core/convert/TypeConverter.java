package cn.chenzw.toolkit.core.convert;

/**
 * 字段类型转换器
 *
 * @param <T>
 */
public interface TypeConverter<T> {

    /**
     * 将值转换成指定类型（如果类型无法确定，则使用默认值）
     *
     * @param value
     * @param defaultValue
     * @return
     */
    T convert(Object value, T defaultValue);
}
