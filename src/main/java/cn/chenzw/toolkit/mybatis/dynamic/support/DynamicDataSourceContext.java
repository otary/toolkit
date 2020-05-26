package cn.chenzw.toolkit.mybatis.dynamic.support;


import cn.chenzw.toolkit.commons.MapExtUtils;
import cn.chenzw.toolkit.mybatis.dynamic.config.DynamicDataSourceConfig;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 多数据源容器
 *
 * @author chenzw
 * @since 1.0.3
 */
public class DynamicDataSourceContext {

    private DynamicDataSourceContext() {

    }

    private Map<String, DataSourceExtProperties> dataSourceMap = new ConcurrentHashMap<>();

    public static DynamicDataSourceContext getInstance() {
        return InnerDataSourceContext.instance;
    }


    private static class InnerDataSourceContext {
        private static DynamicDataSourceContext instance = new DynamicDataSourceContext();
    }

    public void add(String dsName, DataSource dataSource) {
        dataSourceMap.put(dsName, new DataSourceExtProperties(dsName, dataSource));
    }


    public DataSource get(String dsName) {
        DataSourceExtProperties dataSourceExtProperties = dataSourceMap.get(dsName);
        if (dataSourceExtProperties != null) {
            return dataSourceExtProperties.getDataSource();
        }
        return null;
    }

    public DataSourceExtProperties getExt(String dsName) {
        return dataSourceMap.get(dsName);
    }

    private DataSourceExtProperties getPrimaryExt() {
        Iterator<Map.Entry<String, DataSourceExtProperties>> iterator = dataSourceMap.entrySet().iterator();
        if (iterator.hasNext()) {
            DataSourceExtProperties extProperties = iterator.next().getValue();
            if (extProperties.isPrimary()) {
                return extProperties;
            }
        }
        return dataSourceMap.getOrDefault(DynamicDataSourceConfig.DEFAULT_DATASOURCE_NAME,
                (DataSourceExtProperties) MapExtUtils.getFirstValue(dataSourceMap));
    }

    /**
     * 获取主数据源
     *
     * @return
     */
    public DataSource getPrimary() {
        return getPrimaryExt().getDataSource();
    }

    public String getPrimaryName() {
        return getPrimaryExt().getName();
    }


    public Map<String, DataSource> list() {
        Map<String, DataSource> results = new HashMap<>();
        for (Map.Entry<String, DataSourceExtProperties> extPropertiesEntry : dataSourceMap.entrySet()) {
            results.put(extPropertiesEntry.getKey(), extPropertiesEntry.getValue().getDataSource());
        }
        return results;
    }

    public Map<Object, Object> list2() {
        Map<Object, Object> results = new HashMap<>();
        for (Map.Entry<String, DataSourceExtProperties> extPropertiesEntry : dataSourceMap.entrySet()) {
            results.put(extPropertiesEntry.getKey(), extPropertiesEntry.getValue().getDataSource());
        }
        return results;
    }


    public Map<String, DataSourceExtProperties> listExt() {
        return dataSourceMap;
    }

    public void add(String dsName, DataSource dataSource, boolean primary) {
        dataSourceMap.put(dsName, new DataSourceExtProperties(dsName, dataSource, primary));
    }


    public class DataSourceExtProperties {

        private String name;

        private boolean primary = false;

        private DataSource dataSource;


        public DataSourceExtProperties(String name, DataSource dataSource) {
            this.name = name;
            this.dataSource = dataSource;
        }

        public DataSourceExtProperties(String name, DataSource dataSource, boolean primary) {
            this.name = name;
            this.dataSource = dataSource;
            this.primary = primary;
        }

        public void setPrimary(boolean primary) {
            this.primary = primary;
        }

        public boolean isPrimary() {
            return primary;
        }

        public DataSource getDataSource() {
            return dataSource;
        }

        public void setDataSource(DataSource dataSource) {
            this.dataSource = dataSource;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return "DataSourceExtProperties{" +
                    "name='" + name + '\'' +
                    ", primary=" + primary +
                    ", dataSource=" + dataSource +
                    '}';
        }
    }

}
