package cn.chenzw.toolkit.dynamic.datasource.core.factory;

import cn.chenzw.toolkit.core.lang.ConvertKit;
import cn.chenzw.toolkit.dynamic.datasource.core.DataSourceWrapper;
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

    protected abstract DataSource doCreateDS(Map<String, Object> dsMap);

    protected abstract DataSource createSpecialDS(Map<String, Object> dsMap);

    @Override
    public List<DataSourceWrapper> buildDataSources(Map<String, Object> dsMap) {
        List<DataSourceWrapper> dataSourceWrappers = new ArrayList<>();

        // 如果直接存在url/username/password, 则将其作为主数据源
        if (checkExistsDsLink(dsMap)) {
            dataSourceWrappers.add(new DataSourceWrapper(DEFAULT_DATASOURCE_NAME, doCreateDS(dsMap), true));
        }

        DataSource specialDs = createSpecialDS(dsMap);
        if (specialDs != null) {
            dataSourceWrappers.add(new DataSourceWrapper(DEFAULT_DATASOURCE_NAME, specialDs, true));
        }

        if (!dsMap.containsKey("dynamic")) {
            log.warn("Missing property [{}]", DYNAMIC_DATASOURCE_PROPERTY_PREFIX);
            return dataSourceWrappers;
        }

        Map<String, Object> dynamicDsMap = (Map<String, Object>) dsMap.get("dynamic");
        log.debug("Find {} datasource!", dynamicDsMap == null ? 0 : dynamicDsMap.size());

        dynamicDsMap.forEach((dsName, dsProperties) -> {
            Map<String, Object> dsPropertiesMap = (Map<String, Object>) dsProperties;
            boolean isPrimary = ConvertKit.convert(
                    Boolean.class,
                    dsPropertiesMap.getOrDefault("primary", false)
            );
            dataSourceWrappers.add(
                    new DataSourceWrapper(dsName, doCreateDS(dsPropertiesMap), isPrimary)
            );
        });
        return dataSourceWrappers;

    }

    protected Object tryGetProperty(Map<String, Object> dsMap, String... properties) {
        for (String property : properties) {
            if (dsMap.containsKey(property)) {
                return dsMap.get(property);
            }
        }
        return null;
    }
}
