package cn.chenzw.toolkit.spring.util;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionReaderUtils;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * Spring工具类
 *
 * @author chenzw
 */
public class SpringUtils implements ApplicationContextAware {

    private static ApplicationContext appContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        appContext = applicationContext;
    }

    public static Object getBean(String name) {
        return appContext.getBean(name);
    }

    public static <T> T getBean(Class<T> clazz) {
        return appContext.getBean(clazz);
    }

    public static <T> T getBean(String name, Class<T> clazz) {
        return appContext.getBean(name, clazz);
    }

    /**
     * 注册Bean
     *
     * @param name  Bean名称
     * @param clazz
     * @param args
     * @param <T>
     * @return
     */
    public static <T> T registerBean(String name, Class<T> clazz, Object... args) {
        return doRegisterBean(name, clazz, args);
    }

    /**
     * 注册Bean
     *
     * @param clazz
     * @param args
     * @param <T>
     * @return
     */
    public static <T> T registerBean(Class<T> clazz, Object... args) {
        return registerBean(null, clazz, args);
    }

    private static <T> T doRegisterBean(String name, Class<T> clazz, Object... args) {
        BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(clazz);
        if (ArrayUtils.isNotEmpty(args)) {
            for (Object arg : args) {
                beanDefinitionBuilder.addConstructorArgValue(arg);
            }
        }
        BeanDefinition beanDefinition = beanDefinitionBuilder.getRawBeanDefinition();
        BeanDefinitionRegistry beanFactory = (BeanDefinitionRegistry) ((ConfigurableApplicationContext) appContext)
                .getBeanFactory();
        if (StringUtils.isBlank(name)) {
            name = BeanDefinitionReaderUtils.generateBeanName(beanDefinition, beanFactory, false);
        }
        beanFactory.registerBeanDefinition(name, beanDefinition);
        return appContext.getBean(name, clazz);
    }
}
