package cn.chenzw.toolkit.spring.validation.exception;


import cn.chenzw.toolkit.spring.http.HttpRequestWrapper;

import java.util.List;

/**
 * 违反约束校验异常封装类
 *
 * @author chenzw
 */
public interface ConstraintViolationExceptionWrapper {


    /**
     * 获取所有无效的字段
     *
     * @return
     */
    List<InvalidField> getInvalidFields();

    /**
     * 获取调用的方法
     *
     * @return
     */
    String getMethodName();

    /**
     * 获取调用的控制器类
     *
     * @return
     */
    Class<?> getBeanClass();

    /**
     * 获取请求封装类
     *
     * @return
     */
    HttpRequestWrapper getHttpRequestWrapper();

    String toHumanString();

    String getMessages();

    class InvalidField {
        /**
         * 字段名称
         */
        private String fieldName;

        /**
         * 错误提示
         */
        private String message;

        /**
         * 非法值
         */
        private Object invalidValue;

        /**
         * 错误消息模版
         */
        private String messageTemplate;

        public InvalidField(String fieldName, String message, Object invalidValue, String messageTemplate) {
            this.fieldName = fieldName;
            this.message = message;
            this.invalidValue = invalidValue;
            this.messageTemplate = messageTemplate;
        }

        public String getFieldName() {
            return fieldName;
        }

        public void setFieldName(String fieldName) {
            this.fieldName = fieldName;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public String getMessageTemplate() {
            return messageTemplate;
        }

        public void setMessageTemplate(String messageTemplate) {
            this.messageTemplate = messageTemplate;
        }

        public Object getInvalidValue() {
            return invalidValue;
        }

        public void setInvalidValue(Object invalidValue) {
            this.invalidValue = invalidValue;
        }

        @Override
        public String toString() {
            return "InvalidField{" + "fieldName='" + fieldName + '\'' + ", message='" + message + '\''
                    + ", invalidValue=" + invalidValue + ", messageTemplate='" + messageTemplate + '\'' + '}';
        }
    }
}
