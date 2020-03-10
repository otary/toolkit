package cn.chenzw.toolkit.commons;


import java.util.Map;
import java.util.Properties;

/**
 * Map对象扩展
 *
 * @author chenzw
 */
public final class MapExtUtils {

    private MapExtUtils() {
    }


    public static <K, V> Properties toProperties(final Map<K, V> map) {
        final Properties answer = new Properties();
        if (map != null) {
            for (final Map.Entry<K, V> entry : map.entrySet()) {
                answer.put(entry.getKey(), entry.getValue());
            }
        }
        return answer;
    }

}
