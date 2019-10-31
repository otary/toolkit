package cn.chenzw.toolkit.commons;

import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.Field;
import java.util.*;

/**
 * org.apache.commons.collections.ListUtils扩展类
 *
 * @author chenzw
 */
public class ListExtUtils {

    private ListExtUtils() {
    }

    /**
     * 提取列表对象中某个字段的值，并拼接成字符串
     *
     * @param list
     * @param fieldName      要提取值的字段名称
     * @param separatorChars 拼接的间隔符
     * @param <T>
     * @return
     * @throws NoSuchFieldException
     * @throws IllegalAccessException
     */
    public static final <T> String joinFieldValue(List<T> list, String fieldName, String separatorChars)
            throws NoSuchFieldException, IllegalAccessException {
        List<String> fieldValues = new ArrayList<>();
        if (list != null && !list.isEmpty()) {
            Field field = list.get(0).getClass().getDeclaredField(fieldName);
            field.setAccessible(true);
            for (T item : list) {
                fieldValues.add(String.valueOf(field.get(item)));
            }
            return StringUtils.join(fieldValues.toArray(new String[fieldValues.size()]), separatorChars);
        }
        return "";
    }

    /**
     * 提取列表对象中某个字段的值，并拼接成字符串（使用逗号间隔）
     *
     * @param list
     * @param fieldName 要提取值的字段名称
     * @param <T>
     * @return
     * @throws NoSuchFieldException
     * @throws IllegalAccessException
     */
    public static final <T> String joinFieldValue(List<T> list, String fieldName)
            throws NoSuchFieldException, IllegalAccessException {
        return joinFieldValue(list, fieldName, ",");
    }

    /**
     * 判断对象集合中是否包含某个元素
     *
     * @param list      集合对象
     * @param fieldName 字段名称
     * @param value     查找的值
     * @param <T>
     * @return
     * @throws NoSuchFieldException
     * @throws IllegalAccessException
     */
    public static final <T> boolean contains(List<T> list, String fieldName, Object value)
            throws NoSuchFieldException, IllegalAccessException {
        if (list != null && !list.isEmpty()) {
            Field field = list.get(0).getClass().getDeclaredField(fieldName);
            field.setAccessible(true);
            for (T item : list) {
                if (Objects.equals(field.get(item), value)) {
                    return true;
                }
            }
        }
        return false;
    }


    /**
     * 判断对象集合中是否包含某些元素
     *
     * @param list  集合对象
     * @param kvMap 待查找的包含的元素 > key: 对象字段名; value: 对象值
     * @param <T>
     * @return
     */
    public static final <T> boolean contains(List<T> list, Map<String, Object> kvMap) throws NoSuchFieldException, IllegalAccessException {
        if (list != null && !list.isEmpty()) {
            Map<Field, Object> fvMap = getFielddMap(list, kvMap);

            for (T item : list) {
                if (contains(fvMap, item)) {
                    return true;
                }
            }
        }
        return false;
    }


    /**
     * 对象集合中查找匹配的元素
     *
     * @param list  集合对象
     * @param kvMap 待查找的包含的元素 > key: 对象字段名; value: 对象值
     * @param <T>
     * @return
     * @throws NoSuchFieldException
     * @throws IllegalAccessException
     */
    public static final <T> List<T> find(List<T> list, Map<String, Object> kvMap) throws NoSuchFieldException, IllegalAccessException {
        List<T> finded = new ArrayList<T>();
        if (list != null && !list.isEmpty()) {
            Map<Field, Object> fvMap = getFielddMap(list, kvMap);
            for (T item : list) {
                if (contains(fvMap, item)) {
                    finded.add(item);
                }
            }
        }
        return finded;
    }

    /**
     * 对象集合中查找匹配的第一个元素
     *
     * @param list  集合对象
     * @param kvMap 待查找的包含的元素 > key: 对象字段名; value: 对象值
     * @param <T>
     * @return
     * @throws NoSuchFieldException
     * @throws IllegalAccessException
     */
    public static final <T> T findFirst(List<T> list, Map<String, Object> kvMap)
            throws NoSuchFieldException, IllegalAccessException {
        List<T> rets = find(list, kvMap);

        if (!rets.isEmpty()) {
            return rets.get(0);
        }

        return null;
    }


    private static final <T> Map<Field, Object> getFielddMap(List<T> list, Map<String, Object> kvMap) throws NoSuchFieldException {
        Map<Field, Object> fvMap = new HashMap<>();
        for (Map.Entry<String, Object> kvEntity : kvMap.entrySet()) {
            Field field = list.get(0).getClass().getDeclaredField(kvEntity.getKey());
            field.setAccessible(true);
            fvMap.put(field, kvEntity.getValue());
        }

        return fvMap;
    }

    private static <T> boolean contains(Map<Field, Object> fvMap, T item) throws IllegalAccessException {
        for (Map.Entry<Field, Object> fvEntity : fvMap.entrySet()) {
            if (!Objects.equals(fvEntity.getKey().get(item), fvEntity.getValue())) {
                return false;
            }
        }
        return true;
    }

}
