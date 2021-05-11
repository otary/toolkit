package cn.chenzw.toolkit.mybatis.dynamic.support.factory;

import cn.chenzw.toolkit.commons.ConvertExtUtils;
import cn.chenzw.toolkit.commons.MapExtUtils;
import cn.chenzw.toolkit.commons.StringExtUtils;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.apache.commons.collections4.map.TransformedMap;

import javax.sql.DataSource;
import java.util.Map;
import java.util.stream.Collectors;

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
        // 中划线格式 转 驼峰
        dsMap = dsMap.entrySet().stream().collect(
                Collectors.toMap(
                        (entry) -> StringExtUtils.toCamel(entry.getKey(), "-", false),
                        (entry) -> ConvertExtUtils.convert(String.class, entry.getValue())
                ));

        HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setJdbcUrl(String.valueOf(tryGetProperty(dsMap, "jdbcUrl", "url", "jdbc-url")));
        hikariConfig.setUsername(String.valueOf(tryGetProperty(dsMap, "username", "userName")));
        hikariConfig.setPassword(String.valueOf(tryGetProperty(dsMap, "password")));
        hikariConfig.setDataSourceProperties(MapExtUtils.toProperties(dsMap));
        return  new HikariDataSource(hikariConfig);
    }

    @Override
    protected DataSource createSpecialDs(Map<String, Object> dsMap) {
        return null;
    }




   /* private class PropertyAlias {

        private String[] aliasProperties;

        public PropertyAlias(String... aliasProperties) {
            this.aliasProperties = aliasProperties;
        }

        public void fill(Map<String, Object> map) {
            for (String alias : aliasProperties) {
                if (map.containsKey(alias) && map.get(alias) != null) {
                    Object value = map.get(alias);
                    for (String key : aliasProperties) {
                        map.put(key, value);
                    }
                }
            }
        }
    }*/

}
