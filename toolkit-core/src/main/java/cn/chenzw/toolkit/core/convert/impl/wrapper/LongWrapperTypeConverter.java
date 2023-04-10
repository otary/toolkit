package cn.chenzw.toolkit.core.convert.impl.wrapper;

import cn.chenzw.toolkit.core.convert.AbstractTypeConverter;
import cn.chenzw.toolkit.core.lang.BooleanKit;
import org.apache.commons.lang3.StringUtils;

public class LongWrapperTypeConverter extends AbstractTypeConverter<Long> {


    @Override
    protected Long convertInternal(Object value) {
        if (value instanceof Number) {
            return ((Number) value).longValue();
        } else if (value instanceof Boolean) {
            return BooleanKit.toLong((Boolean) value);
        }
        final String sValue = convertToStr(value);
        if (StringUtils.isEmpty(sValue)) {
            return null;
        }
        return Long.valueOf(sValue);
    }
}
