package cn.chenzw.toolkit.dynamic.datasource.aop;

import cn.chenzw.toolkit.dynamic.datasource.annotation.DynamicDS;
import cn.chenzw.toolkit.dynamic.datasource.core.DynamicDataSourceHolder;
import cn.chenzw.toolkit.mybatis.util.SqlMapperKit;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.reflect.MethodSignature;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Optional;

@Slf4j
public abstract class AbstractDynamicDataSourceAspect {

    public void before(JoinPoint joinPoint) {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Method method = methodSignature.getMethod();

        Optional<DynamicDS> dataSourceOpt = findAnnotation(method, DynamicDS.class);
        if (!dataSourceOpt.isPresent()) {
            if (DynamicDataSourceHolder.get() == null) {
                dataSourceOpt = SqlMapperKit.getProxyTargetAnnotation(joinPoint.getTarget(), DynamicDS.class);
            }
        }
        if (dataSourceOpt.isPresent()) {
            log.debug("method [{}] use [{}] dataSource ", method, dataSourceOpt.get().value());
            DynamicDataSourceHolder.set(dataSourceOpt.get().value());
        }
    }

    public void after(JoinPoint point) {
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
