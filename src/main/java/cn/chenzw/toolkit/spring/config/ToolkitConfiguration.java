package cn.chenzw.toolkit.spring.config;

import cn.chenzw.toolkit.spring.core.SpringContextHolder;
import cn.chenzw.toolkit.spring.listener.AppContextStartListener;
import cn.chenzw.toolkit.spring.listener.AppContextStopListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ToolkitConfiguration {

    /*@Bean
    public FilterRegistrationBean cachingRequestWrapperFilterRegistration(){
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean(new ContentCachingRequestWrapperFilter());
        filterRegistrationBean.addUrlPatterns("/*");
        return filterRegistrationBean;
    }*/

    @Bean
    public SpringContextHolder springContextHolder() {
        return new SpringContextHolder();
    }

    @Bean
    public AppContextStartListener appContenxtStartListener() {
        return new AppContextStartListener();
    }

    @Bean
    public AppContextStopListener appContextStopListener() {
        return new AppContextStopListener();
    }
}
