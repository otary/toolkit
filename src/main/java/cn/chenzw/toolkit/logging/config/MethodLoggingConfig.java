package cn.chenzw.toolkit.logging.config;

import cn.chenzw.toolkit.logging.aop.MethodLoggingAspect;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MethodLoggingConfig {

    @Bean
    @ConditionalOnMissingBean
    public MethodLoggingAspect methodLoggingAspect(){
        return new MethodLoggingAspect();
    }
}
