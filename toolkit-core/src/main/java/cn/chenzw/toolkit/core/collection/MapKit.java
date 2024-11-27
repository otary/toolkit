package cn.chenzw.toolkit.core.collection;

import cn.chenzw.toolkit.core.collection.support.MapDiff;
import com.google.common.collect.MapDifference;
import com.google.common.collect.Maps;
import org.apache.commons.lang3.StringUtils;
import org.checkerframework.checker.units.qual.K;

import java.util.*;

/**
 * Map对象扩展
 *
 * @author chenzw
 * @since 1.0.3
 */
public final class MapKit {

    private MapKit() {
    }


    /**
     * Map对象 => Properties对象
     *
     * @param map
     * @param <K>
     * @param <V>
     * @return
     */
    public static <K, V> Properties toProperties(final Map<K, V> map) {
        final Properties answer = new Properties();
        if (map != null) {
            for (final Map.Entry<K, V> entry : map.entrySet()) {
                answer.put(entry.getKey(), entry.getValue());
            }
        }
        return answer;
    }

    /**
     * 获取第一项值
     *
     * @param map
     * @param <K>
     * @param <V>
     * @return
     */
    public static <K, V> Object getFirstValue(final Map<K, V> map) {
        Collection<V> values = map.values();
        for (V value : values) {
            return value;
        }
        return null;
    }

    /**
     * 获取深层值
     *
     * <pre>
     *     Map<String, Object> map = new HashMap<String, Object>() {
     *             {
     *                 put("a", new HashMap<String, Object>(){
     *                     {
     *                         put("a1", "a1");
     *                         put("a2", "a2");
     *                     }
     *                 });
     *                 put("b", new HashMap<String, Object>(){
     *                     {
     *                         put("b1", "b1");
     *                         put("b2", "b2");
     *                     }
     *                 });
     *             }
     *         };
     *  MapKit.getDeepValue(map, new String[]{"a", "a1"}) = "a1"
     *  MapKit.getDeepValue(map, new String[]{"b", "b2"}) = "b2"
     * </pre>
     *
     * @param map
     * @param deepKeys
     * @param <T>
     * @return
     */
    public static final <T> T getDeepValue(Map<String, T> map, String... deepKeys) {
        Map<String, T> tmp = map;
        for (int i = 0; i < deepKeys.length; i++) {
            Object value = tmp.get(deepKeys[i]);
            // 最后一个元素
            if (i == deepKeys.length - 1) {
                return (T) value;
            }

            if (!(value instanceof Map)) {
                return null;
            }
            tmp = (Map<String, T>) value;
        }
        return null;
    }

    /**
     * 添加深层值
     *
     * @param map
     * @param deepKeys
     * @param value
     * @param <K>
     * @param <V>
     */
    public static <K, V> void addDeepValue(Map map, K[] deepKeys, V value) {
        Map tmp = map;
        for (int i = 0; i < deepKeys.length; i++) {
            K key = deepKeys[i];
            Object item = tmp.get(key);

            if (item == null) {
                item = new HashMap<>();
                tmp.put(key, item);
            }

            // 最后一个元素
            if (i == deepKeys.length - 1) {
                tmp.put(key, value);
            }

            tmp = (Map) item;
        }
    }

    /**
     * 扁平化差异
     *
     * @param left
     * @param right
     * @return
     */
    public static Map flatDiff(Map left, Map right) {
        return new MapDiff().flatDiff(left, right);
    }

    /**
     * 提取差异值
     *
     * @param left
     * @param right
     * @return
     */
    public static Map diff(Map left, Map right) {
        return new MapDiff().diff(left, right);
    }
}
