package cn.chenzw.toolkit.mybatis.dynamic.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;

import java.lang.reflect.Method;

/**
 * Mapper拦截器
 *
 * @author chenzw
 * @since 1.0.3
 */
@Aspect
public class MapperDynamicDataSourceAspect extends AbstractDynamicDataSourceAspect {

   /* private String paths;

    public MapperDynamicDataSourceAspect(String paths) {
        this.paths = paths;
    }*/

    private static final String POINT_CUT = "datasource()";

    @Pointcut("!execution(* cn.chenzw.toolkit..*.*(..))")
   //@Pointcut("@annotation(cn.chenzw.toolkit.mybatis.dynamic.support.DynamicDataSource)")
    public void datasource() {

    }

    /**
     * 拦截DataSource注解,并注入数据源
     */
    @Before(POINT_CUT)
    public void before(JoinPoint joinPoint) {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Method method = methodSignature.getMethod();
    }
}
