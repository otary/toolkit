package cn.chenzw.toolkit.spring.ratelimit.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.concurrent.TimeUnit;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
public @interface MethodRateLimit {

    /**
     * 许可证数
     *
     * @return
     */
    double permits() default 1;


    /**
     * 持续时间
     *
     * @return
     */
    long period() default 1;

    /**
     * 时间单位
     *
     * @return
     */
    TimeUnit unit() default TimeUnit.SECONDS;

    long timeout() default 1;

}
