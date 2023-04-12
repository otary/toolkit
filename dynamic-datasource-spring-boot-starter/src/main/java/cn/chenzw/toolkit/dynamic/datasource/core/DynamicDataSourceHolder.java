package cn.chenzw.toolkit.dynamic.datasource.core;

/**
 * 线程级数据源持有者
 *
 * @author chenzw
 * @since 1.0.3
 */
public abstract class DynamicDataSourceHolder {

    private static final ThreadLocal<String> dsTL = new ThreadLocal<>();

    /**
     * 设置数据源
     */
    public static void set(String dataSource) {
        dsTL.set(dataSource);
    }

    /**
     * 获取数据源
     *
     * @return
     */
    public static String get() {
        return dsTL.get();
    }

    /**
     * 清除数据源
     */
    public static void clear() {
        dsTL.remove();
    }
}
