package cn.chenzw.toolkit.mybatis.multiple.support;


import javax.sql.DataSource;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

/**
 * 多数据源容器
 *
 * @author chenzw
 * @since 1.0.3
 */
public class DataSourceHolder {

    private DataSourceHolder() {

    }

    private Map<String, DataSource> dataSourceMap = new TreeMap<>();

    public static DataSourceHolder getInstance() {
        return InnerDataSourceHolder.instance;
    }


    private static class InnerDataSourceHolder {
        private static DataSourceHolder instance = new DataSourceHolder();
    }

    public void add(String dsName, DataSource dataSource) {
        dataSourceMap.put(dsName, dataSource);
    }


    public DataSource get(String dsName) {
        return dataSourceMap.get(dsName);
    }

    public DataSource getPrimary() {
        Iterator<Map.Entry<String, DataSource>> iterator = dataSourceMap.entrySet().iterator();
        if (iterator.hasNext()) {
            return iterator.next().getValue();
        }
        return null;
    }

    public Map<String, DataSource> list() {
        return dataSourceMap;
    }

}
