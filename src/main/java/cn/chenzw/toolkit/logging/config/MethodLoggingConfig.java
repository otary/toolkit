package cn.chenzw.toolkit.logging.config;

import cn.chenzw.toolkit.logging.aop.MethodLoggingAspect;
import cn.chenzw.toolkit.logging.configurer.MethodLoggingAdaptor;
import cn.chenzw.toolkit.logging.configurer.MethodLoggingConfigurer;
import cn.chenzw.toolkit.logging.event.listener.MethodLoggingListener;
import cn.chenzw.toolkit.logging.handler.Log4jMethodLoggingHandler;
import cn.chenzw.toolkit.logging.handler.MethodLoggingHandler;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * @author chenzw
 */
@Configuration
public class MethodLoggingConfig {

    @Bean
    public MethodLoggingAspect methodLoggingAspect() {
        return new MethodLoggingAspect();
    }

    @Bean
    @ConditionalOnMissingBean({MethodLoggingConfigurer.class, MethodLoggingAdaptor.class})
    public MethodLoggingConfigurer methodLoggingConfigurer() {
        return new MethodLoggingAdaptor();
    }

    @Bean
    public MethodLoggingListener methodLoggingListener() {
        return new MethodLoggingListener();
    }

    @Bean
    @ConditionalOnMissingBean(MethodLoggingHandler.class)
    public MethodLoggingHandler methodLoggingHandler() {
        return new Log4jMethodLoggingHandler();
    }
}
