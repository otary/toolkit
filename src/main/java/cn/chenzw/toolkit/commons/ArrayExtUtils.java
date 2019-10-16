package cn.chenzw.toolkit.commons;

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
}

