package cn.chenzw.toolkit.commons.support.convert.impl;

import cn.chenzw.toolkit.commons.support.convert.AbstractTypeConverter;

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
