package cn.chenzw.toolkit.mybatis.dynamic.aop;

import cn.chenzw.toolkit.mybatis.dynamic.support.DynamicDataSource;
import cn.chenzw.toolkit.mybatis.dynamic.support.DynamicDataSourceHolder;
import cn.chenzw.toolkit.mybatis.dynamic.util.SqlMapperUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Optional;

public abstract class AbstractDynamicDataSourceAspect {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    public void before(JoinPoint joinPoint) {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Method method = methodSignature.getMethod();
        System.out.println(method);

        Optional<DynamicDataSource> dataSourceOptl = findAnnotation(method, DynamicDataSource.class);
        if (!dataSourceOptl.isPresent()) {
            if (DynamicDataSourceHolder.get() == null) {
                dataSourceOptl = SqlMapperUtils.getProxyTargetAnnotation(joinPoint.getTarget(), DynamicDataSource.class);
            }
        }
        if (dataSourceOptl.isPresent()) {
            System.out.println(method + "use" + dataSourceOptl.get().value());
            logger.debug("[{}] use [{}] dataSource ", method, dataSourceOptl.get().value());
            DynamicDataSourceHolder.set(dataSourceOptl.get().value());
        }
    }

    public void after(JoinPoint point){
        DynamicDataSourceHolder.clear();
    }


    private <T extends Annotation> Optional<T> findAnnotation(Method method, Class<T> annotationClass) {
        // 方法上的注解
        if (method.isAnnotationPresent(annotationClass)) {
            return Optional.of(method.getAnnotation(annotationClass));
        }

        // 类上的注解
        if (method.getDeclaringClass().isAnnotationPresent(annotationClass)) {
            return Optional.of(method.getDeclaringClass().getAnnotation(annotationClass));
        }
        return Optional.empty();
    }

}
