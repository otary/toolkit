package cn.chenzw.toolkit.spring.util;

import cn.chenzw.toolkit.spring.domain.ContextBeans;
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
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.HandlerMapping;
import org.springframework.web.servlet.handler.BeanNameUrlHandlerMapping;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import javax.servlet.Servlet;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Spring工具类
 *
 * @author chenzw
 */
public class SpringUtils implements ApplicationContextAware {

    private static ApplicationContext appContext;

    public static ApplicationContext getAppContext() {
        return appContext;
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
     * 获取所有Bean
     *
     * @return
     */
    public static ContextBeans getBeans() {
        return ContextBeans.describing((ConfigurableApplicationContext) appContext);
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

    /**
     * 获取ContextPath
     *
     * @return
     */
    public static String getContextPath() {
        return appContext.getEnvironment().getProperty("server.context-path");
    }

    /**
     * 获取服务端口
     *
     * @return
     */
    public static String getPort() {
        return appContext.getEnvironment().getProperty("server.port");
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


    public static void getHandlerMappings() {
        Map<String, HandlerMapping> handerMappings = appContext.getBeansOfType(HandlerMapping.class);

        for (Map.Entry<String, HandlerMapping> handlerMappingEntity : handerMappings.entrySet()) {
            System.out.println("----------------" + handlerMappingEntity.getKey() + "::" + handlerMappingEntity.getValue());

            HandlerMapping handlerMapping2 = handlerMappingEntity.getValue();

            if(handlerMapping2 instanceof RequestMappingHandlerMapping){
                RequestMappingHandlerMapping handlerMapping21 = (RequestMappingHandlerMapping) handlerMapping2;
                Map<RequestMappingInfo, HandlerMethod> handlerMethods = handlerMapping21.getHandlerMethods();
                for (Map.Entry<RequestMappingInfo, HandlerMethod> handlerMethodEntry : handlerMethods.entrySet()) {

                    System.out.println(handlerMethodEntry.getKey());
                    System.out.println(handlerMethodEntry.getValue());

                }
            } else if(handlerMapping2 instanceof BeanNameUrlHandlerMapping) {
                BeanNameUrlHandlerMapping handlerMapping21 = (BeanNameUrlHandlerMapping) handlerMapping2;
                Map<String, Object> handlerMap = handlerMapping21.getHandlerMap();
                System.out.println(handlerMap);
            } else {
                System.out.println("-------------instanceof:" + handlerMapping2.getClass());
            }

        }

    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        appContext = applicationContext;
    }


}
