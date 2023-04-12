package cn.chenzw.toolkit.logging.annotation;

import cn.chenzw.toolkit.logging.consts.LogField;

import java.lang.annotation.*;

/**
 * @author chenzw
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Documented
public @interface MethodLogging {

    LogField[] logFields() default { LogField.ALL };

    String[] support() default {
        "DEFAULT"
    };

    String appName() default "";

    String moduleName() default "";

}
