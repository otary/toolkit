package cn.chenzw.toolkit.core.convert.impl;

import cn.chenzw.toolkit.core.convert.AbstractTypeConverter;
import cn.chenzw.toolkit.core.lang.BooleanKit;
import org.apache.commons.lang3.StringUtils;

import java.util.concurrent.atomic.AtomicLong;

public class AtomicLongTypeConverter extends AbstractTypeConverter<AtomicLong> {
    @Override
    protected AtomicLong convertInternal(Object value) {
        final AtomicLong longValue = new AtomicLong();
        if (value instanceof Number) {
            longValue.set(((Number) value).longValue());
        } else if (value instanceof Boolean) {
            longValue.set(BooleanKit.toLong((Boolean) value));
        }
        final String sValue = convertToStr(value);
        if (StringUtils.isBlank(sValue)) {
            return null;
        }
        longValue.set(Long.parseLong(sValue));
        return longValue;
    }
}
