package cn.chenzw.toolkit.commons.support.convert.impl.wrapper;

import cn.chenzw.toolkit.commons.BooleanExtUtils;
import cn.chenzw.toolkit.commons.support.convert.AbstractTypeConverter;
import org.apache.commons.lang3.StringUtils;

public class IntegerWrapperTypeConverter extends AbstractTypeConverter<Integer> {

    @Override
    protected Integer convertInternal(Object value) {
        if (value instanceof Number) {
            return ((Number) value).intValue();
        } else if (value instanceof Boolean) {
            return BooleanExtUtils.toInteger((Boolean) value);
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
