package cn.chenzw.toolkit.core.system;

import com.sun.management.OperatingSystemMXBean;

/**
 * 操作系统工具类
 *
 * @author chenzw
 * @since 1.0.3
 */
public class OSKit {

    /**
     * 获取操作系统名称
     *
     * @return 操作系统名称
     * @since 1.0.3
     */
    public static String getName() {
        // 等同于 System.getProperty("os.name")
        return SystemKit.getOperatingSystemMXBean().getName();
    }

    /**
     * 获取操作系统架构
     *
     * @return 操作系统架构
     * @since 1.0.3
     */
    public static String getArch() {
        // 等同于 System.getProperty("os.arch")
        return SystemKit.getOperatingSystemMXBean().getArch();
    }

    /**
     * 获取操作系统版本
     *
     * @return 操作系统版本
     * @since 1.0.3
     */
    public static String getVersion() {
        // 等同于 System.getProperty("os.version")
        return SystemKit.getOperatingSystemMXBean().getVersion();
    }


    /**
     * 获取操作系统可用内核数
     *
     * @return
     * @since 1.0.3
     */
    public static int getAvailableProcessors() {
        // 等同于 Runtime.availableProcessors()
        return SystemKit.getOperatingSystemMXBean().getAvailableProcessors();
    }

    /**
     * 获取系统负载
     *
     * @return
     * @since 1.0.3
     */
    public static double getSystemLoadAverage() {
        return SystemKit.getOperatingSystemMXBean().getSystemLoadAverage();
    }


    /**
     * 获取操作系统总物理内存大小
     *
     * @return
     * @since 1.0.3
     */
    public static long getTotalPhysicalMemorySize() {
        return ((OperatingSystemMXBean) SystemKit.getOperatingSystemMXBean()).getTotalPhysicalMemorySize();
    }

    /**
     * 获取操作系统空闲物理内存大小
     *
     * @return
     * @since 1.0.3
     */
    public static long getFreePhysicalMemorySize() {
        return ((OperatingSystemMXBean) SystemKit.getOperatingSystemMXBean()).getFreePhysicalMemorySize();
    }

    /**
     * 获取操作系统已使用的物理内存大小
     *
     * @return
     * @since 1.0.3
     */
    public static long getUsedPhysicalMemorySize() {
        return getTotalPhysicalMemorySize() - getFreePhysicalMemorySize();
    }

    /**
     * 获取操作系统交换空间大小
     *
     * @return
     * @since 1.0.3
     */
    public static long getTotalSwapSpaceSize() {
        return ((OperatingSystemMXBean) SystemKit.getOperatingSystemMXBean()).getTotalSwapSpaceSize();
    }


    /**
     * 获取操作系统空闲的交换空间大小
     *
     * @return
     * @since 1.0.3
     */
    public static long getFreeSwapSpaceSize() {
        return ((OperatingSystemMXBean) SystemKit.getOperatingSystemMXBean()).getFreeSwapSpaceSize();
    }
}
