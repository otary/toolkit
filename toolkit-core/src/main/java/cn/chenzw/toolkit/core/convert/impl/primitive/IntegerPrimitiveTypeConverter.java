package cn.chenzw.toolkit.core.convert.impl.primitive;

import cn.chenzw.toolkit.core.convert.AbstractTypeConverter;
import cn.chenzw.toolkit.core.lang.BooleanKit;
import cn.chenzw.toolkit.core.lang.StringKit;

/**
 * @author chenzw
 */
public class IntegerPrimitiveTypeConverter extends AbstractTypeConverter<Integer> {

    @Override
    protected Integer convertInternal(Object value) {
        if (value instanceof Number) {
            return ((Number) value).intValue();
        } else if (value instanceof Boolean) {
            return BooleanKit.toInt((Boolean) value);
        }
        String sValue = convertToStr(value);

        return StringKit.toInteger(sValue, 0);
    }
}
