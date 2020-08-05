package cn.chenzw.toolkit.logging.annotation;

import cn.chenzw.toolkit.logging.config.MethodLoggingConfig;
import cn.chenzw.toolkit.logging.core.LogField;
import org.springframework.context.annotation.Import;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author chenzw
 * @since 1.0.3
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Import({MethodLoggingConfig.class})
public @interface EnableMethodLogging {

    LogField[] globalLogFields() default {};

}
