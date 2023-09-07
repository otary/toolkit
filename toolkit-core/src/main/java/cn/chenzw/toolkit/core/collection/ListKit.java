package cn.chenzw.toolkit.core.collection;

import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.Field;
import java.util.*;
import java.util.function.BiFunction;
import java.util.function.BiPredicate;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * org.apache.commons.collections.ListUtils扩展类
 *
 * @author chenzw
 */
public abstract class ListKit {

    private ListKit() {
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
            return StringUtils.join(
                    fieldValues.toArray(new String[fieldValues.size()]),
                    separatorChars
            );
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
    public static final <T> boolean contains(List<T> list, Map<String, Object> kvMap)
            throws NoSuchFieldException, IllegalAccessException {
        if (list != null && !list.isEmpty()) {
            Map<Field, Object> fvMap = getFieldMap(list, kvMap);

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
        List<T> found = new ArrayList<T>();
        if (list != null && !list.isEmpty()) {
            Map<Field, Object> fvMap = getFieldMap(list, kvMap);
            for (T item : list) {
                if (contains(fvMap, item)) {
                    found.add(item);
                }
            }
        }
        return found;
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

    /**
     * 获取两个集合的差集（listA - listB）
     *
     * @param listA
     * @param listB
     * @return
     */
    public static final <T1, T2> List<T1> subtract(Collection<T1> listA, Collection<T2> listB, BiFunction<T1, T2, Boolean> matchesFunc) {
        Objects.requireNonNull(listA, "listA must not be null!");
        Objects.requireNonNull(listB, "listB must not be null!");
        return listA.stream().filter(item -> listB.stream().allMatch(item2 -> !matchesFunc.apply(item, item2))).collect(Collectors.toList());
    }


    /**
     * 获取两个集合的交集（listA的元素同时存在于listB中）
     *
     * @param listA
     * @param listB
     * @param <T>
     * @return
     */
    public static final <T> List<T> intersection(Collection<T> listA, Collection<T> listB, BiFunction<T, T, Boolean> matchesFunc) {
        Objects.requireNonNull(listA, "listA must not be null!");
        Objects.requireNonNull(listB, "listB must not be null!");
        return listA.stream().filter(item -> listB.stream().anyMatch(item2 -> matchesFunc.apply(item, item2))).collect(Collectors.toList());
    }


    /**
     * 集合去重
     *
     * @param list
     * @param matchesFunc
     * @param <T>
     * @return
     */
    public static final <T, U extends Comparable<? super U>> List<T> unique(Collection<T> list, Function<T, U> matchesFunc) {
        Objects.requireNonNull(list);
        return list.stream().collect(
                Collectors.collectingAndThen(Collectors.toCollection(
                        () -> new TreeSet<>(Comparator.comparing(matchesFunc))), ArrayList::new)
        );
    }

    /**
     * 判断数组是否相等
     *
     * @param listA
     * @param listB
     * @param matches
     * @param <T1>
     * @param <T2>
     * @return
     */
    public static final <T1, T2> boolean equals(Collection<T1> listA, Collection<T2> listB, BiPredicate<T1, T2> matches) {
        if (listA.size() != listB.size()) {
            return false;
        }

        for (T2 itemB : listB) {
            boolean anyMatch = listA.stream().anyMatch((itemA) -> matches.test(itemA, itemB));
            if (!anyMatch) {
                return false;
            }
        }
        return true;
    }

    /**
     * 是否包含子项
     *
     * @param list
     * @param item
     * @param matches
     * @param <T>
     * @return
     */
    public static final <T> boolean contains(Collection<T> list, T item, BiPredicate<T, T> matches) {
        return list.stream().anyMatch((_item) -> matches.test(item, _item));
    }

    /**
     * 去重
     *
     * @param list
     * @param keyExtractor
     * @param <T>
     * @param <U>
     * @return
     */
    public static final <T, U extends Comparable<? super U>> List<T> distinct(Collection<T> list, Function<? super T, ? extends U> keyExtractor) {
        return list.stream().collect(Collectors.collectingAndThen(
                Collectors.toCollection(() -> new TreeSet<T>(Comparator.comparing(keyExtractor))), ArrayList::new));
    }

    private static final <T> Map<Field, Object> getFieldMap(List<T> list, Map<String, Object> kvMap) throws NoSuchFieldException {
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
