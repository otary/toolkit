package cn.chenzw.toolkit.spring.aop;

import cn.chenzw.toolkit.spring.http.HttpHolder;
import cn.chenzw.toolkit.spring.http.HttpRequestWrapper;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.reflect.MethodSignature;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

/**
 * 切点封装类
 *
 * @author chenzw
 */
public class JoinPointWrapper {

    private JoinPoint joinPoint;
    private HttpRequestWrapper requestWrapper;

    public JoinPointWrapper(JoinPoint joinPoint) {
        this.joinPoint = joinPoint;
        this.requestWrapper = new HttpRequestWrapper(HttpHolder.getRequest());
    }


    /**
     * 获取切点对象
     *
     * @return
     */
    public JoinPoint getJoinPoint() {
        return joinPoint;
    }

    /**
     * 获取HttpServletRequest对象
     *
     * @return
     * @since
     */
    public HttpServletRequest getRequest() {
        return requestWrapper.getRequest();
    }

    /**
     * 获取HttpRequestWrapper对象
     *
     * @return
     */
    public HttpRequestWrapper getRequestWrapper() {
        return requestWrapper;
    }

    /**
     * 获取切点方法上的注解
     *
     * @param annotationClass
     * @return
     */
    public <T extends Annotation> T getAnnotation(Class<T> annotationClass) {
        MethodSignature methodSignature = (MethodSignature) this.joinPoint.getSignature();
        Method method = methodSignature.getMethod();
        if (method.isAnnotationPresent(annotationClass)) {
            return method.getAnnotation(annotationClass);
        }
        return null;
    }

    /**
     * 切点方法上是否存在指定的注解
     *
     * @param annotationClass
     * @return
     * @since 1.0.3
     */
    public boolean hasAnnotation(Class<? extends Annotation> annotationClass) {
        MethodSignature methodSignature = (MethodSignature) this.joinPoint.getSignature();
        return methodSignature.getMethod().isAnnotationPresent(annotationClass);
    }

    /**
     * 获取请求链接（含参数）
     *
     * @return
     */
    public String getURI() {
        return this.requestWrapper.getURI();
    }

    public String getHttpMethod() {
        return this.requestWrapper.getMethod();
    }

    /**
     * 获取请求参数
     *
     * @return
     */
    public String getQueryString() {
        return this.requestWrapper.getQueryString();
    }

    /**
     * 获取请求的客户端IP
     *
     * @return
     */
    public String getClientIp() {
        return this.requestWrapper.getClientIp();
    }

    public long getThreadId() {
        return this.requestWrapper.getThreadId();
    }

    public String getThreadName() {
        return this.requestWrapper.getThreadName();
    }

    /**
     * 获取类全名
     *
     * @return
     */
    public String getClassName() {
        return this.joinPoint.getSignature().getDeclaringTypeName();
    }

    /**
     * 获取方法名
     *
     * @return
     */
    public String getMethodName() {
        return this.joinPoint.getSignature().getName();
    }

    /**
     * 获取方法的全路径名
     *
     * @return
     */
    public String getCanonicalClassMethod() {
        return this.getClassName() + "." + this.getMethodName();
    }

    /**
     * 获取方法参数
     *
     * @return
     */
    public ParamMeta[] getMethodArgs() {
        MethodSignature methodSignature = (MethodSignature) this.joinPoint.getSignature();
        String[] parameterNames = methodSignature.getParameterNames();
        Class[] parameterTypes = methodSignature.getParameterTypes();
        ParamMeta[] paramMetas = new ParamMeta[parameterNames.length];
        Object[] args = joinPoint.getArgs();
        for (int i = 0; i < paramMetas.length; i++) {
            ParamMeta paramMeta = new ParamMeta();
            paramMeta.setName(parameterNames[i]);
            paramMeta.setType(parameterTypes[i]);
            paramMeta.setValue(args[i]);
            paramMetas[i] = paramMeta;
        }
        return paramMetas;
    }

    /**
     * 获取HTTP BODY内容
     *
     * @return
     * @throws IOException
     */
    public String getBodyString() throws IOException {
        return this.requestWrapper.getBodyString();
    }

    /**
     * 获取模块名称
     *
     * @return
     */
    public String getArtifactId() {
        Package aPackage = this.joinPoint.getTarget().getClass().getPackage();
        return aPackage.getImplementationTitle();
    }

    public static class ParamMeta {

        private String name;
        private Class<?> type;
        private Object value;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Class<?> getType() {
            return type;
        }

        public void setType(Class<?> type) {
            this.type = type;
        }

        public Object getValue() {
            return value;
        }

        public void setValue(Object value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return "ParamMeta{" + "name='" + name + '\'' + ", type=" + type + ", value='" + value + '\'' + '}';
        }
    }


}
