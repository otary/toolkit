package cn.chenzw.toolkit.core.convert.impl.wrapper;

import cn.chenzw.toolkit.core.convert.AbstractTypeConverter;
import cn.chenzw.toolkit.core.lang.BooleanKit;
import org.apache.commons.lang3.StringUtils;

public class ByteWrapperTypeConverter extends AbstractTypeConverter<Byte> {

    @Override
    protected Byte convertInternal(Object value) {
        if (value instanceof Number) {
            return ((Number) value).byteValue();
        } else if (value instanceof Boolean) {
            return BooleanKit.toByte((Boolean) value);
        }

        String sValue = convertToStr(value);
        if (StringUtils.isBlank(sValue)) {
            return null;
        }
        return Byte.valueOf(sValue);
    }
}
