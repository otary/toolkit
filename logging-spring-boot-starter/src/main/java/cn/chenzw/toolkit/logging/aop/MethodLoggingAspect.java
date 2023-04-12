package cn.chenzw.toolkit.logging.aop;

import cn.chenzw.toolkit.logging.annotation.MethodLogging;
import cn.chenzw.toolkit.logging.configurer.MethodLoggingConfigurer;
import cn.chenzw.toolkit.logging.consts.LogField;
import cn.chenzw.toolkit.logging.event.MethodLoggingEvent;
import cn.chenzw.toolkit.spring.aop.JoinPointWrapper;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
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
@Slf4j
public class MethodLoggingAspect {

    private static final String LOG_POINT_CUT = "logPointCut()";

    @Autowired
    private MethodLoggingConfigurer configurer;

    @Autowired
    private ApplicationContext appContext;

    @Pointcut("@annotation(cn.chenzw.toolkit.logging.annotation.MethodLogging)")
    public void logPointCut() {

    }

    @Around(LOG_POINT_CUT)
    public Object around(ProceedingJoinPoint joinPoint) throws IOException {
        Date startTime = Calendar.getInstance().getTime();
        JoinPointWrapper jpw = new JoinPointWrapper(joinPoint);

        MethodLogging methodLogging = jpw.getAnnotation(MethodLogging.class);
        Map<LogField, Object> methodLogMap = new HashMap<LogField, Object>() {
            {
                put(LogField.LOG_ID, configurer.generateLogId());
                put(LogField.APP_NAME, methodLogging.appName());
                put(LogField.MODULE_NAME, methodLogging.moduleName());
                put(LogField.HTTP_METHOD, jpw.getHttpMethod());
                put(LogField.URI, jpw.getURI());
                put(LogField.QUERY_STRING, jpw.getQueryString());
                put(LogField.BODY_STRING, jpw.getBodyString());
                put(LogField.CLASS_NAME, jpw.getClassName());
                put(LogField.METHOD_NAME, jpw.getMethodName());
                put(LogField.METHOD_ARGS, jpw.getMethodArgs());
                put(LogField.CLIENT_IP, jpw.getClientIp());
                put(LogField.THREAD_ID, jpw.getThreadId());
                put(LogField.THREAD_NAME, jpw.getThreadName());
                put(LogField.START_TIME, startTime);
                put(LogField.USER_AGENT, jpw.getRequestWrapper().getUserAgent());
                put(LogField.REQUEST, jpw.getRequest());
                put(LogField.EXT, configurer.getExt(jpw));
            }
        };

        try {
            Object result = joinPoint.proceed();
            Date finishTime = Calendar.getInstance().getTime();
            methodLogMap.put(LogField.FINISH_TIME, finishTime);
            methodLogMap.put(LogField.COST, finishTime.getTime() - startTime.getTime());
            methodLogMap.put(LogField.RETURN_VALUE, result);
            appContext.publishEvent(
                    new MethodLoggingEvent(methodLogMap, jpw.getRequest(), methodLogging, null)
            );
            return result;
        } catch (Throwable ex) {
            Date finishTime = Calendar.getInstance().getTime();
            methodLogMap.put(LogField.FINISH_TIME, finishTime);
            methodLogMap.put(LogField.COST, finishTime.getTime() - startTime.getTime());
            methodLogMap.put(LogField.EXCEPTION, ex);
            appContext.publishEvent(
                    new MethodLoggingEvent(methodLogMap, jpw.getRequest(), methodLogging, ex)
            );
        }
        return null;
    }


}
