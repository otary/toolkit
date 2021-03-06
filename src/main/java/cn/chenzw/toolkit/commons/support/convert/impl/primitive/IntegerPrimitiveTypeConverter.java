package cn.chenzw.toolkit.commons.support.convert.impl.primitive;

import cn.chenzw.toolkit.commons.BooleanExtUtils;
import cn.chenzw.toolkit.commons.StringExtUtils;
import cn.chenzw.toolkit.commons.support.convert.AbstractTypeConverter;

/**
 * @author chenzw
 */
public class IntegerPrimitiveTypeConverter extends AbstractTypeConverter<Integer> {

    @Override
    protected Integer convertInternal(Object value) {
        if (value instanceof Number) {
            return ((Number) value).intValue();
        } else if (value instanceof Boolean) {
            return BooleanExtUtils.toInt((Boolean) value);
        }
        String sValue = convertToStr(value);

        return StringExtUtils.toInteger(sValue, 0);
    }
}
