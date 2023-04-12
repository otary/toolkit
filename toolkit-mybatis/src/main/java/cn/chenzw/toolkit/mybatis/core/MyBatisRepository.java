package cn.chenzw.toolkit.mybatis.core;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 用于标注MyBatis Mapper
 *
 * @author chenzw
 * @since 1.0.3
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface MyBatisRepository {
}
