package cn.chenzw.toolkit.logging.aop;

import cn.chenzw.toolkit.logging.annotation.MethodLogging;
import cn.chenzw.toolkit.logging.configurer.MethodLoggingConfigurer;
import cn.chenzw.toolkit.logging.core.LogField;
import cn.chenzw.toolkit.logging.event.MethodLoggingEvent;
import cn.chenzw.toolkit.spring.aop.JoinPointWrapper;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author chenzw
 */
@Aspect
public class MethodLoggingAspect {

    private Logger log = LoggerFactory.getLogger(getClass());

    private static final String LOG_POINT_CUT = "logPointCut()";

    @Autowired
    MethodLoggingConfigurer methodLoggingConfigurer;

    @Autowired
    ApplicationContext appContext;

    @Pointcut("@annotation(cn.chenzw.toolkit.logging.annotation.MethodLogging)")
    public void logPointCut() {

    }

    @Around(LOG_POINT_CUT)
    public Object doAround(ProceedingJoinPoint joinPoint) throws IOException {
        Date startTime = Calendar.getInstance().getTime();
        JoinPointWrapper jpw = new JoinPointWrapper(joinPoint);

        Map<LogField, Object> methodLogMap = new HashMap<>();

        MethodLogging methodLogging = jpw.getAnnotation(MethodLogging.class);
        methodLogMap.put(LogField.LOG_ID, methodLoggingConfigurer.generateLogId());
        methodLogMap.put(LogField.APP_NAME, methodLogging.appName());
        methodLogMap.put(LogField.MODULE_NAME, methodLogging.moduleName());
        methodLogMap.put(LogField.HTTP_METHOD, jpw.getHttpMethod());
        methodLogMap.put(LogField.URI, jpw.getURI());
        methodLogMap.put(LogField.QUERY_STRING, jpw.getQueryString());
        methodLogMap.put(LogField.BODY_STRING, jpw.getBodyString());
        methodLogMap.put(LogField.CLASS_NAME, jpw.getClassName());
        methodLogMap.put(LogField.METHOD_NAME, jpw.getMethodName());
        methodLogMap.put(LogField.METHOD_ARGS, jpw.getMethodArgs());
        methodLogMap.put(LogField.CLIENT_IP, jpw.getClientIp());
        methodLogMap.put(LogField.THREAD_ID, jpw.getThreadId());
        methodLogMap.put(LogField.THREAD_NAME, jpw.getThreadName());
        methodLogMap.put(LogField.START_TIME, startTime);


        try {
            Object result = joinPoint.proceed();
            Date finishTime = Calendar.getInstance().getTime();
            methodLogMap.put(LogField.FINISH_TIME, finishTime);
            methodLogMap.put(LogField.COST, finishTime.getTime() - startTime.getTime());
            methodLogMap.put(LogField.RETURN_VALUE, result);

            appContext.publishEvent(new MethodLoggingEvent(methodLogMap, null));

            return result;
        } catch (Throwable ex) {
            Date finishTime = Calendar.getInstance().getTime();
            methodLogMap.put(LogField.FINISH_TIME, finishTime);
            methodLogMap.put(LogField.COST, finishTime.getTime() - startTime.getTime());
            methodLogMap.put(LogField.EXCEPTION, ex);

            appContext.publishEvent(new MethodLoggingEvent(methodLogMap, ex));

        }

        return null;
    }


}
