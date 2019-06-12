package cn.chenzw.toolkit.spring.config;

import cn.chenzw.toolkit.http.ContentCachingRequestWrapperFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.FilterRegistration;

@Configuration
public class ToolkitConfig {

    /*@Bean
    public FilterRegistrationBean cachingRequestWrapperFilterRegistration(){

        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean(new ContentCachingRequestWrapperFilter());
        filterRegistrationBean.addUrlPatterns("/*");
        return filterRegistrationBean;
    }*/
}
