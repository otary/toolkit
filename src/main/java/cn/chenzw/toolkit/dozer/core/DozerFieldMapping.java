package cn.chenzw.toolkit.dozer.core;

import org.dozer.CustomConverter;

/**
 * @author chenzw
 */
public class DozerFieldMapping implements FieldMapping {

    private String srcFieldName;
    private String destFieldName;
    private CustomConverter converter;

    /**
     * 源字段和目标字段名称相同
     *
     * @param fieldName
     * @param converter
     */
    public DozerFieldMapping(String fieldName, CustomConverter converter) {
        this.srcFieldName = fieldName;
        this.destFieldName = fieldName;
        this.converter = converter;
    }

    public DozerFieldMapping(String srcFieldName, String destFieldName, CustomConverter converter) {
        this.srcFieldName = srcFieldName;
        this.destFieldName = destFieldName;
        this.converter = converter;
    }

    @Override
    public String getSrcFieldName() {
        return this.srcFieldName;
    }

    @Override
    public String getDestFieldName() {
        return this.destFieldName;
    }

    @Override
    public CustomConverter getConverter() {
        return this.converter;
    }
}
