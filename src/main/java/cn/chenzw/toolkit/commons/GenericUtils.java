package cn.chenzw.toolkit.commons;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

/**
 * 泛型类工具
 *
 * @author chenzw
 */
public abstract class GenericUtils {

    private GenericUtils() {
    }

    /**
     * 获取集合的泛型类型
     *
     * @param list
     * @param <T>
     * @return
     */
    public static final <T> Class getSuperClassGenricType(List<T> list) {
        if (list != null && list.size() > 0) {
            return list.get(0).getClass();
        } else {
            // @TODO 集合为空时无法获取泛型类型
            return Object.class;
        }
    }


    /**
     * 获取泛型的参数类型
     *
     * @param clazz
     * @return
     */
    public static final Class getSuperClassGenricType(Class clazz) {
        return getSuperClassGenricType(clazz, 0);
    }

    /**
     * 获取泛型的参数类型
     *
     * @param clazz
     * @param index
     * @return
     * @throws IndexOutOfBoundsException
     */
    public static Class getSuperClassGenricType(Class clazz, int index)
            throws IndexOutOfBoundsException {

        // @TODO 暂时无效

        Type genType = clazz.getGenericSuperclass();
        if (!(genType instanceof ParameterizedType)) {
            return Object.class;
        }
        Type[] params = ((ParameterizedType) genType).getActualTypeArguments();
        if (index >= params.length || index < 0) {
            return Object.class;
        }
        if (!(params[index] instanceof Class)) {
            return Object.class;
        }
        return (Class) params[index];
    }
}
