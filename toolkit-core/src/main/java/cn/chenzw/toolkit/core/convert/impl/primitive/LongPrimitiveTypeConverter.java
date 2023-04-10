package cn.chenzw.toolkit.core.convert.impl.primitive;

import cn.chenzw.toolkit.core.convert.AbstractTypeConverter;
import cn.chenzw.toolkit.core.lang.BooleanKit;
import org.apache.commons.lang3.math.NumberUtils;

public class LongPrimitiveTypeConverter extends AbstractTypeConverter<Long> {


    @Override
    protected Long convertInternal(Object value) {
        if (value instanceof Number) {
            return ((Number) value).longValue();
        } else if (value instanceof Boolean) {
            return BooleanKit.toLong((Boolean) value);
        }
        final String sValue = convertToStr(value);
        return NumberUtils.toLong(sValue, 0L);
    }
}
