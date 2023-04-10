package cn.chenzw.toolkit.core.convert.impl.primitive;

import cn.chenzw.toolkit.core.convert.AbstractTypeConverter;
import cn.chenzw.toolkit.core.lang.BooleanKit;
import org.apache.commons.lang3.StringUtils;

public class CharPrimitiveTypeConverter extends AbstractTypeConverter<Character> {

    @Override
    protected Character convertInternal(Object value) {
        if (value instanceof Character) {
            return ((Character) value).charValue();
        } else if (value instanceof Boolean) {
            return BooleanKit.toChar((Boolean) value);
        }
        final String sValue = convertToStr(value);
        if (StringUtils.isBlank(sValue)) {
            return 0;
        }
        return sValue.charAt(0);
    }
}
