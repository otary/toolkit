package cn.chenzw.toolkit.mybatis.dynamic.support.factory;

import cn.chenzw.toolkit.commons.MapExtUtils;
import com.zaxxer.hikari.HikariDataSource;

import javax.sql.DataSource;
import java.util.Map;

/**
 * @author chenzw
 * @since 1.0.3
 */
public class HikariDataSourceFactory extends AbstractDynamicDataSourceFactory {

    private static final String SUPPORT_DS_TYPE = "com.zaxxer.hikari.HikariDataSource";

    @Override
    public boolean support(String type) {
        return SUPPORT_DS_TYPE.equalsIgnoreCase(type);
    }

    @Override
    protected DataSource doCreateDs(Map<String, Object> dsMap) {
        HikariDataSource hikariDataSource = new HikariDataSource();
        hikariDataSource.setDataSourceProperties(MapExtUtils.toProperties(dsMap));
        return hikariDataSource;
    }

    @Override
    protected DataSource createSpecialDs(Map<String, Object> dsMap) {
        return null;
    }

}
