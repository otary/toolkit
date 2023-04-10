package cn.chenzw.toolkit.core.convert.impl;

import cn.chenzw.toolkit.core.convert.AbstractTypeConverter;
import cn.chenzw.toolkit.core.exception.ConvertException;
import cn.chenzw.toolkit.core.lang.BooleanKit;
import org.apache.commons.lang3.StringUtils;

import java.text.NumberFormat;
import java.text.ParseException;

public class NumberTypeConverter extends AbstractTypeConverter<Number> {

    @Override
    protected Number convertInternal(Object value) {
        if (value instanceof Number) {
            return (Number) value;
        } else if (value instanceof Boolean) {
            return BooleanKit.toInteger((Boolean) value);
        }
        final String sValue = convertToStr(value);

        if (StringUtils.isBlank(sValue)) {
            return null;
        }

        try {
            return NumberFormat.getInstance().parse(sValue);
        } catch (ParseException e) {
            throw new ConvertException(e);
        }
    }
}
