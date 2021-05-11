package cn.chenzw.toolkit.mybatis.dynamic.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

/**
 * 注解拦截切面
 *
 * @author chenzw
 * @since 1.0.3
 */
@Aspect
public class AnnotationDynamicDataSourceAspect extends AbstractDynamicDataSourceAspect {

    private static final String POINT_CUT = "datasource()";

    @Pointcut("@annotation(cn.chenzw.toolkit.mybatis.dynamic.annotation.DynamicDS) || @within(cn.chenzw.toolkit.mybatis.dynamic.annotation.DynamicDS)")
    public void datasource() {

    }

    /**
     * 拦截DynamicDataSource注解,并注入数据源
     */
    @Before(POINT_CUT)
    public void before(JoinPoint joinPoint) {
        super.before(joinPoint);
    }

    @After(POINT_CUT)
    public void after(JoinPoint joinPoint){
        super.after(joinPoint);
    }


}
