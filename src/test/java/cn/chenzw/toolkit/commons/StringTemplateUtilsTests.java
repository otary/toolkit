package cn.chenzw.toolkit.commons;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.HashMap;
import java.util.Map;

@RunWith(JUnit4.class)
public class StringTemplateUtilsTests {

    @Test
    public void testProcessTemplate() {
        Map<String, Object> map = new HashMap<>();
        map.put("name", "zhangsan");
        map.put("age", 10.0);
        map.put("sex", "男");
        String result = StringTemplateUtils.processTemplate("姓名:${name}, 年龄: ${age}, 性别: ${sex}", map);

        Assert.assertEquals("姓名:zhangsan, 年龄: 10.0, 性别: 男", result);
    }
}
