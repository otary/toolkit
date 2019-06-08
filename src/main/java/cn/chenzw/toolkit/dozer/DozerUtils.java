package cn.chenzw.toolkit.dozer;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;

import java.util.ArrayList;
import java.util.List;

/**
 * 对象映射工具类
 * @author chenzw
 */
public abstract class DozerUtils {


    /**
     * List对象互转
     * @param mapper {@link Mapper}
     * @param sourceList 源List对象
     * @param targetClass 目标对象类型
     * @return 目标List对象
     */
    public static <T, S> List<T> mapList(final Mapper mapper, List<S> sourceList, Class<T> targetClass) {
        List<T> targetList = new ArrayList<>();
        for (S s : sourceList) {
            targetList.add(mapper.map(s, targetClass));
        }
        return targetList;
    }

    public static <T, S> List<T> mapList(List<S> sourceList, Class<T> targetClass) {
        Mapper mapper = new DozerBeanMapper();
        return mapList(mapper, sourceList, targetClass);
    }
}
