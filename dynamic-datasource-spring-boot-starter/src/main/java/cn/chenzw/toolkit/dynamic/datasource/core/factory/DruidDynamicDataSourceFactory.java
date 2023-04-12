package cn.chenzw.toolkit.dynamic.datasource.core.factory;

import cn.chenzw.toolkit.core.collection.MapKit;
import cn.chenzw.toolkit.core.lang.ConvertKit;
import cn.chenzw.toolkit.core.lang.StringKit;
import com.alibaba.druid.pool.DruidDataSource;
import lombok.extern.slf4j.Slf4j;

import javax.sql.DataSource;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Druid数据源工厂类
 *
 * @author chenzw
 * @since 1.0.3
 */
@Slf4j
public class DruidDynamicDataSourceFactory extends AbstractDynamicDataSourceFactory {

    private static final String SUPPORT_DS_TYPE = "com.alibaba.druid.pool.DruidDataSource";

    @Override
    public boolean support(String type) {
        return SUPPORT_DS_TYPE.equalsIgnoreCase(type);
    }

    @Override
    protected DataSource doCreateDS(Map<String, Object> dsMap) {
        DruidDataSource druidDataSource = new DruidDataSource();
        dsMap = dsMap.entrySet().stream().collect(
                Collectors.toMap(
                        (entry) -> "druid." + StringKit.toCamelCase(entry.getKey(), "-", false),
                        (entry) -> ConvertKit.convert(String.class, entry.getValue())
                ));
        druidDataSource.configFromPropety(MapKit.toProperties(dsMap));
        return druidDataSource;
    }

    @Override
    protected DataSource createSpecialDS(Map<String, Object> dsMap) {
        if (dsMap.containsKey("druid")) {
            Map<String, Object> druidDsMap = (Map<String, Object>) dsMap.get("druid");
            if (checkExistsDsLink(druidDsMap)) {
                return doCreateDS(dsMap);
            }
        }
        return null;
    }

}
