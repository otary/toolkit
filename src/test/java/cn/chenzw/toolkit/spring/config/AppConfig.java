package cn.chenzw.toolkit.spring.config;

import cn.chenzw.toolkit.spring.util.SpringUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    SpringUtils springUtils() {
        return new SpringUtils();
    }
}
