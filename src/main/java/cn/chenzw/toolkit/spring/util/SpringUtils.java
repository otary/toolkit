package cn.chenzw.toolkit.spring.util;

import cn.chenzw.toolkit.spring.domain.ContextBeans;
import cn.chenzw.toolkit.spring.domain.ContextFilterMappings;
import cn.chenzw.toolkit.spring.domain.ContextMappings;
import cn.chenzw.toolkit.spring.domain.ContextServletMappings;
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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.HandlerMapping;
import org.springframework.web.servlet.handler.AbstractUrlHandlerMapping;
import org.springframework.web.servlet.handler.BeanNameUrlHandlerMapping;
import org.springframework.web.servlet.handler.SimpleUrlHandlerMapping;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.RequestMappingInfoHandlerMapping;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;
import org.springframework.web.servlet.resource.ResourceHttpRequestHandler;

import javax.servlet.FilterRegistration;
import javax.servlet.Servlet;
import javax.servlet.ServletRegistration;
import java.util.*;
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


    /**
     * @return
     */
    public static ContextMappings getHandlerMappings() {

        // Map<String, DispatcherServlet> dispatcherServlets = new LinkedHashMap<>();

       /* context.getBeansOfType(ServletRegistrationBean.class).values().forEach((registration) -> {
            Servlet servlet = registration.getServlet();
            if (servlet instanceof DispatcherServlet && !dispatcherServlets.containsValue(servlet)) {
                dispatcherServlets.put(registration.getServletName(), (DispatcherServlet) servlet);
            }
        });*/


        List<ContextMappings.HandlerMappingDescription> handlerMappingDescriptions = new ArrayList<>();

        // RequestMappingInfoHandlerMapping
        /*Map<String, RequestMappingInfoHandlerMapping> requestMappingInfoHandlerMappingMap = appContext.getBeansOfType(RequestMappingInfoHandlerMapping.class);
        for (Map.Entry<String, RequestMappingInfoHandlerMapping> requestMappingInfoHandlerMappingEntry : requestMappingInfoHandlerMappingMap.entrySet()) {
            Map<RequestMappingInfo, HandlerMethod> handlerMethods = requestMappingInfoHandlerMappingEntry.getValue().getHandlerMethods();
            for (Map.Entry<RequestMappingInfo, HandlerMethod> handlerMethodEntry : handlerMethods.entrySet()) {
                handlerMappingDescriptions.add(new ContextMappings.HandlerMappingDescription(handlerMethodEntry.getKey().toString(), new ContextMappings.HandlerMethodDescription(handlerMethodEntry.getValue()), new ContextMappings.RequestMappingConditionsDescription(handlerMethodEntry.getKey())));
            }
        }*/

        // AbstractUrlHandlerMapping
       /* Map<String, AbstractUrlHandlerMapping> urlHandlerMappingMap = appContext.getBeansOfType(AbstractUrlHandlerMapping.class);
        for (Map.Entry<String, AbstractUrlHandlerMapping> urlHandlerMappingEntry : urlHandlerMappingMap.entrySet()) {
            AbstractUrlHandlerMapping urlHandlerMapping = urlHandlerMappingEntry.getValue();

            Map<String, Object> handlerMap = urlHandlerMapping.getHandlerMap();
            for (Map.Entry<String, Object> handlerEntry : handlerMap.entrySet()) {
                ContextMappings.HandlerMappingDescription handlerMappingDescription = new ContextMappings.HandlerMappingDescription(handlerEntry.getValue().toString(), null, null);
                handlerMappingDescriptionMap.put(handlerEntry.getKey(), handlerMappingDescription);
            }
        }*/

        // DelegatingHandlerMapping


        Map<String, HandlerMapping> handerMappings = appContext.getBeansOfType(HandlerMapping.class);

        for (Map.Entry<String, HandlerMapping> handlerMappingEntity : handerMappings.entrySet()) {
            // System.out.println(handlerMappingEntity.getKey() + "::" + handlerMappingEntity.getValue());

            HandlerMapping handlerMapping = handlerMappingEntity.getValue();

            if (handlerMapping instanceof RequestMappingHandlerMapping) {
               /* RequestMappingHandlerMapping handlerMapping21 = (RequestMappingHandlerMapping) handlerMapping2;
                Map<RequestMappingInfo, HandlerMethod> handlerMethods = handlerMapping21.getHandlerMethods();
                for (Map.Entry<RequestMappingInfo, HandlerMethod> handlerMethodEntry : handlerMethods.entrySet()) {
                    System.out.println(handlerMethodEntry.getKey());
                    System.out.println(handlerMethodEntry.getValue());
                }*/

                RequestMappingHandlerMapping requestMappingHandlerMapping = (RequestMappingHandlerMapping) handlerMapping;
                Map<RequestMappingInfo, HandlerMethod> handlerMethods = requestMappingHandlerMapping.getHandlerMethods();
                for (Map.Entry<RequestMappingInfo, HandlerMethod> handlerMethodEntry : handlerMethods.entrySet()) {
                    handlerMappingDescriptions.add(new ContextMappings.HandlerMappingDescription(handlerMethodEntry.getValue().toString(), handlerMethodEntry.getKey().toString(), new ContextMappings.HandlerMethodDescription(handlerMethodEntry.getValue()), new ContextMappings.RequestMappingConditionsDescription(handlerMethodEntry.getKey())));
                }

            } else if (handlerMapping instanceof BeanNameUrlHandlerMapping) {
                BeanNameUrlHandlerMapping beanNameUrlHandlerMapping = (BeanNameUrlHandlerMapping) handlerMapping;
                Map<String, Object> handlerMap = beanNameUrlHandlerMapping.getHandlerMap();


                for (Map.Entry<String, Object> handlerMappingEntry : handlerMap.entrySet()) {
                    if (handlerMappingEntity.getValue() instanceof ResourceHttpRequestHandler) {
                        ResourceHttpRequestHandler resourceHttpRequestHandler = (ResourceHttpRequestHandler) handlerMappingEntity.getValue();
                        handlerMappingDescriptions.add(new ContextMappings.HandlerMappingDescription(handlerMappingEntity.getValue().toString(), handlerMappingEntry.getKey(), new ContextMappings.HandlerMethodDescription(resourceHttpRequestHandler.getClass().toString(), resourceHttpRequestHandler.getClass().getName(), null),
                                new ContextMappings.RequestMappingConditionsDescription(null, null, Collections.singleton(RequestMethod.GET), null, Collections.singleton(handlerMappingEntity.getValue().toString()), null)));
                    } else {
                        System.out.println("--------------BeanNameUrlHandlerMapping:-----------" + handlerMap);


                    }
                }


            } else if (handlerMapping instanceof SimpleUrlHandlerMapping) {
                SimpleUrlHandlerMapping simpleUrlHandlerMapping = (SimpleUrlHandlerMapping) handlerMapping;
                Map<String, ?> urlMap = simpleUrlHandlerMapping.getUrlMap();
                System.out.println("--------------SimpleUrlHandlerMapping:-------" + urlMap);

            } else {
                System.out.println("-------------instanceof:---------------" + handlerMapping.getClass());
            }

        }

        return new ContextMappings(handlerMappingDescriptions, appContext.getId());

    }

    /**
     * 获取Filter映射列表
     *
     * @return
     */
    public static ContextFilterMappings getFilterMappings() {
        List<ContextFilterMappings.FilterRegistrationMappingDescription> mappings = new ArrayList<>();
        if (appContext instanceof WebApplicationContext) {
            Collection<? extends FilterRegistration> filterRegistrations = ((WebApplicationContext) appContext).getServletContext().getFilterRegistrations().values();
            for (FilterRegistration filterRegistration : filterRegistrations) {
                mappings.add(new ContextFilterMappings.FilterRegistrationMappingDescription(filterRegistration));
            }
        }
        return new ContextFilterMappings(mappings, appContext.getId());
    }

    /**
     * @return
     */
    public static ContextServletMappings getServletMappings() {
        List<ContextServletMappings.ServletRegistrationMappingDescription> mappings = new ArrayList<>();

        if (appContext instanceof WebApplicationContext) {
            Collection<? extends ServletRegistration> servletRegistrations = ((WebApplicationContext) appContext).getServletContext().getServletRegistrations().values();
            for (ServletRegistration servletRegistration : servletRegistrations) {
                mappings.add(new ContextServletMappings.ServletRegistrationMappingDescription(servletRegistration));
            }
        }

        return new ContextServletMappings(mappings, appContext.getId());

    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        appContext = applicationContext;
    }


}
