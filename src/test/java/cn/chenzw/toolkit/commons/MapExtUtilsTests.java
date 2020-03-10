package cn.chenzw.toolkit.commons;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

@RunWith(JUnit4.class)
public class MapExtUtilsTests {

    @Test
    public void testToProperties() {
        Map<String, Object> kvMap = new HashMap() {
            {
                put("id", 3);
                put("name", "zhangsan3");
            }
        };
        Properties properties = MapExtUtils.toProperties(kvMap);

        Assert.assertEquals(properties.toString(), "{name=zhangsan3, id=3}");
    }
}
