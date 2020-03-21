package cn.chenzw.toolkit.spring.util;

import cn.chenzw.toolkit.spring.SpringBootStarter;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringBootStarter.class)
//@WebAppConfiguration
//@ContextConfiguration(classes = {AppConfig.class, WebConfig.class})
public class PropertyUtilsTests {

    @Autowired
    ApplicationContext applicationContext;

    @Test
    public void testGet() {
        Map map = PropertyUtils.get(applicationContext, "spring.application.name", Map.class);
        System.out.println(map);
    }
}
