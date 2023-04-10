package cn.chenzw.toolkit.core.convert.impl.primitive;

import cn.chenzw.toolkit.core.convert.AbstractTypeConverter;
import cn.chenzw.toolkit.core.lang.BooleanKit;
import org.apache.commons.lang3.math.NumberUtils;

/**
 * @author chenzw
 */
public class FloatPrimitiveTypeConverter extends AbstractTypeConverter<Float> {

    @Override
    protected Float convertInternal(Object value) {
        if (value instanceof Number) {
            return ((Number) value).floatValue();
        } else if (value instanceof Boolean) {
            return BooleanKit.toFloat((Boolean) value);
        }
        final String sValue = convertToStr(value);
        return NumberUtils.toFloat(sValue, 0F);
    }
}
