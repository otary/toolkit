package cn.chenzw.toolkit.mybatis.dynamic.config;

import cn.chenzw.toolkit.mybatis.dynamic.aop.AnnotationDynamicDataSourceAspect;
import cn.chenzw.toolkit.mybatis.dynamic.support.DataSourceExt;
import cn.chenzw.toolkit.mybatis.dynamic.support.DynamicDataSourceContext;
import cn.chenzw.toolkit.mybatis.dynamic.support.DynamicRoutingDataSource;
import cn.chenzw.toolkit.mybatis.dynamic.support.factory.DefaultDynamicDataSourceFactory;
import cn.chenzw.toolkit.mybatis.dynamic.support.factory.DynamicDataSourceFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.bind.Binder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;

import javax.sql.DataSource;
import java.util.List;
import java.util.Map;

/**
 * @author chenzw
 * @since 1.0.3
 */
@Configuration
@Order(Ordered.HIGHEST_PRECEDENCE)
public class DynamicDataSourceConfig implements ApplicationContextAware {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    private static final String DYNAMIC_DATASOURCE_PROPERTY_PREFIX = "spring.datasource.dynamic";

    private static final String DEFAULT_DATASOURCE_PROPERTY_PREFIX = "spring.datasource";


    @Bean
    @ConditionalOnMissingBean
    public DataSource dataSource() {
        DynamicRoutingDataSource dynamicRoutingDataSource = new DynamicRoutingDataSource();

        DynamicDataSourceContext dynamicDataSourceContext = DynamicDataSourceContext.getInstance();
        DataSource primaryDataSource = dynamicDataSourceContext.getPrimary();

        logger.debug("Use primary datasource [{}]", dynamicDataSourceContext.getPrimaryName());

        dynamicRoutingDataSource.setDefaultTargetDataSource(primaryDataSource);
        dynamicRoutingDataSource.setTargetDataSources(dynamicDataSourceContext.list());
        return dynamicRoutingDataSource;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        DynamicDataSourceContext dynamicDataSourceContext = DynamicDataSourceContext.getInstance();

        Binder binder = Binder.get(applicationContext.getEnvironment());
        Map<String, Object> dsMap = binder.bind(DEFAULT_DATASOURCE_PROPERTY_PREFIX, Map.class).get();
        if (dsMap == null) {
            throw new IllegalArgumentException("Missing property [" + DEFAULT_DATASOURCE_PROPERTY_PREFIX + "]");
        }

        DynamicDataSourceFactory dataSourceFactory = new DefaultDynamicDataSourceFactory();
        List<DataSourceExt> dsExts = dataSourceFactory.createDs(dsMap);
        dynamicDataSourceContext.addAll(dsExts);

    }


    /**
     * 注解拦截器
     *
     * @return
     */
    @Bean
    public AnnotationDynamicDataSourceAspect annotationDynamicDataSourceAspect() {
        return new AnnotationDynamicDataSourceAspect();
    }

   /* @Bean
    public MapperDynamicDataSourceAspect mapperDynamicDataSourceAspect(){
        return new MapperDynamicDataSourceAspect();
    }*/


}
