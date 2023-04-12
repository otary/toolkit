package cn.chenzw.toolkit.dynamic.datasource.core;


import cn.chenzw.toolkit.core.collection.MapKit;
import cn.chenzw.toolkit.dynamic.datasource.core.factory.DynamicDataSourceFactory;

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

    private Map<String, DataSourceWrapper> dataSourceMap = new ConcurrentHashMap<>();

    private DynamicDataSourceContext() {
    }

    public static DynamicDataSourceContext getInstance() {
        return InnerDataSourceContext.instance;
    }

    private static class InnerDataSourceContext {
        private static DynamicDataSourceContext instance = new DynamicDataSourceContext();
    }

    public void add(String dsName, DataSource dataSource) {
        dataSourceMap.put(dsName, new DataSourceWrapper(dsName, dataSource));
    }

    public void addAll(List<DataSourceWrapper> dataSourceWrappers) {
        for (DataSourceWrapper dataSourceWrapper : dataSourceWrappers) {
            dataSourceMap.put(dataSourceWrapper.getName(), dataSourceWrapper);
        }
    }


    public DataSource get(String dsName) {
        DataSourceWrapper dataSourceWrapper = dataSourceMap.get(dsName);
        if (dataSourceWrapper != null) {
            return dataSourceWrapper.getDataSource();
        }
        return null;
    }

    public DataSourceWrapper getExt(String dsName) {
        return dataSourceMap.get(dsName);
    }

    private DataSourceWrapper getPrimaryDS() {
        Iterator<Map.Entry<String, DataSourceWrapper>> iterator = dataSourceMap.entrySet().iterator();
        if (iterator.hasNext()) {
            DataSourceWrapper dataSource = iterator.next().getValue();
            if (dataSource.isPrimary()) {
                return dataSource;
            }
        }

        return dataSourceMap.getOrDefault(
                DynamicDataSourceFactory.DEFAULT_DATASOURCE_NAME,
                (DataSourceWrapper) MapKit.getFirstValue(dataSourceMap)
        );
    }

    /**
     * 获取主数据源
     *
     * @return
     */
    public DataSource getPrimary() {
        return getPrimaryDS().getDataSource();
    }

    /**
     * 获取主数据源名称
     *
     * @return
     */
    public String getPrimaryName() {
        return getPrimaryDS().getName();
    }


    /**
     * 获取数据源列表
     *
     * @return
     */
    public <K, V> Map<K, V> list() {
        Map<K, V> results = new HashMap<>();
        for (Map.Entry<String, DataSourceWrapper> dataSourceEntry : dataSourceMap.entrySet()) {
            results.put((K) dataSourceEntry.getKey(), (V) dataSourceEntry.getValue().getDataSource());
        }
        return results;
    }


    public Map<String, DataSourceWrapper> listExt() {
        return dataSourceMap;
    }

    public void add(String dsName, DataSource dataSource, boolean primary) {
        dataSourceMap.put(dsName, new DataSourceWrapper(dsName, dataSource, primary));
    }

}
