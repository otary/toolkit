package cn.chenzw.toolkit.commons;

import org.apache.commons.lang.StringUtils;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 数组工具类
 *
 * @author chenzw
 */
public class ArrayExtUtils {


    /**
     * 将数组切割成指定大小块
     *
     * @param data
     * @param segmentLen 每块的长度
     * @param <T>
     * @return
     */
    public static <T> List<T[]> split(T[] data, int segmentLen) {
        List<T[]> result = new ArrayList<>();
        int dataSize = data.length;
        if (dataSize < segmentLen) {
            result.add(data);
        } else {
            int segmentCount = dataSize / segmentLen;
            segmentCount = dataSize % segmentLen == 0 ? segmentCount : segmentCount + 1;

            for (int i = 0; i < segmentCount; i++) {
                if (i == segmentCount - 1) {
                    result.add(Arrays.copyOfRange(data, segmentLen * i, dataSize));
                } else {
                    result.add(Arrays.copyOfRange(data, segmentLen * i, segmentLen * (i + 1)));
                }
            }
        }
        return result;
    }


    /**
     * 数组克隆
     *
     * @param o
     * @param <T>
     * @return
     */
    public static <T> T clone(T o) {
        if (o == null) {
            return null;
        }

        if (o.getClass().isArray()) {
            final Object result;
            final Class<?> componentType = o.getClass().getComponentType();
            if (componentType.isPrimitive()) {// 原始类型
                int length = Array.getLength(o);
                result = Array.newInstance(componentType, length);
                while (length-- > 0) {
                    Array.set(result, length, Array.get(o, length));
                }
            } else {
                result = ((Object[]) o).clone();
            }
            return (T) result;
        }

        return null;
    }

    /**
     * 去除数组项前后空格
     *
     * @param data
     * @return
     */
    public static String[] trim(String[] data) {
        String[] result = new String[data.length];
        for (int i = 0; i < data.length; i++) {
            result[i] = StringUtils.trim(data[i]);
        }
        return result;
    }
}

