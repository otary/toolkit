package cn.chenzw.toolkit.core.convert.impl.wrapper;

import cn.chenzw.toolkit.core.convert.AbstractTypeConverter;
import cn.chenzw.toolkit.core.lang.BooleanKit;
import org.apache.commons.lang3.StringUtils;

public class IntegerWrapperTypeConverter extends AbstractTypeConverter<Integer> {

    @Override
    protected Integer convertInternal(Object value) {
        if (value instanceof Number) {
            return ((Number) value).intValue();
        } else if (value instanceof Boolean) {
            return BooleanKit.toInteger((Boolean) value);
        }
        String sValue = convertToStr(value);
        if (StringUtils.isEmpty(sValue)) {
            return null;
        }

        if (StringUtils.contains(sValue, ".")) {
            return Double.valueOf(sValue).intValue();
        }
        return Integer.valueOf(sValue);
    }
}
