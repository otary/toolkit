package cn.chenzw.toolkit.core.convert.impl;

import cn.chenzw.toolkit.core.convert.AbstractTypeConverter;

/**
 * 字符串格式转换器
 *
 * @author chenzw
 */
public class StringTypeConverter extends AbstractTypeConverter<String> {

    @Override
    protected String convertInternal(Object value) {
        return convertToStr(value);
    }
}
