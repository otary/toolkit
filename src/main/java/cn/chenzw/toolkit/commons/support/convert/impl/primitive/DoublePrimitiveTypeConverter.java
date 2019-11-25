package cn.chenzw.toolkit.commons.support.convert.impl.primitive;

import cn.chenzw.toolkit.commons.BooleanExtUtils;
import cn.chenzw.toolkit.commons.StringExtUtils;
import cn.chenzw.toolkit.commons.support.convert.AbstractTypeConverter;

/**
 * @author chenzw
 */
public class DoublePrimitiveTypeConverter extends AbstractTypeConverter<Double> {


    @Override
    protected Double convertInternal(Object value) {
        if (value instanceof Number) {
            return ((Number) value).doubleValue();
        } else if (value instanceof Boolean) {
            return BooleanExtUtils.todouble((Boolean) value);
        }
        final String sValue = convertToStr(value);
        return StringExtUtils.toDouble(sValue, 0D);
    }
}
