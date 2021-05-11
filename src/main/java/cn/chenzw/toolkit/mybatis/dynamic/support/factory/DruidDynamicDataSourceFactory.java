package cn.chenzw.toolkit.mybatis.dynamic.support.factory;

import cn.chenzw.toolkit.commons.ConvertExtUtils;
import cn.chenzw.toolkit.commons.MapExtUtils;
import cn.chenzw.toolkit.commons.StringExtUtils;
import com.alibaba.druid.pool.DruidDataSource;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.map.TransformedMap;

import javax.sql.DataSource;
import java.util.Map;

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
    protected DataSource doCreateDs(Map<String, Object> dsMap) {
        DruidDataSource druidDataSource = new DruidDataSource();
        TransformedMap.transformedMap(dsMap, propertyName -> "druid." + StringExtUtils.toCamel(propertyName, "-", false), propertyValue -> ConvertExtUtils.convert(String.class, propertyValue));
        druidDataSource.configFromPropety(MapExtUtils.toProperties(dsMap));
        return druidDataSource;
    }

    @Override
    protected DataSource createSpecialDs(Map<String, Object> dsMap) {
        if (dsMap.containsKey("druid")) {
            Map<String, Object> druidDsMap = (Map<String, Object>) dsMap.get("druid");
            if (checkExistsDsLink(druidDsMap)) {
                return doCreateDs(dsMap);
            }
        }
        return null;
    }

}
