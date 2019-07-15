package cn.chenzw.toolkit.spring.config;

import cn.chenzw.toolkit.spring.util.SpringUtils;
import org.springframework.cache.CacheManager;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.cache.ehcache.EhCacheManagerFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

@Configuration
public class AppConfig {

    @Bean
    public SpringUtils springUtils() {
        return new SpringUtils();
    }

}
