package cn.chenzw.toolkit.logging.aop;

import cn.chenzw.toolkit.commons.StringTemplateUtils;
import cn.chenzw.toolkit.logging.annotation.MethodLogging;
import cn.chenzw.toolkit.logging.entity.LogField;
import cn.chenzw.toolkit.spring.aop.JoinPointWrapper;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

@Aspect
public class MethodLoggingAspect {


    private Logger log = LoggerFactory.getLogger(getClass());

    private static final String LOG_POINT_CUT = "logPointCut()";


    @Pointcut("@annotation(cn.chenzw.toolkit.logging.annotation.MethodLogging)")
    public void logPointCut() {

    }

    @Around(LOG_POINT_CUT)
    public void doAround(ProceedingJoinPoint joinPoint) {
        JoinPointWrapper jpw = new JoinPointWrapper(joinPoint);

        MethodLogging methodLogging = jpw.getAnnotation(MethodLogging.class);
        if(methodLogging != null){
            LogField[] logFields = methodLogging.logFields();


            Map<LogField, String> templateMap = new HashMap<>();
            templateMap.put(LogField.HTTP_METHOD, "httpMethod: ${httpMethod}");

            Map<String, Object> valueMap = new HashMap<>();
            valueMap.put(LogField.HTTP_METHOD.getName(), jpw.getHttpMethod());

            StringBuilder template = new StringBuilder();
            for (LogField logField : logFields) {
                template.append(templateMap.get(logField)).append(" ");
            }

            log.info(StringTemplateUtils.processTemplate(template.toString(), valueMap));
        }

        try {
            Object proceed = joinPoint.proceed();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
    }

}
