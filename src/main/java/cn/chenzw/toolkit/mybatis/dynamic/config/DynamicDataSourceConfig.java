package cn.chenzw.toolkit.mybatis.dynamic.config;

import cn.chenzw.toolkit.commons.ConvertExtUtils;
import cn.chenzw.toolkit.commons.MapExtUtils;
import cn.chenzw.toolkit.commons.StringExtUtils;
import cn.chenzw.toolkit.mybatis.dynamic.aop.AnnotationDynamicDataSourceAspect;
import cn.chenzw.toolkit.mybatis.dynamic.support.DynamicDataSourceContext;
import cn.chenzw.toolkit.mybatis.dynamic.support.DynamicRoutingDataSource;
import com.alibaba.druid.pool.DruidDataSource;
import org.apache.commons.collections4.map.TransformedMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.bind.Binder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.util.Map;

/**
 * @author chenzw
 * @since 1.0.3
 */
@Configuration
public class DynamicDataSourceConfig implements ApplicationContextAware {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    private static final String MULTIPLE_DATASOURCE_PROPERTY_PREFIX = "spring.datasource.druid";

    public static final String DEFAULT_DATASOURCE_NAME = "default.ds";

    @Bean
    @ConditionalOnMissingBean
    public DataSource dataSource() {
        DynamicRoutingDataSource dynamicRoutingDataSource = new DynamicRoutingDataSource();

        DynamicDataSourceContext dynamicDataSourceContext = DynamicDataSourceContext.getInstance();
        DataSource primaryDataSource = dynamicDataSourceContext.getPrimary();
        logger.debug("Use primary datasource [{}]", dynamicDataSourceContext.getPrimaryName());
        dynamicRoutingDataSource.setDefaultTargetDataSource(primaryDataSource);

        dynamicRoutingDataSource.setTargetDataSources(dynamicDataSourceContext.list2());
        return dynamicRoutingDataSource;
    }


    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        Binder binder = Binder.get(applicationContext.getEnvironment());
        Map<String, Object> dynamicDsMap = binder.bind(MULTIPLE_DATASOURCE_PROPERTY_PREFIX, Map.class).get();
        logger.debug("Find {} datasource!", dynamicDsMap == null ? 0 : dynamicDsMap.size());

        if (dynamicDsMap == null) {
            throw new IllegalArgumentException("Missing property [" + MULTIPLE_DATASOURCE_PROPERTY_PREFIX + "]");
        }
        DynamicDataSourceContext dynamicDataSourceContext = DynamicDataSourceContext.getInstance();
        if (dynamicDsMap.containsKey("url") || dynamicDsMap.containsKey("type")) {
            DruidDataSource druidDataSource = new DruidDataSource();
            druidDataSource.configFromPropety(MapExtUtils.toProperties(dynamicDsMap));
            dynamicDataSourceContext.add(DEFAULT_DATASOURCE_NAME, druidDataSource, true);
            return;
        }

        dynamicDsMap.forEach((dsName, dsProperies) -> {
            Map<String, Object> dsPropertiesMap = (Map<String, Object>) dsProperies;
            boolean isPrimary = ConvertExtUtils.convert(Boolean.class, dsPropertiesMap.getOrDefault("primary", false));
            TransformedMap.transformedMap(dsPropertiesMap, propertyName -> "druid." + StringExtUtils.toCamel(propertyName, "-", false), propertyValue -> ConvertExtUtils.convert(String.class, propertyValue));
            DruidDataSource druidDataSource = new DruidDataSource();
            druidDataSource.configFromPropety(MapExtUtils.toProperties(dsPropertiesMap));
            dynamicDataSourceContext.add(dsName, druidDataSource, isPrimary);
        });
    }


    @Bean
    public AnnotationDynamicDataSourceAspect annotationDynamicDataSourceAspect() {
        return new AnnotationDynamicDataSourceAspect();
    }

   /* @Bean
    public MapperDynamicDataSourceAspect mapperDynamicDataSourceAspect(){
        return new MapperDynamicDataSourceAspect();
    }*/


}
