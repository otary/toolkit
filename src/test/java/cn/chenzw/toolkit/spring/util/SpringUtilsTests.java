package cn.chenzw.toolkit.spring.util;

import cn.chenzw.toolkit.domain.entity.Book;
import cn.chenzw.toolkit.domain.entity.User;
import cn.chenzw.toolkit.spring.config.AppConfig;
import cn.chenzw.toolkit.spring.config.WebConfig;
import cn.chenzw.toolkit.spring.core.SpringContextHolder;
import cn.chenzw.toolkit.spring.domain.ContextBeans;
import cn.chenzw.toolkit.spring.domain.ContextFilterMappings;
import cn.chenzw.toolkit.spring.domain.ContextHandlerMappings;
import cn.chenzw.toolkit.spring.domain.ContextServletMappings;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.Map;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = {AppConfig.class, WebConfig.class})
public class SpringUtilsTests {

    @Autowired
    private ApplicationContext applicationContext;

    @Test
    public void testRegisterBean() {
        // 注册bean
        User userBean = SpringUtils.registerBean(User.class);
        Assert.assertNotNull(userBean);

        // 指定bean名称注册
        Book bookBean = SpringUtils.registerBean("book2", Book.class);
        Assert.assertNotNull(bookBean);

        // 获取指定bean名称的bean
        Object user = SpringUtils.getBean("cn.chenzw.toolkit.domain.entity.User#0");
        Assert.assertNotNull(user);

        // 获取指定类的bean
        User userBean3 = SpringUtils.getBean(User.class);
        Assert.assertNotNull(userBean3);


        Object book = SpringUtils.getBean("book2");
        Assert.assertNotNull(book);

        Book bookBean2 = SpringUtils.getBean(Book.class);
        Assert.assertNotNull(bookBean2);

    }

    @Test
    public void testGetBeans() {
        ContextBeans beans = SpringUtils.getBeans();
        Assert.assertNotNull(beans);
    }

    /**
     * <pre>
     * {
     *     "mappings": [{
     *         "handler": "public void cn.chenzw.springboot.web.controllers.FileUploadController.upload(org.springframework.web.multipart.MultipartFile) throws java.io.IOException",
     *         "predicate": "{POST /upload}",
     *         "handlerMethod": {
     *             "className": "cn.chenzw.springboot.web.controllers.FileUploadController",
     *             "name": "upload",
     *             "descriptor": "(Lorg/springframework/web/multipart/MultipartFile;)V"
     *         },
     *         "requestMappingConditions": {
     *             "consumes": [],
     *             "headers": [],
     *             "methods": ["POST"],
     *             "params": [],
     *             "patterns": ["/upload"],
     *             "produces": []
     *         }
     *     }, {
     *         "handler": "ResourceHttpRequestHandler [\"classpath:/META-INF/resources/webjars/\"]",
     *         "predicate": "/webjars/**",
     *         "handlerMethod": {
     *             "className": "org.springframework.web.servlet.resource.ResourceHttpRequestHandler",
     *             "name": "ResourceHttpRequestHandler",
     *             "descriptor": null
     *         },
     *         "requestMappingConditions": {
     *             "consumes": null,
     *             "headers": null,
     *             "methods": ["GET"],
     *             "params": null,
     *             "patterns": ["/webjars/**"],
     *             "produces": null
     *         }
     *     }],
     *     "parentId": "application"
     * }
     * </pre>
     */
    @Test
    public void testGetHandlerMappings() {
        ContextHandlerMappings handlerMappings = SpringUtils.getHandlerMappings();

        Assert.assertThat(handlerMappings.getMappings(), Matchers.hasItem(Matchers.hasProperty("handler", Matchers.is("public java.lang.String cn.chenzw.toolkit.spring.controllers.HelloRestController.receive(java.lang.String)"))));
        Assert.assertThat(handlerMappings.getMappings(), Matchers.hasItem(Matchers.hasProperty("predicate", Matchers.is("{[/hello/say],methods=[GET]}"))));

    }

    /**
     * <pre>
     *    {
     *     "servletFilters": [{
     *         "servletNameMappings": [],
     *         "urlPatternMappings": ["/*"],
     *         "name": "requestContextFilter",
     *         "className": "org.springframework.boot.web.servlet.filter.OrderedRequestContextFilter"
     *     }, {
     *         "servletNameMappings": [],
     *         "urlPatternMappings": ["/*"],
     *         "name": "Tomcat WebSocket (JSR356) Filter",
     *         "className": "org.apache.tomcat.websocket.server.WsFilter"
     *     }, {
     *         "servletNameMappings": [],
     *         "urlPatternMappings": ["/*"],
     *         "name": "hiddenHttpMethodFilter",
     *         "className": "org.springframework.boot.web.servlet.filter.OrderedHiddenHttpMethodFilter"
     *     }, {
     *         "servletNameMappings": [],
     *         "urlPatternMappings": ["/*"],
     *         "name": "characterEncodingFilter",
     *         "className": "org.springframework.boot.web.servlet.filter.OrderedCharacterEncodingFilter"
     *     }, {
     *         "servletNameMappings": [],
     *         "urlPatternMappings": ["/*"],
     *         "name": "formContentFilter",
     *         "className": "org.springframework.boot.web.servlet.filter.OrderedFormContentFilter"
     *     }],
     *     "parentId": "application"
     * }
     * </pre>
     */
    @Test
    public void testGetFilterMappings() {
        ContextFilterMappings filterMappings = SpringUtils.getFilterMappings();

        System.out.println("filterMappings:" + filterMappings);
    }

    /**
     * <pre>
     *   {
     *     "servlets": [{
     *         "name": "default",
     *         "className": "org.apache.catalina.servlets.DefaultServlet",
     *         "mappings": []
     *     }, {
     *         "name": "dispatcherServlet",
     *         "className": "org.springframework.web.servlet.DispatcherServlet",
     *         "mappings": ["/"]
     *     }],
     *     "parentId": "application"
     *  }
     * </pre>
     */
    @Test
    public void testGetServletMappings() {
        ContextServletMappings servletMappings = SpringUtils.getServletMappings();

        System.out.println("servletMappings:" + servletMappings);
    }


    @Test
    public void testGetAllAppContext() {
        Map<String, ApplicationContext> appContexts =
                SpringContextHolder.getAllAppContexts();

        Assert.assertTrue(!appContexts.isEmpty());
    }

    @Test
    public void testPresent() {
        Assert.assertTrue(SpringUtils.SPRING_FREMAE_PRESENT);
        Assert.assertTrue(SpringUtils.SPRING_WEB_FRAME_PRESENT);
    }
}


