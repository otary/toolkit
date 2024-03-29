package cn.chenzw.toolkit.core.lang;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

/**
 * 泛型类工具
 *
 * @author chenzw
 */
public abstract class GenericKit {

    private GenericKit() {
    }

    /**
     * 获取集合的泛型类型
     * <pre>
     * List<User> users = new ArrayList<>();
     * Class aClass = GenericKit.getSuperClassGenericType(users); // => User.class
     * </pre>
     *
     * @param list
     * @param <T>
     * @return
     */
    public static final <T> Class getSuperClassGenericType(List<T> list) {
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
    public static final Class getSuperClassGenericType(Class clazz) {
        return getSuperClassGenericType(clazz, 0);
    }

    /**
     * 获取泛型的参数类型
     *
     * @param clazz
     * @param index
     * @return
     * @throws IndexOutOfBoundsException
     */
    public static Class getSuperClassGenericType(Class clazz, int index)
            throws IndexOutOfBoundsException {

        Type genType = clazz.getGenericSuperclass();
        if (!(genType instanceof ParameterizedType)) {
            return Object.class;
        }
        Type[] params = ((ParameterizedType) genType).getActualTypeArguments();
        if (index >= params.length || index < 0) {
            return Object.class;
        }

        if (params[index] instanceof ParameterizedType) {
            return (Class) ((ParameterizedType) params[index]).getRawType();
        }

        if (!(params[index] instanceof Class)) {
            return Object.class;
        }
        return (Class) params[index];
    }
}
