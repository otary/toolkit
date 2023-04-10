package cn.chenzw.toolkit.core.convert.impl.wrapper;

import cn.chenzw.toolkit.core.convert.AbstractTypeConverter;
import cn.chenzw.toolkit.core.lang.BooleanKit;
import org.apache.commons.lang3.StringUtils;

public class FloatWrapperTypeConverter extends AbstractTypeConverter<Float> {

    @Override
    protected Float convertInternal(Object value) {
        if (value instanceof Number) {
            return ((Number) value).floatValue();
        } else if (value instanceof Boolean) {
            return BooleanKit.toFloat((Boolean) value);
        }
        final String sValue = convertToStr(value);
        if (StringUtils.isEmpty(sValue)) {
            return null;
        }
        return Float.valueOf(sValue);
    }
}
