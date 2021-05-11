package cn.chenzw.toolkit.mybatis.dynamic.support.factory;

import cn.chenzw.toolkit.mybatis.dynamic.support.DataSourceExt;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * @author chenzw
 * @since 1.0.3
 */
public class DefaultDynamicDataSourceFactory implements DynamicDataSourceFactory {

    private List<AbstractDynamicDataSourceFactory> dynamicDataSourceFactories = Arrays.asList(
            new DruidDynamicDataSourceFactory(),
            new HikariDataSourceFactory()
    );

    public List<DataSourceExt> createDs(Map<String, Object> dsMap) {
        String dsType = String.valueOf(dsMap.get("type"));
        for (AbstractDynamicDataSourceFactory dynamicDataSourceFactory : dynamicDataSourceFactories) {
            if (dynamicDataSourceFactory.support(dsType)) {
                return dynamicDataSourceFactory.createDs(dsMap);
            }
        }
        return Collections.emptyList();
    }
}
