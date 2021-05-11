package cn.chenzw.toolkit.mybatis.dynamic.support.factory;

import cn.chenzw.toolkit.commons.ConvertExtUtils;
import cn.chenzw.toolkit.mybatis.dynamic.support.DataSourceExt;
import lombok.extern.slf4j.Slf4j;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author chenzw
 */
@Slf4j
public abstract class AbstractDynamicDataSourceFactory implements DynamicDataSourceFactory {

    public abstract boolean support(String type);

    /**
     * 是否存在数据源连接相关数据
     *
     * @param dsMap
     * @return
     */
    protected boolean checkExistsDsLink(Map<String, Object> dsMap) {
        return dsMap.containsKey("url")
                && dsMap.containsKey("username")
                && dsMap.containsKey("password");
    }

    protected abstract DataSource doCreateDs(Map<String, Object> dsMap);

    protected abstract DataSource createSpecialDs(Map<String, Object> dsMap);

    @Override
    public List<DataSourceExt> createDs(Map<String, Object> dsMap) {
        List<DataSourceExt> dataSourceExts = new ArrayList<>();

        // 如果直接存在url/username/password, 则将其作为主数据源
        if (checkExistsDsLink(dsMap)) {
            dataSourceExts.add(new DataSourceExt(DEFAULT_DATASOURCE_NAME, doCreateDs(dsMap), true));
        }

        DataSource specialDs = createSpecialDs(dsMap);
        if (specialDs != null) {
            dataSourceExts.add(new DataSourceExt(DEFAULT_DATASOURCE_NAME, specialDs, true));
        }

        if (!dsMap.containsKey("dynamic")) {
            log.warn("Missing property [{}]", DYNAMIC_DATASOURCE_PROPERTY_PREFIX);
            return dataSourceExts;
        }

        Map<String, Object> dynamicDsMap = (Map<String, Object>) dsMap.get("dynamic");
        log.debug("Find {} datasource!", dynamicDsMap == null ? 0 : dynamicDsMap.size());

        dynamicDsMap.forEach((dsName, dsProperties) -> {
            Map<String, Object> dsPropertiesMap = (Map<String, Object>) dsProperties;
            boolean isPrimary = ConvertExtUtils.convert(Boolean.class, dsPropertiesMap.getOrDefault("primary", false));

            dataSourceExts.add(new DataSourceExt(dsName, doCreateDs(dsMap), isPrimary));
        });
        return dataSourceExts;

    }
}
