package cn.chenzw.toolkit.spring.core;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
import org.springframework.web.context.ContextLoader;

/**
 * @author chenzw
 */
@Component
public class SpringContextHolder implements ApplicationContextAware, DisposableBean {

    private static ApplicationContext appContext;

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
