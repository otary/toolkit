package cn.chenzw.toolkit.spring.boot.starter;

import cn.chenzw.toolkit.spring.boot.starter.properties.ToolkitProperties;
import cn.chenzw.toolkit.spring.core.SpringContextHolder;
import cn.chenzw.toolkit.spring.http.filter.ContentCachingRequestWrapperFilter;
import cn.chenzw.toolkit.spring.http.filter.HttpHoldFilter;
import cn.chenzw.toolkit.spring.listener.AppContextStartListener;
import cn.chenzw.toolkit.spring.listener.AppContextStopListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author chenzw
 */
@Configuration
public class ToolkitAutoConfiguration {

    @Autowired
    private ToolkitProperties toolkitProperties;

    public static final String PROPERTY_PREFIX = "spring.toolkit";


    @Bean
    @ConditionalOnProperty(prefix = PROPERTY_PREFIX, name = {"appContextHolder.enable", "app-context-holder.enable"}, havingValue = "true", matchIfMissing = true)
    public SpringContextHolder springContextHolder() {
        return new SpringContextHolder();
    }

    @Bean
    @ConditionalOnProperty(prefix = PROPERTY_PREFIX, name = {"appContextHolder.enable", "app-context-holder.enable"}, havingValue = "true", matchIfMissing = true)
    public AppContextStartListener appContextStartListener() {
        return new AppContextStartListener();
    }

    @Bean
    @ConditionalOnProperty(prefix = PROPERTY_PREFIX, name = {"appContextHolder.enable", "app-context-holder.enable"}, havingValue = "true", matchIfMissing = true)
    public AppContextStopListener appContextStopListener() {
        return new AppContextStopListener();
    }

    @Bean
    @ConditionalOnProperty(prefix = PROPERTY_PREFIX, name = {"httpHolder.enable", "http-holder.enable"}, havingValue = "true", matchIfMissing = true)
    public FilterRegistrationBean httpHeldFilter() {
        FilterRegistrationBean frb = new FilterRegistrationBean(new HttpHoldFilter());
        frb.addUrlPatterns(
                toolkitProperties.getHttpHolder().getUrlPatterns()
        );
        frb.setOrder(FilterRegistrationBean.HIGHEST_PRECEDENCE);
        return frb;
    }

    @Bean
    @ConditionalOnProperty(prefix = PROPERTY_PREFIX, name = {"requestContentCache.enable", "request-content-cache.enable"}, havingValue = "true", matchIfMissing = true)
    public FilterRegistrationBean cachingRequestWrapperFilterRegistration(){
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean(new ContentCachingRequestWrapperFilter());
        filterRegistrationBean.addUrlPatterns(
                toolkitProperties.getRequestContentCache().getUrlPatterns()
        );
        return filterRegistrationBean;
    }

}
