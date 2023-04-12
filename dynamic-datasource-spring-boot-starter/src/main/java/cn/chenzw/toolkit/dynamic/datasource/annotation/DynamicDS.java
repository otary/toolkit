package cn.chenzw.toolkit.dynamic.datasource.annotation;

import java.lang.annotation.*;

/**
 * 动态数据源注解（指定使用的数据源）
 *
 * @author chenzw
 * @since 1.0.3
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.TYPE})
@Inherited
public @interface DynamicDS {

    /**
     * 数据源名称
     *
     * @return
     */
    String value();
}
