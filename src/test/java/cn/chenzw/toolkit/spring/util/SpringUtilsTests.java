package cn.chenzw.toolkit.spring.util;

import cn.chenzw.toolkit.domain.entity.Book;
import cn.chenzw.toolkit.domain.entity.User;
import cn.chenzw.toolkit.spring.config.AppConfig;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = {AppConfig.class})
public class SpringUtilsTests {

    @Autowired
    ApplicationContext applicationContext;

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


}
