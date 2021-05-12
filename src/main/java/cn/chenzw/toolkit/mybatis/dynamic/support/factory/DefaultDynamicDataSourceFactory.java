package cn.chenzw.toolkit.mybatis.dynamic.support.factory;

import cn.chenzw.toolkit.mybatis.dynamic.support.DataSourceExt;
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

    public List<DataSourceExt> buildDataSources(Map<String, Object> dsMap) {
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
