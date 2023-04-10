package cn.chenzw.toolkit.core.exception;

import java.text.MessageFormat;

/**
 * 字段不存在异常
 *
 * @author chenzw
 */
public class FieldNotExistException extends Exception {
    private String fieldName;
    private Object target;

    public FieldNotExistException(String fieldName, Object target) {
        super(MessageFormat.format("Field [{0}] does not exist in Object [{1}]", fieldName, target.getClass().getName()));
        this.fieldName = fieldName;
        this.target = target;
    }


    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public Object getTarget() {
        return target;
    }

    public void setTarget(Object target) {
        this.target = target;
    }


    @Override
    public String toString() {
        return MessageFormat.format("Field [{0}] does not exist in Object [{1}]", fieldName, target.getClass().getName());
    }

}
