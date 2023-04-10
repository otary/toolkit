package cn.chenzw.toolkit.core.convert.impl.wrapper;

import cn.chenzw.toolkit.core.convert.AbstractTypeConverter;
import cn.chenzw.toolkit.core.lang.BooleanKit;
import org.apache.commons.lang3.StringUtils;

public class DoubleWrapperTypeConverter extends AbstractTypeConverter<Double> {

    @Override
    protected Double convertInternal(Object value) {
        if (value instanceof Number) {
            return ((Number) value).doubleValue();
        } else if (value instanceof Boolean) {
            return BooleanKit.toDouble((Boolean) value);
        }
        final String sValue = convertToStr(value);
        if (StringUtils.isEmpty(sValue)) {
            return null;
        }
        return Double.valueOf(sValue);
    }
}
