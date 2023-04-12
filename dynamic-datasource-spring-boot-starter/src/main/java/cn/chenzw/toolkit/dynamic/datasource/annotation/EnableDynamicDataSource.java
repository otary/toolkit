package cn.chenzw.toolkit.dynamic.datasource.annotation;

import cn.chenzw.toolkit.dynamic.datasource.DynamicDatasourceAutoConfiguration;
import org.springframework.context.annotation.Import;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 开启多数据源
 *
 * @author chenzw
 * @since 1.0.3
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Import({DynamicDatasourceAutoConfiguration.class})
public @interface EnableDynamicDataSource {

}
