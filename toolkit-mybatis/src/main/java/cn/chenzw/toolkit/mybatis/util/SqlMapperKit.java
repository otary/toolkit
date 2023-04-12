package cn.chenzw.toolkit.mybatis.util;

import cn.chenzw.toolkit.core.lang.ReflectKit;
import org.apache.ibatis.binding.MapperProxy;
import org.apache.ibatis.session.SqlSession;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;
import java.util.Optional;

/**
 * Mapper工具类
 *
 * @author chenzw
 * @since 1.0.3
 */
public final class SqlMapperKit {

    private SqlMapperKit() {

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
    /*public static <T> T getMapper(DataSource dataSource, Class<T> mapper) throws Exception {
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
    }*/


    /**
     * 关闭Mapper
     *
     * @param mapper
     */
    public static void closeMapper(Object mapper) {
        if (mapper == null) {
            throw new NullPointerException("Mapper is null!");
        }
        InvocationHandler proxy = Proxy.getInvocationHandler(mapper);
        if (!(proxy instanceof MapperProxy)) {
            throw new IllegalArgumentException("Mapper [" + mapper + "] not instanceof MapperProxy!");
        }
        try {
            SqlSession sqlSession = (SqlSession) ReflectKit.getFieldValueQuietly((MapperProxy) proxy, "sqlSession");
            if (sqlSession != null) {
                sqlSession.close();
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }


    /**
     * 获取Mapper上的注解
     *
     * @param target
     * @param annotationClass
     * @param <T>
     * @return
     */
    public static <T extends Annotation> Optional<T> getProxyTargetAnnotation(Object target, Class<T> annotationClass) {
        if (Proxy.isProxyClass(target.getClass())) {
            InvocationHandler proxy = Proxy.getInvocationHandler(target);

            if (MapperProxy.class.isInstance(proxy)) {
                Object targetMapper = null;
                try {
                    targetMapper = ReflectKit.getFieldValueQuietly((MapperProxy) proxy, "mapperInterface");
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }

                if (targetMapper != null) {
                    if (((Class) targetMapper).isAnnotationPresent(annotationClass)) {
                        return Optional.ofNullable((T) ((Class) targetMapper).getAnnotation(annotationClass));
                    }
                }
            }
        }
        return Optional.empty();
    }


}
