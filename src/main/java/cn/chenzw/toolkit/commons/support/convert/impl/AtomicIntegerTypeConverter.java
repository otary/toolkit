package cn.chenzw.toolkit.commons.support.convert.impl;

import cn.chenzw.toolkit.commons.BooleanExtUtils;
import cn.chenzw.toolkit.commons.support.convert.AbstractTypeConverter;
import org.apache.commons.lang3.StringUtils;

import java.util.concurrent.atomic.AtomicInteger;

public class AtomicIntegerTypeConverter extends AbstractTypeConverter<AtomicInteger> {

    @Override
    protected AtomicInteger convertInternal(Object value) {
        final AtomicInteger intValue = new AtomicInteger();
        if (value instanceof Number) {
            intValue.set(((Number) value).intValue());
        } else if (value instanceof Boolean) {
            intValue.set(BooleanExtUtils.toInt((Boolean) value));
        }
        final String sValue = convertToStr(value);
        if (StringUtils.isBlank(sValue)) {
            return null;
        }
        intValue.set(Integer.parseInt(sValue));
        return intValue;
    }
}
