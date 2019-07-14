package cn.chenzw.toolkit.commons;

import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * org.apache.commons.collections.ListUtils扩展类
 * @author chenzw
 */
public class ListExtUtils {

    /**
     * 提取列表对象中某个字段的值，并拼接成字符串
     *
     * @param list
     * @param fieldName 要提取值的字段名称
     * @param separatorChars 拼接的间隔符
     * @param <T>
     * @return
     * @throws NoSuchFieldException
     * @throws IllegalAccessException
     */
    public static final <T> String joinFieldValue(List<T> list, String fieldName, String separatorChars)
            throws NoSuchFieldException, IllegalAccessException {
        List<String> fieldValues = new ArrayList<String>();
        if (list.size() > 0) {
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
}
