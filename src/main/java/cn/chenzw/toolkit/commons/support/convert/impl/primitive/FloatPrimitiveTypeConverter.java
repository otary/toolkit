package cn.chenzw.toolkit.commons.support.convert.impl.primitive;

import cn.chenzw.toolkit.commons.BooleanExtUtils;
import cn.chenzw.toolkit.commons.StringExtUtils;
import cn.chenzw.toolkit.commons.support.convert.AbstractTypeConverter;

/**
 * @author chenzw
 */
public class FloatPrimitiveTypeConverter extends AbstractTypeConverter<Float> {

    @Override
    protected Float convertInternal(Object value) {
        if (value instanceof Number) {
            return ((Number) value).floatValue();
        } else if (value instanceof Boolean) {
            return BooleanExtUtils.tofloat((Boolean) value);
        }
        final String sValue = convertToStr(value);
        return StringExtUtils.toFloat(sValue, 0F);
    }
}
