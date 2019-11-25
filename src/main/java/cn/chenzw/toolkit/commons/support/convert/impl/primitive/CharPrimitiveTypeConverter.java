package cn.chenzw.toolkit.commons.support.convert.impl.primitive;

import cn.chenzw.toolkit.commons.BooleanExtUtils;
import cn.chenzw.toolkit.commons.support.convert.AbstractTypeConverter;
import org.apache.commons.lang3.StringUtils;

public class CharPrimitiveTypeConverter extends AbstractTypeConverter<Character> {

    @Override
    protected Character convertInternal(Object value) {
        if (value instanceof Character) {
            return ((Character) value).charValue();
        } else if (value instanceof Boolean) {
            return BooleanExtUtils.toChar((Boolean) value);
        }
        final String sValue = convertToStr(value);
        if (StringUtils.isBlank(sValue)) {
            return 0;
        }
        return sValue.charAt(0);
    }
}
