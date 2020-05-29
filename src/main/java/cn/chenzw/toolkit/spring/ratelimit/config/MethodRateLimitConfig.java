package cn.chenzw.toolkit.spring.ratelimit.config;

import cn.chenzw.toolkit.spring.ratelimit.aop.MethodRateLimitAspect;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MethodRateLimitConfig {

    @Bean
    public MethodRateLimitAspect methodRateLimitAspect() {
        return new MethodRateLimitAspect();
    }
}
