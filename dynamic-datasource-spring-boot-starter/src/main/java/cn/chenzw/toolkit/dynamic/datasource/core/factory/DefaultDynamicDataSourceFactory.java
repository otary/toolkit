package cn.chenzw.toolkit.dynamic.datasource.core.factory;

import cn.chenzw.toolkit.dynamic.datasource.core.DataSourceWrapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * @author chenzw
 * @since 1.0.3
 */
@Slf4j
public class DefaultDynamicDataSourceFactory implements DynamicDataSourceFactory {

    private List<AbstractDynamicDataSourceFactory> dynamicDataSourceFactories = Arrays.asList(
            new DruidDynamicDataSourceFactory(),
            new HikariDataSourceFactory()
    );

    public List<DataSourceWrapper> buildDataSources(Map<String, Object> dsMap) {
        String dsType = String.valueOf(dsMap.get("type"));

        if (StringUtils.isBlank(dsType)) {
            log.warn("Missing Properties[spring.datasource.type]!");
        }

        for (AbstractDynamicDataSourceFactory dynamicDataSourceFactory : dynamicDataSourceFactories) {
            if (dynamicDataSourceFactory.support(dsType)) {
                return dynamicDataSourceFactory.buildDataSources(dsMap);
            }
        }
        return Collections.emptyList();
    }
}
