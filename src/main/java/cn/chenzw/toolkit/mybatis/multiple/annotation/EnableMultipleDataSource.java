package cn.chenzw.toolkit.mybatis.multiple.annotation;

import cn.chenzw.toolkit.mybatis.multiple.config.MultipleDataSourceConfig;
import cn.chenzw.toolkit.mybatis.multiple.support.MybatisPropertiesHolder;
import org.springframework.context.annotation.Import;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Import({MultipleDataSourceConfig.class, MybatisPropertiesHolder.class})
public @interface EnableMultipleDataSource {
}
