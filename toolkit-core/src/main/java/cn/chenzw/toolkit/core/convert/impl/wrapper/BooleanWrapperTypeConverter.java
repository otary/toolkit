package cn.chenzw.toolkit.core.convert.impl.wrapper;

import cn.chenzw.toolkit.core.convert.AbstractTypeConverter;
import org.apache.commons.lang3.BooleanUtils;

public class BooleanWrapperTypeConverter extends AbstractTypeConverter<Boolean> {
    @Override
    protected Boolean convertInternal(Object value) {
        if (value instanceof Boolean) {
            return ((Boolean) value).booleanValue();
        }
        String sValue = convertToStr(value);
        return BooleanUtils.toBoolean(sValue);
    }
}
