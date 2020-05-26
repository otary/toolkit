package cn.chenzw.toolkit.mybatis.dynamic.support;

import java.lang.annotation.*;

/**
 * 指定使用的数据源
 *
 * @author chenzw
 * @since 1.0.3
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.TYPE})
@Inherited
public @interface DynamicDataSource {

    /**
     * 数据源名称
     *
     * @return
     */
    String value();
}
