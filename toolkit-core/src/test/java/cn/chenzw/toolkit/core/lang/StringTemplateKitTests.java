package cn.chenzw.toolkit.core.lang;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.HashMap;
import java.util.Map;

@RunWith(JUnit4.class)
public class StringTemplateKitTests {

    @Test
    public void testCompile() {
        Map<String, Object> map = new HashMap<String, Object>() {
            {
                put("name", "zhangsan");
                put("age", 10.0);
                put("sex", "男");
            }
        };
        String result = StringTemplateKit.compile("姓名:${name}, 年龄: ${age}, 性别: ${sex}", map);

        Assert.assertEquals("姓名:zhangsan, 年龄: 10.0, 性别: 男", result);
    }
}
