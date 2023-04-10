package cn.chenzw.toolkit.core.convert.impl.wrapper;

import cn.chenzw.toolkit.core.convert.AbstractTypeConverter;
import cn.chenzw.toolkit.core.lang.BooleanKit;
import org.apache.commons.lang3.StringUtils;

public class ShortWrapperTypeConverter extends AbstractTypeConverter<Short> {

    @Override
    protected Short convertInternal(Object value) {
        if (value instanceof Number) {
            return ((Number) value).shortValue();
        } else if (value instanceof Boolean) {
            return BooleanKit.toShort((Boolean) value);
        }
        String sValue = convertToStr(value);
        if (StringUtils.isBlank(sValue)) {
            return null;
        }
        return Short.valueOf(sValue);
    }
}
