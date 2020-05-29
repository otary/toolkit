package cn.chenzw.toolkit.spring.ratelimit.annotation;

import cn.chenzw.toolkit.spring.ratelimit.config.MethodRateLimitConfig;
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
@Import({MethodRateLimitConfig.class})
public @interface EnableRateLimit {



}
