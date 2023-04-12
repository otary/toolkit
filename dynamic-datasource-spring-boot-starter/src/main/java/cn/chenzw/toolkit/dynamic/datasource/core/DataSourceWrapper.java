package cn.chenzw.toolkit.dynamic.datasource.core;

import javax.sql.DataSource;

/**
 * DataSource扩展类
 *
 * @author chenzw
 * @since 1.0.3
 */
public class DataSourceWrapper {

    private String name;

    private boolean primary = false;

    private DataSource dataSource;


    public DataSourceWrapper(String name, DataSource dataSource) {
        this.name = name;
        this.dataSource = dataSource;
    }

    public DataSourceWrapper(String name, DataSource dataSource, boolean primary) {
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
        return "DataSourceExt{" +
                "name='" + name + '\'' +
                ", primary=" + primary +
                ", dataSource=" + dataSource +
                '}';
    }
}
