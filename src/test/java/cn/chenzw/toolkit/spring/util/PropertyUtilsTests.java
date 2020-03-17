package cn.chenzw.toolkit.spring.util;

import cn.chenzw.toolkit.spring.config.AppConfig;
import cn.chenzw.toolkit.spring.config.WebConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.Map;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = {AppConfig.class, WebConfig.class})
@EnableConfigurationProperties
public class PropertyUtilsTests {

    @Autowired
    ApplicationContext applicationContext;

    @Test
    public void testGet() {
        Map map = PropertyUtils.get(applicationContext, "spring.application.name", Map.class);
        System.out.println(map);
    }
}
