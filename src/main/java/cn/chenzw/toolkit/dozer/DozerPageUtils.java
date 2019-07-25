package cn.chenzw.toolkit.dozer;

import com.github.pagehelper.Page;
import org.dozer.Mapper;

import java.util.ArrayList;
import java.util.List;

/**
 * 兼容com.github.pagehelper.Page的对象转换工具
 *
 * @author chenzw
 */
public abstract class DozerPageUtils {

    /**
     * List对象互转
     *
     * @param mapper      {@link Mapper}
     * @param sourceList  源List对象
     * @param targetClass 目标对象类型
     * @return 目标List对象
     */
    public static <T, S> List<T> mapList(final Mapper mapper, List<S> sourceList, Class<T> targetClass) {
        List<T> targetList = new ArrayList<>();
        if (sourceList instanceof Page) {
            Page<S> sourcePage = (Page<S>) sourceList;
            Page<T> targetPage = mapper.map(sourcePage, Page.class);
            targetPage.addAll(targetList);
            return targetPage;
        } else {
            targetList = doMapList(mapper, sourceList, targetClass);
        }
        return targetList;
    }

    private static <T, S> List<T> doMapList(final Mapper mapper, List<S> sourceList, Class<T> targetClass) {
        List<T> targetList = new ArrayList<>();
        for (S s : sourceList) {
            targetList.add(mapper.map(s, targetClass));
        }
        return targetList;
    }


}
