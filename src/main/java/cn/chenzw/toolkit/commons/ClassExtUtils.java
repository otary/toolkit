package cn.chenzw.toolkit.commons;


import java.net.URL;
import java.security.CodeSource;
import java.security.ProtectionDomain;

/**
 * org.apache.commons.lang3.ClassUtils扩展类
 * @author chenzw
 */
public abstract class ClassExtUtils {

    private ClassExtUtils() {
    }

    /**
     * 判断指定的类是否存在
     * @param className
     * @return
     */
    public static boolean isPresent(String className) {
        return isPresent(className, null);
    }

    public static boolean isPresent(String className, ClassLoader classLoader) {
        try {
            forName(className, classLoader);
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

    /**
     * 反射类
     * @param name
     * @return
     * @throws ClassNotFoundException
     */
    public static Class<?> forName(String name) throws ClassNotFoundException {
        return forName(name, null);
    }

    public static Class<?> forName(String name, ClassLoader classLoader) throws ClassNotFoundException {
        if (classLoader == null) {
            classLoader = getDefaultClassLoader();
        }
        try {
            return (classLoader != null ? classLoader.loadClass(name) : Class.forName(name));
        } catch (ClassNotFoundException ex) {
            throw ex;
        }
    }

    private static ClassLoader getDefaultClassLoader() {
        ClassLoader cl = null;
        try {
            cl = Thread.currentThread().getContextClassLoader();
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (cl == null) {
            cl = ClassExtUtils.class.getClassLoader();
            if (cl == null) {
                try {
                    cl = ClassLoader.getSystemClassLoader();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }
        return cl;
    }

    /**
     * 查找指定的类的来源Jar包
     *
     * @param cls
     * @return
     */
    public static URL findSourceJar(Class cls) {
        ProtectionDomain protectionDomain = cls.getProtectionDomain();
        if (protectionDomain == null) {
            return null;
        }

        CodeSource codeSource = protectionDomain.getCodeSource();
        if (codeSource == null) {
            return null;
        }
        return codeSource.getLocation();
    }

}
