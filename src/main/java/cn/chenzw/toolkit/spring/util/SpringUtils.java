package cn.chenzw.toolkit.spring.util;

import cn.chenzw.toolkit.commons.ClassExtUtils;
import cn.chenzw.toolkit.spring.core.SpringContextHolder;
import cn.chenzw.toolkit.spring.domain.ContextBeans;
import cn.chenzw.toolkit.spring.domain.ContextFilterMappings;
import cn.chenzw.toolkit.spring.domain.ContextHandlerMappings;
import cn.chenzw.toolkit.spring.domain.ContextServletMappings;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionReaderUtils;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerExecutionChain;
import org.springframework.web.servlet.HandlerMapping;
import org.springframework.web.servlet.handler.BeanNameUrlHandlerMapping;
import org.springframework.web.servlet.handler.SimpleUrlHandlerMapping;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;
import org.springframework.web.servlet.resource.ResourceHttpRequestHandler;

import javax.servlet.FilterRegistration;
import javax.servlet.ServletRegistration;
import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.*;

/**
 * Spring工具类
 *
 * @author chenzw
 */
public final class SpringUtils {

    private static final Logger logger = LoggerFactory.getLogger(SpringUtils.class);

    private static final String RESOURCE_HANDLER_MAPPING_BEAN_NAME = "resourceHandlerMapping";

    /**
     * 是否Spring环境
     */
    public static final boolean SPRING_FREMAE_PRESENT = ClassExtUtils.isPresent("org.springframework.context.ApplicationContext");

    /**
     * 是否SpringWeb环境
     */
    public static final boolean SPRING_WEB_FRAME_PRESENT = ClassExtUtils.isPresent("org.springframework.web.servlet.DispatcherServlet");


    private SpringUtils() {
    }

    public static ApplicationContext getAppContext() {
        return SpringContextHolder.getAppContext();
    }

    public static Object getBean(String name) {
        return getAppContext().getBean(name);
    }

    public static <T> T getBean(Class<T> clazz) {
        return getAppContext().getBean(clazz);
    }

    public static <T> T getBean(String name, Class<T> clazz) {
        return getAppContext().getBean(name, clazz);
    }

    public static <T> Map<String, T> getBeansOfType(Class<T> clazz) {
        return getAppContext().getBeansOfType(clazz);
    }

    public static boolean containsBean(String name) {
        return getAppContext().containsBean(name);
    }

    /**
     * 获取所有Bean
     *
     * @return
     */
    public static ContextBeans getBeans() {
        return ContextBeans.describing((ConfigurableApplicationContext) getAppContext());
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
     * 注册controller
     *
     * @param requestMappingInfo 映射信息
     *                           <pre>RequestMappingInfo requestMappingInfo = RequestMappingInfo.paths("/staff/list").methods(RequestMethod.GET).build(); </pre>
     * @param handler            controller对象
     * @param method             绑定的方法
     * @throws NoSuchMethodException
     */
    public static void registerController(RequestMappingInfo requestMappingInfo, Object handler, Method method) throws NoSuchMethodException {
        RequestMappingHandlerMapping requestMappingHandlerMapping = getBean(RequestMappingHandlerMapping.class);
        requestMappingHandlerMapping.registerMapping(requestMappingInfo, handler, method);
    }

    /**
     * 获取ContextPath
     *
     * @return
     */
    public static String getContextPath() {
        return getAppContext().getEnvironment().getProperty("server.context-path");
    }

    /**
     * 获取服务端口
     *
     * @return
     */
    public static String getPort() {
        return getAppContext().getEnvironment().getProperty("server.port");
    }

    private static <T> T doRegisterBean(String beanName, Class<T> clazz, Object... args) {
        BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(clazz);
        if (ArrayUtils.isNotEmpty(args)) {
            for (Object arg : args) {
                beanDefinitionBuilder.addConstructorArgValue(arg);
            }
        }
        ApplicationContext appContext = getAppContext();
        BeanDefinition beanDefinition = beanDefinitionBuilder.getRawBeanDefinition();
        BeanDefinitionRegistry beanFactory = (BeanDefinitionRegistry) ((ConfigurableApplicationContext) appContext)
                .getBeanFactory();
        if (StringUtils.isBlank(beanName)) {
            beanName = BeanDefinitionReaderUtils.generateBeanName(beanDefinition, beanFactory, false);
        }
        beanFactory.registerBeanDefinition(beanName, beanDefinition);
        return appContext.getBean(beanName, clazz);
    }


    /**
     * 获取所有的HandlerMapping
     *
     * @return
     */
    public static ContextHandlerMappings getHandlerMappings() {
        ApplicationContext appContext = getAppContext();
        List<ContextHandlerMappings.HandlerMappingDescription> handlerMappingDescriptions = new ArrayList<>();
        Map<String, HandlerMapping> handerMappings = appContext.getBeansOfType(HandlerMapping.class);
        for (Map.Entry<String, HandlerMapping> handlerMappingEntity : handerMappings.entrySet()) {
            HandlerMapping handlerMapping = handlerMappingEntity.getValue();

            if (handlerMapping instanceof RequestMappingHandlerMapping) {
                RequestMappingHandlerMapping requestMappingHandlerMapping = (RequestMappingHandlerMapping) handlerMapping;
                Map<RequestMappingInfo, HandlerMethod> handlerMethods = requestMappingHandlerMapping.getHandlerMethods();
                for (Map.Entry<RequestMappingInfo, HandlerMethod> handlerMethodEntry : handlerMethods.entrySet()) {
                    handlerMappingDescriptions.add(new ContextHandlerMappings.HandlerMappingDescription(handlerMethodEntry.getValue().toString(), handlerMethodEntry.getKey().toString(), new ContextHandlerMappings.HandlerMethodDescription(handlerMethodEntry.getValue()), new ContextHandlerMappings.RequestMappingConditionsDescription(handlerMethodEntry.getKey())));
                }
            } else if (handlerMapping instanceof BeanNameUrlHandlerMapping) {
                BeanNameUrlHandlerMapping beanNameUrlHandlerMapping = (BeanNameUrlHandlerMapping) handlerMapping;
                Map<String, Object> handlerMap = beanNameUrlHandlerMapping.getHandlerMap();

                logger.info("BeanNameUrlHandlerMapping: {}", handlerMap);
            } else if (handlerMapping instanceof SimpleUrlHandlerMapping) {
                SimpleUrlHandlerMapping simpleUrlHandlerMapping = (SimpleUrlHandlerMapping) handlerMapping;
                Map<String, ?> urlMap = simpleUrlHandlerMapping.getUrlMap();
                for (Map.Entry<String, ?> urlMappingEntry : urlMap.entrySet()) {
                    if (urlMappingEntry.getValue() instanceof ResourceHttpRequestHandler) {
                        ResourceHttpRequestHandler resourceHttpRequestHandler = (ResourceHttpRequestHandler) urlMappingEntry.getValue();
                        handlerMappingDescriptions.add(new ContextHandlerMappings.HandlerMappingDescription(urlMappingEntry.getValue().toString(), urlMappingEntry.getKey(), new ContextHandlerMappings.HandlerMethodDescription(resourceHttpRequestHandler.getClass().getName(), resourceHttpRequestHandler.getClass().getSimpleName(), null),
                                new ContextHandlerMappings.RequestMappingConditionsDescription(null, null, Collections.singleton(RequestMethod.GET), null, Collections.singleton(urlMappingEntry.getKey()), null)));
                    } else {
                        logger.info("Extended HttpRequestHandler: {}", urlMappingEntry);
                    }
                }
            } else {
                logger.info("Extended HandlerMapping: {}", handlerMapping);
            }
        }
        return new ContextHandlerMappings(handlerMappingDescriptions, appContext.getId());

    }

    /**
     * 获取Filter映射列表
     *
     * @return
     */
    public static ContextFilterMappings getFilterMappings() {
        ApplicationContext appContext = getAppContext();
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
        ApplicationContext appContext = getAppContext();
        if (appContext instanceof WebApplicationContext) {
            Collection<? extends ServletRegistration> servletRegistrations = ((WebApplicationContext) appContext).getServletContext().getServletRegistrations().values();
            for (ServletRegistration servletRegistration : servletRegistrations) {
                mappings.add(new ContextServletMappings.ServletRegistrationMappingDescription(servletRegistration));
            }
        }
        return new ContextServletMappings(mappings, appContext.getId());
    }


    /**
     * 获取请求对应的HttpMethod对象
     *
     * @param request
     * @return
     * @throws Exception
     */
    public static HandlerMethod getHanlerMethod(HttpServletRequest request) throws Exception {

        Map<String, HandlerMapping> handlerMappingMap = getBeansOfType(HandlerMapping.class);

        for (HandlerMapping handlerMapping : handlerMappingMap.values()) {
            HandlerExecutionChain handlerExecutionChain = handlerMapping.getHandler(request);
            if (handlerExecutionChain != null) {
                Object handler = handlerExecutionChain.getHandler();

                if (handler instanceof HandlerMethod) {
                    return (HandlerMethod) handler;
                }
            }
        }


       /* Map<String, DispatcherServlet> dispatcherServletMap = SpringUtils.getBeansOfType(DispatcherServlet.class);
        // Not Spring Environment
        if (dispatcherServletMap == null || dispatcherServletMap.isEmpty()) {
            return null;
        }

        for (DispatcherServlet dispatcherServlet : dispatcherServletMap.values()) {
            Field handlerMappingsField = dispatcherServlet.getClass().getDeclaredField("handlerMappings");
            handlerMappingsField.setAccessible(true);
            List<HandlerMapping> handlerMappings = (List<HandlerMapping>) handlerMappingsField.get(dispatcherServlet);

            for (HandlerMapping handlerMapping : handlerMappings) {
                HandlerExecutionChain handlerExecutionChain = handlerMapping.getHandler(request);
                if (handlerExecutionChain != null) {
                    Object handler = handlerExecutionChain.getHandler();

                    if (handler instanceof HandlerMethod) {
                        return (HandlerMethod) handler;
                    }
                }

            }
        }*/
        return null;
    }

    /**
     * 获取所有的静态资源映射关系
     *
     * @return
     */
    public static Map<String, List<Resource>> getResourceMappings() {
        Map<String, List<Resource>> resourceMappingMap = new HashMap<>();
        if (containsBean(RESOURCE_HANDLER_MAPPING_BEAN_NAME)) {
            SimpleUrlHandlerMapping urlHandlerMapping = getBean(RESOURCE_HANDLER_MAPPING_BEAN_NAME, SimpleUrlHandlerMapping.class);
            Map<String, ?> urlMap = urlHandlerMapping.getUrlMap();
            for (Map.Entry<String, ?> entry : urlMap.entrySet()) {
                ResourceHttpRequestHandler resourceHttpRequestHandler = (ResourceHttpRequestHandler) entry.getValue();
                resourceMappingMap.put(entry.getKey(), resourceHttpRequestHandler.getLocations());
            }
        }
        return resourceMappingMap;
    }

}
