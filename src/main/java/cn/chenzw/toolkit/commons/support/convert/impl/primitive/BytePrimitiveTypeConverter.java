package cn.chenzw.toolkit.commons.support.convert.impl.primitive;

import cn.chenzw.toolkit.commons.BooleanExtUtils;
import cn.chenzw.toolkit.commons.support.convert.AbstractTypeConverter;
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
            return BooleanExtUtils.tobyte((Boolean) value);
        }

        String sValue = convertToStr(value);
        if (StringUtils.isBlank(sValue)) {
            return 0;
        }
        return Byte.parseByte(sValue);
    }
}
