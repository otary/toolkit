package cn.chenzw.toolkit.spring.encrypt.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 响应对象Base64编码
 *
 * @author chenzw
 */
@Target({ElementType.PARAMETER, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ResponseBodyBase64 {

    String charset() default "utf-8";
}
