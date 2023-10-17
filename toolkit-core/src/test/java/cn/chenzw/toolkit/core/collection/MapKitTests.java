package cn.chenzw.toolkit.core.collection;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

@Slf4j
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
                put("a", new HashMap<String, Object>() {
                    {
                        put("a1", "a1");
                        put("a2", "a2");
                    }
                });
                put("b", new HashMap<String, Object>() {
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

    @Test
    public void testAddDeepValue() {
        Map<String, Object> map = new HashMap<String, Object>() {
            {
                put("a", new HashMap<String, Object>() {
                    {
                        put("a1", "a1");
                        put("a2", "a2");
                    }
                });
                put("b", new HashMap<String, Object>() {
                    {
                        put("b1", "b1");
                        put("b2", "b2");
                    }
                });
            }
        };
        MapKit.addDeepValue(map, new String[]{"a", "a3", "aa3"}, "xxx");

        log.info("addDeepValue => {}", map);
    }

    @Test
    public void testFlatDiff() {
        Map<String, Object> mapA = new HashMap<String, Object>() {
            {
                put("a", new HashMap<String, Object>() {
                    {
                        put("a1", "a1");
                        put("a2", new HashMap<String, Object>() {
                            {
                                put("aa2", "aa2");
                                put("aa3", "aa3");
                            }
                        });
                    }
                });
                put("b", new HashMap<String, Object>() {
                    {
                        put("b1", "b1");
                        put("b2", "b2");
                    }
                });
            }
        };

        Map<String, Object> mapB = new HashMap<String, Object>() {
            {
                put("a", new HashMap<String, Object>() {
                    {
                        put("a1", "a1");
                        put("a2", "a2");
                    }
                });
                put("b", new HashMap<String, Object>() {
                    {
                        put("b1", "b1");
                        put("b2", "b2");
                        put("b3", "b3");
                    }
                });
            }
        };

        Map flatDiffMap = MapKit.flatDiff(mapA, mapB);

        log.info("flatDiff => {}", flatDiffMap);
    }

    @Test
    public void testDiff() throws JsonProcessingException {
        /*String json1 = "{\"spring\":{\"profiles\":{\"include\":\"base\"},\"datasource\":{\"type\":\"com.zaxxer.hikari.HikariDataSource\",\"driver-class-name\":\"com.mysql.cj.jdbc.Driver\"}}}";
        String json2 = "{\"spring\":{\"datasource\":{\"type\":\"com.zaxxer.hikari.HikariDataSource\",\"driver-class-name\":\"com.mysql.cj.jdbc.Driver\",\"url\":\"jdbc:mysql://10.10.10.10:3306/test?allowMultiQueries=true&useUnicode=true&characterEncoding=UTF-8&zeroDateTimeBehavior=convertToNull&serverTimezone=Asia/Shanghai\",\"username\":\"root\",\"password\":\"xxxx\"}}}";

        ObjectMapper objectMapper = new ObjectMapper();
        Map mapA = objectMapper.readValue(json1, Map.class);
        Map mapB = objectMapper.readValue(json2, Map.class);*/

        Map<String, Object> mapA = new HashMap<String, Object>() {
            {
                put("a", new HashMap<String, Object>() {
                    {
                        put("a1", "a1");
                        put("a2", new HashMap<String, Object>() {
                            {
                                put("aa2", "aa2");
                                put("aa3", "aa3");
                            }
                        });
                    }
                });
                put("b", new HashMap<String, Object>() {
                    {
                        put("b1", "b1");
                        put("b2", "b2");
                    }
                });
            }
        };

        Map<String, Object> mapB = new HashMap<String, Object>() {
            {
                put("a", new HashMap<String, Object>() {
                    {
                        put("a1", "a1");
                        put("a2", "a2");
                    }
                });
                put("b", new HashMap<String, Object>() {
                    {
                        put("b1", "b1");
                        put("b2", "b2");
                        put("b3", "b3");
                    }
                });
            }
        };

        Map diffMap1 = MapKit.diff(mapA, mapB);
        Map diffMap2 = MapKit.diff(mapB, mapA);
        log.info("diff1 => {}", diffMap1);
        log.info("diff2 => {}", diffMap2);
    }
}
