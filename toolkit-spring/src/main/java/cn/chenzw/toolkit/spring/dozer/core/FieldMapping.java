package cn.chenzw.toolkit.spring.dozer.core;

import com.github.dozermapper.core.CustomConverter;

/**
 * 自定义字段转换映射接口
 */
public interface FieldMapping {

    String getSrcFieldName();

    String getDestFieldName();

    CustomConverter getConverter();
}
