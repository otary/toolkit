package cn.chenzw.toolkit.spring.core;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
import org.springframework.web.context.ContextLoader;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author chenzw
 */
@Component
public class SpringContextHolder implements ApplicationContextAware, DisposableBean {

    private static ApplicationContext appContext;

    private static Map<String, ApplicationContext> appContexts = new ConcurrentHashMap<>();

    public static void addContext(ApplicationContext applicationContext) {
        appContexts.putIfAbsent(applicationContext.getId(), applicationContext);
    }

    public static void removeContext(ApplicationContext applicationContext) {
        appContexts.remove(applicationContext.getId());
    }

    /**
     * 获取所有容器
     *
     * @return
     */
    public static Map<String, ApplicationContext> getAllAppContexts() {
        return appContexts;
    }

    public static ApplicationContext getAppContext() {
        if (appContext == null) {
            appContext = ContextLoader.getCurrentWebApplicationContext();
        }
        return appContext;
    }

    public static void clear() {
        appContext = null;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        appContext = applicationContext;
    }

    @Override
    public void destroy() throws Exception {
        clear();
    }
}
