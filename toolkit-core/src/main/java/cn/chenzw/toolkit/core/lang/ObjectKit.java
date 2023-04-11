package cn.chenzw.toolkit.core.lang;

import cn.chenzw.toolkit.core.collection.ArrayKit;

import java.io.*;
import java.lang.reflect.InvocationTargetException;

/**
 * Object扩展工具类
 *
 * @author chenzw
 */
public class ObjectKit {

    /**
     * 获取对象的内存地址
     * <pre>
     * String a1 = "hello";
     * String a2 = "hello";
     * ObjectKit.identityHashCode(a1) == ObjectKit.identityHashCode(a2); // 字符串常量池
     *
     * String a3 = new String("hello");
     * ObjectKit.identityHashCode(a1) != ObjectKit.identityHashCode(a3); // 使用new分配新的内存空间
     * </pre>
     *
     * @param o
     * @return
     */
    public static int identityHashCode(Object o) {
        return System.identityHashCode(o);
    }

    /**
     * 对象克隆
     * <br/>
     * <p>对象必须是以下任意一种实现才能进行克隆</p>
     * <ul>
     *   <li>对象实现Cloneable接口 - 调用其clone方法</li>
     *   <li>对象实现Serializable接口 - 序列化和反序列化</li>
     * </ul>
     *
     * @param o
     * @param <T>
     * @return
     */
    public static <T> T clone(T o) {
        T result = ArrayKit.clone(o);
        if (result == null) {
            if (o instanceof Cloneable) {
                try {
                    ReflectKit.invoke(o, "clone");
                } catch (InvocationTargetException | IllegalAccessException e) {
                    e.printStackTrace();
                }
            } else if (o instanceof Serializable) {
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                try (ObjectOutputStream oos = new ObjectOutputStream(baos)) {
                    oos.writeObject(o);
                    oos.flush();
                    ObjectInputStream ois = new ObjectInputStream(
                            new ByteArrayInputStream(baos.toByteArray())
                    );
                    return (T) ois.readObject();
                } catch (IOException | ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }
        return result;
    }
}
