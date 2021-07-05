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

    @Test
    public void testGetDeepValue() {
        Map<String, Object> aMap = new HashMap<>();
        aMap.put("a1", "a1");
        aMap.put("a2", "a2");

        Map<String, Object> bMap = new HashMap<>();
        bMap.put("b1", "b1");
        bMap.put("b2", "b2");

        Map<String, Object> map = new HashMap<>();
        map.put("a", aMap);
        map.put("b", bMap);
        map.put("c", "c");

        Object value = MapExtUtils.getDeepValue(map, new String[]{"c", "b2"});
        Assert.assertNull(value);

        Object value2 = MapExtUtils.getDeepValue(map, new String[]{"a", "a1"});
        Assert.assertEquals("a1", value2);

        Object value3 = MapExtUtils.getDeepValue(map, new String[]{"b", "b2"});
        Assert.assertEquals("b2", value3);
    }
}
