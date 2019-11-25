package cn.chenzw.toolkit.commons.support.convert.impl.wrapper;

import cn.chenzw.toolkit.commons.BooleanExtUtils;
import cn.chenzw.toolkit.commons.support.convert.AbstractTypeConverter;
import org.apache.commons.lang3.StringUtils;

public class ShortWrapperTypeConverter extends AbstractTypeConverter<Short> {

    @Override
    protected Short convertInternal(Object value) {
        if (value instanceof Number) {
            return ((Number) value).shortValue();
        } else if (value instanceof Boolean) {
            return BooleanExtUtils.toShort((Boolean) value);
        }
        String sValue = convertToStr(value);
        if (StringUtils.isBlank(sValue)) {
            return null;
        }
        return Short.valueOf(sValue);
    }
}
