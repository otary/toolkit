package cn.chenzw.toolkit.core.convert.impl.primitive;

import cn.chenzw.toolkit.core.convert.AbstractTypeConverter;
import cn.chenzw.toolkit.core.lang.BooleanKit;
import org.apache.commons.lang3.StringUtils;

/**
 * Byte格式转换器
 *
 * @author chenzw
 */
public class BytePrimitiveTypeConverter extends AbstractTypeConverter<Byte> {

    @Override
    protected Byte convertInternal(Object value) {
        if (value instanceof Number) {
            return ((Number) value).byteValue();
        } else if (value instanceof Boolean) {
            return BooleanKit.toByte((Boolean) value);
        }

        String sValue = convertToStr(value);
        if (StringUtils.isBlank(sValue)) {
            return 0;
        }
        return Byte.parseByte(sValue);
    }
}
