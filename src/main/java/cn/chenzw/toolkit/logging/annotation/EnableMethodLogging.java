package cn.chenzw.toolkit.logging.annotation;

import cn.chenzw.toolkit.logging.core.LogField;

import java.lang.annotation.*;

/**
 *
 * @author chenzw
 * @since 1.0.3
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Documented
public @interface EnableMethodLogging {

    LogField[] globalLogFields() default {};

}
