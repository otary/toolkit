package cn.chenzw.toolkit.spring.encrypt.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 请求内容Base64解码
 *
 * @author chenzw
 * @since 1.0.3
 */
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
public @interface RequestBodyBase64 {


}
