package cn.chenzw.toolkit.spring.boot.starter.annotation;

import cn.chenzw.toolkit.spring.boot.starter.ToolkitAutoConfiguration;
import cn.chenzw.toolkit.spring.boot.starter.properties.ToolkitProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Import;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Import(ToolkitAutoConfiguration.class)
@EnableConfigurationProperties(ToolkitProperties.class)
public @interface EnableToolkit {

}
