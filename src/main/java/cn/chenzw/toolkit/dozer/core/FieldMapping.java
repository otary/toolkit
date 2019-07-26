package cn.chenzw.toolkit.dozer.core;

import org.dozer.CustomConverter;

/**
 * 自定义字段转换映射接口
 */
public interface FieldMapping {

    String getSrcFieldName();

    String getDestFieldName();

    CustomConverter getConverter();
}
