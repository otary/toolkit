package cn.chenzw.toolkit.commons.support.convert.impl.primitive;

import cn.chenzw.toolkit.commons.BooleanExtUtils;
import cn.chenzw.toolkit.commons.StringExtUtils;
import cn.chenzw.toolkit.commons.support.convert.AbstractTypeConverter;
import org.apache.commons.lang3.math.NumberUtils;

public class LongPrimitiveTypeConverter extends AbstractTypeConverter<Long> {


    @Override
    protected Long convertInternal(Object value) {
        if (value instanceof Number) {
            return ((Number) value).longValue();
        } else if (value instanceof Boolean) {
            return BooleanExtUtils.tolong((Boolean) value);
        }
        final String sValue = convertToStr(value);
        return NumberUtils.toLong(sValue, 0L);
    }
}
