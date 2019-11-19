package cn.chenzw.toolkit.commons.support.convert.impl.primitive;

import cn.chenzw.toolkit.commons.BooleanExtUtils;
import cn.chenzw.toolkit.commons.StringExtUtils;
import cn.chenzw.toolkit.commons.support.convert.AbstractFieldTypeConverter;

public class LongFieldTypeConverter extends AbstractFieldTypeConverter<Long> {


    @Override
    protected Long convertInternal(Object value) {
        if (value instanceof Number) {
            return ((Number) value).longValue();
        } else if (value instanceof Boolean) {
            return BooleanExtUtils.toLong((Boolean) value);
        }
        final String sValue = convertToStr(value);
        return StringExtUtils.toLong(sValue, 0L);
    }
}
