package cn.chenzw.toolkit.commons;


/**
 * @author chenzw
 */
public abstract class ClassExtUtils {


    public static boolean isPresent(String className) {
        return isPresent(className, null);
    }

    public static boolean isPresent(String className, ClassLoader classLoader) {
        try {
            forName(className, classLoader);
            return true;
        } catch (Throwable ex) {
            return false;
        }
    }

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
        } catch (Throwable e) {

        }

        if (cl == null) {
            cl = ClassExtUtils.class.getClassLoader();
            if (cl == null) {
                try {
                    cl = ClassLoader.getSystemClassLoader();
                } catch (Throwable ex) {

                }
            }
        }
        return cl;

    }
}
