package cn.chenzw.toolkit.dynamic.datasource;

import cn.chenzw.toolkit.dynamic.datasource.aop.AnnotationDynamicDataSourceAspect;
import cn.chenzw.toolkit.dynamic.datasource.core.DataSourceWrapper;
import cn.chenzw.toolkit.dynamic.datasource.core.DynamicDataSourceContext;
import cn.chenzw.toolkit.dynamic.datasource.core.DynamicRoutingDataSource;
import cn.chenzw.toolkit.dynamic.datasource.core.factory.DefaultDynamicDataSourceFactory;
import cn.chenzw.toolkit.dynamic.datasource.core.factory.DynamicDataSourceFactory;
import lombok.extern.slf4j.Slf4j;
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
 */
@Slf4j
@Order(Ordered.HIGHEST_PRECEDENCE)
@Configuration
public class DynamicDatasourceAutoConfiguration implements ApplicationContextAware {

    private static final String DEFAULT_DATASOURCE_PROPERTY_PREFIX = "spring.datasource";

    @Bean
    @ConditionalOnMissingBean({DataSource.class})
    public DataSource dataSource() {
        DynamicRoutingDataSource dynamicRoutingDataSource = new DynamicRoutingDataSource();

        DynamicDataSourceContext dynamicDataSourceContext = DynamicDataSourceContext.getInstance();
        DataSource primaryDataSource = dynamicDataSourceContext.getPrimary();

        log.debug("Use primary datasource [{}]", dynamicDataSourceContext.getPrimaryName());

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
        List<DataSourceWrapper> dsWrappers = dataSourceFactory.buildDataSources(dsMap);
        if (dsWrappers.isEmpty()) {
            throw new IllegalArgumentException("Found 0 dataSource!");
        }
        dynamicDataSourceContext.addAll(dsWrappers);

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


}
