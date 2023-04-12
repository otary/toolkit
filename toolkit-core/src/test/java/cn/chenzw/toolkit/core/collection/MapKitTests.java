package cn.chenzw.toolkit.core.collection;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

@RunWith(JUnit4.class)
public class MapKitTests {

    @Test
    public void testToProperties() {
        Map<String, Object> kvMap = new HashMap() {
            {
                put("id", 3);
                put("name", "zhangsan3");
            }
        };
        Properties properties = MapKit.toProperties(kvMap);

        Assert.assertEquals("{name=zhangsan3, id=3}", properties.toString());
    }

    @Test
    public void testGetDeepValue() {
        Map<String, Object> map = new HashMap<String, Object>() {
            {
                put("a", new HashMap<String, Object>(){
                    {
                        put("a1", "a1");
                        put("a2", "a2");
                    }
                });
                put("b", new HashMap<String, Object>(){
                    {
                        put("b1", "b1");
                        put("b2", "b2");
                    }
                });
            }
        };

        Object value = MapKit.getDeepValue(map, new String[]{"c", "b2"});
        Assert.assertNull(value);

        Object value2 = MapKit.getDeepValue(map, new String[]{"a", "a1"});
        Assert.assertEquals("a1", value2);

        Object value3 = MapKit.getDeepValue(map, new String[]{"b", "b2"});
        Assert.assertEquals("b2", value3);

        Object value3_2 = MapKit.getDeepValue(map, "b", "b2");
        Assert.assertEquals("b2", value3_2);
    }
}
