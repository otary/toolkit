package cn.chenzw.toolkit.logging.annotation;

import cn.chenzw.toolkit.logging.config.MethodLoggingConfig;
import cn.chenzw.toolkit.logging.entity.LogField;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * @author chenzw
 * @since 1.0.3
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Documented
@Import({MethodLoggingConfig.class})
public @interface EnableMethodLogging {

    LogField[] globalLogFields() default {};

}
