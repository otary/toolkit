package cn.chenzw.toolkit.core.convert.impl.primitive;

import cn.chenzw.toolkit.core.convert.AbstractTypeConverter;
import cn.chenzw.toolkit.core.lang.BooleanKit;
import org.apache.commons.lang3.math.NumberUtils;

/**
 * @author chenzw
 */
public class DoublePrimitiveTypeConverter extends AbstractTypeConverter<Double> {


    @Override
    protected Double convertInternal(Object value) {
        if (value instanceof Number) {
            return ((Number) value).doubleValue();
        } else if (value instanceof Boolean) {
            return BooleanKit.toDouble((Boolean) value);
        }
        final String sValue = convertToStr(value);
        return NumberUtils.toDouble(sValue, 0D);
    }
}
