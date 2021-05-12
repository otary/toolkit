package cn.chenzw.toolkit.mybatis.dynamic.support.factory;

import cn.chenzw.toolkit.mybatis.dynamic.support.DataSourceExt;

import java.util.List;
import java.util.Map;

/**
 * @author chenzw
 */
public interface DynamicDataSourceFactory {

    String DEFAULT_DATASOURCE_NAME = "default.ds";

    String DYNAMIC_DATASOURCE_PROPERTY_PREFIX = "spring.datasource.dynamic";

    List<DataSourceExt> buildDataSources(Map<String, Object> dsMap);

}
