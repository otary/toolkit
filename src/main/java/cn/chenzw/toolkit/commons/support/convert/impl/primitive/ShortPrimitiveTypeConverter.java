package cn.chenzw.toolkit.commons.support.convert.impl.primitive;

import cn.chenzw.toolkit.commons.BooleanExtUtils;
import cn.chenzw.toolkit.commons.support.convert.AbstractTypeConverter;
import org.apache.commons.lang3.StringUtils;

/**
 * @author chenzw
 */
public class ShortPrimitiveTypeConverter extends AbstractTypeConverter<Short> {

    @Override
    protected Short convertInternal(Object value) {
        if (value instanceof Number) {
            return ((Number) value).shortValue();
        } else if (value instanceof Boolean) {
            return BooleanExtUtils.toshort((Boolean) value);
        }
        String sValue = convertToStr(value);
        if (StringUtils.isBlank(sValue)) {
            return 0;
        }
        return Short.parseShort(sValue);
    }
}
