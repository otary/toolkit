package cn.chenzw.toolkit.dynamic.datasource.core.factory;

import cn.chenzw.toolkit.dynamic.datasource.core.DataSourceWrapper;

import java.util.List;
import java.util.Map;

/**
 * @author chenzw
 */
public interface DynamicDataSourceFactory {

    String DEFAULT_DATASOURCE_NAME = "default.ds";

    String DYNAMIC_DATASOURCE_PROPERTY_PREFIX = "spring.datasource.dynamic";

    List<DataSourceWrapper> buildDataSources(Map<String, Object> dsMap);

}
