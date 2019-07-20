package cn.chenzw.toolkit.spring.core;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
import org.springframework.web.context.ContextLoader;

/**
 * @author chenzw
 */
@Component
public class SpringContextHolder implements ApplicationContextAware {

    private static ApplicationContext appContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        appContext = applicationContext;
    }

    public static ApplicationContext getAppContext() {
        if (appContext == null) {
            appContext = ContextLoader.getCurrentWebApplicationContext();

            if(appContext == null){

            }
        }
        return appContext;
    }
}
