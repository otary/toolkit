package cn.chenzw.toolkit.mybatis.multiple.util;

import cn.chenzw.toolkit.mybatis.multiple.support.MybatisPropertiesHolder;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.boot.autoconfigure.MybatisProperties;
import org.mybatis.spring.boot.autoconfigure.SpringBootVFS;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import javax.sql.DataSource;

/**
 * Mapper工具类
 *
 * @author chenzw
 */
public final class SqlMapperUtils {

    private SqlMapperUtils() {

    }

    /**
     * 获取指定DataSource的Mapper
     *
     * @param dataSource
     * @param mapper
     * @param <T>
     * @return
     * @throws Exception
     */
    public static <T> T getMapper(DataSource dataSource, Class<T> mapper) throws Exception {
        if (dataSource == null) {
            throw new IllegalArgumentException("Parameter dataSource is null");
        }
        MybatisProperties mybatisProperties = MybatisPropertiesHolder.getMybatisProperties();
        SqlSessionFactoryBean factory = new SqlSessionFactoryBean();
        factory.setDataSource(dataSource);
        factory.setVfs(SpringBootVFS.class);
        if (StringUtils.hasText(mybatisProperties.getConfigLocation())) {
            factory.setConfigLocation(new PathMatchingResourcePatternResolver().getResource(mybatisProperties.getConfigLocation()));
        }
        Configuration configuration = mybatisProperties.getConfiguration();
        if (configuration == null && !StringUtils.hasText(mybatisProperties.getConfigLocation())) {
            configuration = new Configuration();
        }
        factory.setConfiguration(configuration);
        if (mybatisProperties.getConfigurationProperties() != null) {
            factory.setConfigurationProperties(mybatisProperties.getConfigurationProperties());
        }
        if (StringUtils.hasLength(mybatisProperties.getTypeAliasesPackage())) {
            factory.setTypeAliasesPackage(mybatisProperties.getTypeAliasesPackage());
        }
        if (mybatisProperties.getTypeAliasesSuperType() != null) {
            factory.setTypeAliasesSuperType(mybatisProperties.getTypeAliasesSuperType());
        }
        if (StringUtils.hasLength(mybatisProperties.getTypeHandlersPackage())) {
            factory.setTypeHandlersPackage(mybatisProperties.getTypeHandlersPackage());
        }
        if (!ObjectUtils.isEmpty(mybatisProperties.resolveMapperLocations())) {
            factory.setMapperLocations(mybatisProperties.resolveMapperLocations());
        }

        SqlSessionFactory sqlSessionFactory = factory.getObject();
        SqlSession session = sqlSessionFactory.openSession();
        return session.getMapper(mapper);
    }
}
