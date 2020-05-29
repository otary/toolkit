package cn.chenzw.toolkit.spring.ratelimit.aop;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.annotation.Scope;

@Aspect
@Scope
public class MethodRateLimitAspect {



    @Pointcut("@annotation(cn.chenzw.toolkit.spring.ratelimit.annotation.MethodRateLimit)")
    public void methodRateLimit(){

    }
}
