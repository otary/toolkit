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
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;

import javax.sql.DataSource;
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


    private Map<String, Object> getPropertiesMap(Binder binder, String prefix) {
        try {
            return binder.bind(prefix, Map.class).get();
        } catch (Exception e) {
            // 忽略
        }
        return null;
    }

    private DruidDataSource createDruidDataSource(Map<String, Object> dsMap) {
        DruidDataSource defaultDataSource = new DruidDataSource();
        TransformedMap.transformedMap(dsMap, propertyName -> "druid." + StringExtUtils.toCamel(propertyName, "-", false), propertyValue -> ConvertExtUtils.convert(String.class, propertyValue));
        defaultDataSource.configFromPropety(MapExtUtils.toProperties(dsMap));
        return defaultDataSource;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        DynamicDataSourceContext dynamicDataSourceContext = DynamicDataSourceContext.getInstance();

        Binder binder = Binder.get(applicationContext.getEnvironment());
        Map<String, Object> defaultDsMap = getPropertiesMap(binder, DEFAULT_DATASOURCE_PROPERTY_PREFIX);
        if (defaultDsMap == null) {
            throw new IllegalArgumentException("Missing property [" + DEFAULT_DATASOURCE_PROPERTY_PREFIX + "]");
        }

        // 如果直接存在url/username/password, 则将其作为主数据源
        if (defaultDsMap.containsKey("url") && defaultDsMap.containsKey("username") && defaultDsMap.containsKey("password")) {
            dynamicDataSourceContext.add(DEFAULT_DATASOURCE_NAME, createDruidDataSource(defaultDsMap), true);
        }

        // 如果存在druid配置, 则将druid的数据源作为主数据源
        if (defaultDsMap.containsKey("druid")) {
            Map<String, Object> druidDsMap = (Map<String, Object>) defaultDsMap.get("druid");
            if (druidDsMap.containsKey("url") && druidDsMap.containsKey("username") && druidDsMap.containsKey("password")) {
                dynamicDataSourceContext.add(DEFAULT_DATASOURCE_NAME, createDruidDataSource(druidDsMap), true);
            }
        }

        if (!defaultDsMap.containsKey("dynamic")) {
            throw new IllegalArgumentException("Missing property [" + DYNAMIC_DATASOURCE_PROPERTY_PREFIX + "]");
        }

        Map<String, Object> dynamicDsMap = (Map<String, Object>) defaultDsMap.get("dynamic");
        logger.debug("Find {} datasource!", dynamicDsMap == null ? 0 : dynamicDsMap.size());

        dynamicDsMap.forEach((dsName, dsProperties) -> {
            Map<String, Object> dsPropertiesMap = (Map<String, Object>) dsProperties;
            boolean isPrimary = ConvertExtUtils.convert(Boolean.class, dsPropertiesMap.getOrDefault("primary", false));
            dynamicDataSourceContext.add(dsName, createDruidDataSource(dsPropertiesMap), isPrimary);
        });
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
