package cn.chenzw.toolkit.logging.core;

import java.lang.annotation.*;

/**
 * @author chenzw
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Documented
public @interface MethodLogging {

    LogField[] logFields() default {};




}
