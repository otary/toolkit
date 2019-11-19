package cn.chenzw.toolkit.commons.support.convert.impl;

import cn.chenzw.toolkit.commons.support.convert.AbstractFieldTypeConverter;

/**
 * 字符串格式转换器
 *
 * @author chenzw
 */
public class StringFieldTypeConverter extends AbstractFieldTypeConverter<String> {


    @Override
    protected String convertInternal(Object value) {
        return convertToStr(value);
    }
}
