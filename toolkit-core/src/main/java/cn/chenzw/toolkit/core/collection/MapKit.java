package cn.chenzw.toolkit.core.collection;

import java.util.Collection;
import java.util.Map;
import java.util.Properties;

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


}
