package cn.chenzw.toolkit.spring.ratelimit.aop;

import cn.chenzw.toolkit.spring.aop.JoinPointWrapper;
import cn.chenzw.toolkit.spring.ratelimit.annotation.MethodRateLimit;
import cn.chenzw.toolkit.spring.ratelimit.exception.RateLimitException;
import com.google.common.util.concurrent.RateLimiter;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;

import java.util.Arrays;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 方法层限速切面
 *
 * @author chenzw
 * @since 1.0.3
 */
@Aspect
public class MethodRateLimitAspect {

    private final Map<String, RateLimiter> rateLimiters = new ConcurrentHashMap<>();

    @Pointcut("@annotation(cn.chenzw.toolkit.spring.ratelimit.annotation.MethodRateLimit)")
    public void methodRateLimit() {

    }

    @Before("methodRateLimit()")
    public void before(JoinPoint joinPoint) {
        JoinPointWrapper joinPointWrapper = new JoinPointWrapper(joinPoint);
        String rateLimitKey = getRateLimitKey(joinPointWrapper);

        MethodRateLimit methodRateLimit = joinPointWrapper.getAnnotation(MethodRateLimit.class);
        RateLimiter rateLimiter = rateLimiters.get(rateLimitKey);
        if (rateLimiter == null) {
            rateLimiter = RateLimiter.create(methodRateLimit.permits(), methodRateLimit.period(), methodRateLimit.unit());
            rateLimiters.putIfAbsent(rateLimitKey, rateLimiter);
        }

        if(!rateLimiter.tryAcquire(methodRateLimit.timeout(), methodRateLimit.unit())){
            throw new RateLimitException("Request rate exceeds limit");
        }
    }

    private String getRateLimitKey(JoinPointWrapper joinPointWrapper) {
        MethodSignature methodSignature = (MethodSignature) joinPointWrapper.getJoinPoint().getSignature();
        return joinPointWrapper.getCanonicalClassMethod() + "." + joinPointWrapper.getMethodName() + Arrays.toString(methodSignature.getParameterTypes());
    }
}
