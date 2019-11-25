package cn.chenzw.toolkit.commons.support.convert.impl;

import cn.chenzw.toolkit.commons.BooleanExtUtils;
import cn.chenzw.toolkit.commons.support.convert.AbstractTypeConverter;
import org.apache.commons.lang3.StringUtils;

import java.text.NumberFormat;
import java.text.ParseException;

public class NumberTypeConverter extends AbstractTypeConverter<Number> {

    @Override
    protected Number convertInternal(Object value) {
        if (value instanceof Number) {
            return (Number) value;
        } else if (value instanceof Boolean) {
            return BooleanExtUtils.toInteger((Boolean) value);
        }
        final String sValue = convertToStr(value);

        if (StringUtils.isBlank(sValue)) {
            return null;
        }

        try {
            return NumberFormat.getInstance().parse(sValue);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }
}
