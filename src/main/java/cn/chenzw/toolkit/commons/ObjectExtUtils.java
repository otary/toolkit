package cn.chenzw.toolkit.commons;

import java.io.*;
import java.lang.reflect.InvocationTargetException;

/**
 * Object扩展工具类
 *
 * @author chenzw
 */
public class ObjectExtUtils {

    /**
     * 获取对象的内存地址
     *
     * @param o
     * @return
     */
    public static int identityHashCode(Object o) {
        return System.identityHashCode(o);
    }

    /**
     * 对象克隆
     * <ul>
     * <li>对象实现Cloneable接口 -  调用其clone方法</li>
     * <li>对象实现Serializable接口 - 序列化和反序列化</li>
     * </ul>
     *
     * @param o
     * @param <T>
     * @return
     */
    public static <T> T clone(T o) {
        T result = ArrayExtUtils.clone(o);
        if (result == null) {
            if (o instanceof Cloneable) {
                try {
                    ReflectExtUtils.invoke(o, "clone");
                } catch (InvocationTargetException | IllegalAccessException e) {
                    e.printStackTrace();
                }
            } else if (o instanceof Serializable) {
                ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
                try (ObjectOutputStream out = new ObjectOutputStream(byteOut)) {
                    out.writeObject(o);
                    out.flush();
                    ObjectInputStream in = new ObjectInputStream(new ByteArrayInputStream(byteOut.toByteArray()));
                    return (T) in.readObject();
                } catch (IOException | ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }
        return result;
    }
}
