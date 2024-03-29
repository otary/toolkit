package cn.chenzw.toolkit.spring.boot.starter.annotation;

import cn.chenzw.toolkit.spring.boot.starter.ToolkitAutoConfiguration;
import org.springframework.context.annotation.Import;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Import(ToolkitAutoConfiguration.class)
public @interface EnableToolkit {

}
