package cn.chenzw.toolkit.logging.aop;

import cn.chenzw.toolkit.logging.core.MethodLogging;
import cn.chenzw.toolkit.spring.aop.JoinPointWrapper;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Aspect
public class MethodLoggingAspect {


    private Logger log = LoggerFactory.getLogger(getClass());

    private static final String LOG_POINT_CUT = "logPointCut()";


    @Pointcut("@annotation(cn.chenzw.toolkit.logging.core.MethodLogging)")
    public void logPointCut() {

    }

    @Around(LOG_POINT_CUT)
    public void doAround(ProceedingJoinPoint joinPoint) {
        JoinPointWrapper jpw = new JoinPointWrapper(joinPoint);

        MethodLogging methodLogging = jpw.getAnnotation(MethodLogging.class);
        if(methodLogging != null){

        }

        try {
            Object proceed = joinPoint.proceed();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
    }

}
