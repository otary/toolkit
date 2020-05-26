package cn.chenzw.toolkit.commons;


import java.util.Collection;
import java.util.Map;
import java.util.Properties;

/**
 * Map对象扩展
 *
 * @author chenzw
 * @since 1.0.3
 */
public final class MapExtUtils {

    private MapExtUtils() {
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

}
