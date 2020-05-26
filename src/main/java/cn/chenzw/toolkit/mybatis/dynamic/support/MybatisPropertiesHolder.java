package cn.chenzw.toolkit.mybatis.dynamic.support;

import org.mybatis.spring.boot.autoconfigure.MybatisProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 *
 * @author chenzw
 * @since 1.0.3
 */
@Configuration
@EnableConfigurationProperties(MybatisProperties.class)
@ConfigurationProperties(prefix = "mybatis")
public class MybatisPropertiesHolder {

    private static MybatisProperties mybatisProperties;

    public MybatisPropertiesHolder(MybatisProperties _mybatisProperties) {
        mybatisProperties = _mybatisProperties;
    }

    public static final MybatisProperties getMybatisProperties() {
        return mybatisProperties;
    }

    private String dynamicDsMapperPaths;


}
