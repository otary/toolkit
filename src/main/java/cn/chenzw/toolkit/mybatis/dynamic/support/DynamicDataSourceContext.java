package cn.chenzw.toolkit.mybatis.dynamic.support;


import cn.chenzw.toolkit.commons.MapExtUtils;
import cn.chenzw.toolkit.mybatis.dynamic.support.factory.DynamicDataSourceFactory;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 多数据源容器
 *
 * @author chenzw
 * @since 1.0.3
 */
public class DynamicDataSourceContext {

    private Map<String, DataSourceExt> dataSourceMap = new ConcurrentHashMap<>();

    private DynamicDataSourceContext() {
    }

    public static DynamicDataSourceContext getInstance() {
        return InnerDataSourceContext.instance;
    }

    private static class InnerDataSourceContext {
        private static DynamicDataSourceContext instance = new DynamicDataSourceContext();
    }

    public void add(String dsName, DataSource dataSource) {
        dataSourceMap.put(dsName, new DataSourceExt(dsName, dataSource));
    }

    public void addAll(List<DataSourceExt> dsExts) {
        for (DataSourceExt dsExt : dsExts) {
            dataSourceMap.put(dsExt.getName(), dsExt);
        }
    }


    public DataSource get(String dsName) {
        DataSourceExt dataSourceExt = dataSourceMap.get(dsName);
        if (dataSourceExt != null) {
            return dataSourceExt.getDataSource();
        }
        return null;
    }

    public DataSourceExt getExt(String dsName) {
        return dataSourceMap.get(dsName);
    }

    private DataSourceExt getPrimaryExt() {
        Iterator<Map.Entry<String, DataSourceExt>> iterator = dataSourceMap.entrySet().iterator();
        if (iterator.hasNext()) {
            DataSourceExt extProperties = iterator.next().getValue();
            if (extProperties.isPrimary()) {
                return extProperties;
            }
        }

        return dataSourceMap.getOrDefault(DynamicDataSourceFactory.DEFAULT_DATASOURCE_NAME,
                (DataSourceExt) MapExtUtils.getFirstValue(dataSourceMap));
    }

    /**
     * 获取主数据源
     *
     * @return
     */
    public DataSource getPrimary() {
        return getPrimaryExt().getDataSource();
    }

    /**
     * 获取主数据源名称
     *
     * @return
     */
    public String getPrimaryName() {
        return getPrimaryExt().getName();
    }


    /**
     * 获取数据源列表
     *
     * @return
     */
    public <K, V> Map<K, V> list() {
        Map<K, V> results = new HashMap<>();
        for (Map.Entry<String, DataSourceExt> extPropertiesEntry : dataSourceMap.entrySet()) {
            results.put((K) extPropertiesEntry.getKey(), (V) extPropertiesEntry.getValue().getDataSource());
        }
        return results;
    }


    public Map<String, DataSourceExt> listExt() {
        return dataSourceMap;
    }

    public void add(String dsName, DataSource dataSource, boolean primary) {
        dataSourceMap.put(dsName, new DataSourceExt(dsName, dataSource, primary));
    }

}
