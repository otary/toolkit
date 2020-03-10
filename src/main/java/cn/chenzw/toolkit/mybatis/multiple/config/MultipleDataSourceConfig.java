package cn.chenzw.toolkit.mybatis.multiple.config;

import cn.chenzw.toolkit.commons.ConvertExtUtils;
import cn.chenzw.toolkit.commons.MapExtUtils;
import cn.chenzw.toolkit.commons.StringExtUtils;
import cn.chenzw.toolkit.mybatis.multiple.support.DataSourceHolder;
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
public class MultipleDataSourceConfig implements ApplicationContextAware {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    private static final String MULTIPLE_DATASOURCE_PROPERTY_PREFIX = "spring.datasource.druid";

    public static final String DEFAULT_DATASOURCE_NAME = "default.ds";

    @Bean
    @ConditionalOnMissingBean
    public DataSource dataSource() {
        DataSource primaryDataSource = DataSourceHolder.getInstance().getPrimary();
        logger.debug("Create primary datasource [{}]", primaryDataSource);
        return primaryDataSource;
    }


    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {

        Binder binder = Binder.get(applicationContext.getEnvironment());
        Map<String, Object> multipleDsMap = binder.bind(MULTIPLE_DATASOURCE_PROPERTY_PREFIX, Map.class).get();

        logger.debug("Find {} datasource!", multipleDsMap == null ? 0 : multipleDsMap.size());

        if (multipleDsMap == null) {
            throw new IllegalArgumentException("Missing property [" + MULTIPLE_DATASOURCE_PROPERTY_PREFIX + "]");
        }
        DataSourceHolder dataSourceHolder = DataSourceHolder.getInstance();
        if (multipleDsMap.containsKey("url") || multipleDsMap.containsKey("type")) {
            DruidDataSource druidDataSource = new DruidDataSource();
            druidDataSource.configFromPropety(MapExtUtils.toProperties(multipleDsMap));
            dataSourceHolder.add(DEFAULT_DATASOURCE_NAME, druidDataSource);
            return;
        }

        multipleDsMap.forEach((dsName, dsProperies) -> {
            Map<String, Object> dsPropertiesMap = (Map<String, Object>) dsProperies;
            TransformedMap.transformedMap(dsPropertiesMap, propertyName -> "druid." + StringExtUtils.toCamel(propertyName, "-", false), propertyValue -> ConvertExtUtils.convert(String.class, propertyValue));

            DruidDataSource druidDataSource = new DruidDataSource();
            druidDataSource.configFromPropety(MapExtUtils.toProperties(dsPropertiesMap));
            dataSourceHolder.add(dsName, druidDataSource);
        });
    }
}
