package cn.chenzw.toolkit.commons.support.convert.impl.wrapper;

import cn.chenzw.toolkit.commons.BooleanExtUtils;
import cn.chenzw.toolkit.commons.support.convert.AbstractTypeConverter;
import org.apache.commons.lang3.StringUtils;

public class DoubleWrapperTypeConverter extends AbstractTypeConverter<Double> {

    @Override
    protected Double convertInternal(Object value) {
        if (value instanceof Number) {
            return ((Number) value).doubleValue();
        } else if (value instanceof Boolean) {
            return BooleanExtUtils.toDouble((Boolean) value);
        }
        final String sValue = convertToStr(value);
        if (StringUtils.isEmpty(sValue)) {
            return null;
        }
        return Double.valueOf(sValue);
    }
}
