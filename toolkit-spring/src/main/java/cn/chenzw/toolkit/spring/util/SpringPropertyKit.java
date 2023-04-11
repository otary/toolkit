package cn.chenzw.toolkit.spring.util;

import cn.chenzw.toolkit.spring.core.SpringContextHolder;
import org.springframework.boot.context.properties.bind.Binder;
import org.springframework.context.ApplicationContext;


/**
 * 配置属性工具类
 *
 * @author chenzw
 * @since 1.0.3
 */
public final class SpringPropertyKit {

    private SpringPropertyKit() {
    }


    /**
     * 获取配置属性值(application.properties、application.yml等的属性值)
     *
     * @param applicationContext
     * @param propertyName
     * @param returnType
     * @param <T>
     * @return
     */
    public static <T> T get(ApplicationContext applicationContext, String propertyName, Class<T> returnType) {
        Binder binder = Binder.get(applicationContext.getEnvironment());
        return binder.bind(propertyName, returnType).get();
    }

    public static <T> T get(String propertyName, Class<T> returnType) {
        ApplicationContext appContext = SpringContextHolder.getAppContext();
        if (appContext == null) {
            throw new NullPointerException("ApplicationContext is null! you can add @EnableTookit to @Configuration!");
        }
        return get(appContext, propertyName, returnType);
    }
}
