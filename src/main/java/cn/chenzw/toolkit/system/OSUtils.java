package cn.chenzw.toolkit.system;

import com.sun.management.OperatingSystemMXBean;

/**
 * 操作系统工具类
 *
 * @author chenzw
 * @since 1.0.3
 */
public class OSUtils {

    /**
     * 获取操作系统名称
     *
     * @return 操作系统名称
     * @since 1.0.3
     */
    public static String getName() {
        // 等同于 System.getProperty("os.name")
        return SystemUtils.getOperatingSystemMXBean().getName();
    }

    /**
     * 获取操作系统架构
     *
     * @return 操作系统架构
     * @since 1.0.3
     */
    public static String getArch() {
        // 等同于 System.getProperty("os.arch")
        return SystemUtils.getOperatingSystemMXBean().getArch();
    }

    /**
     * 获取操作系统版本
     *
     * @return 操作系统版本
     * @since 1.0.3
     */
    public static String getVersion() {
        // 等同于 System.getProperty("os.version")
        return SystemUtils.getOperatingSystemMXBean().getVersion();
    }


    /**
     * 获取操作系统可用内核数
     *
     * @return
     * @since 1.0.3
     */
    public static int getAvailableProcessors() {
        // 等同于 Runtime.availableProcessors()
        return SystemUtils.getOperatingSystemMXBean().getAvailableProcessors();
    }

    /**
     * 获取系统负载
     *
     * @return
     * @since 1.0.3
     */
    public static double getSystemLoadAverage() {
        return SystemUtils.getOperatingSystemMXBean().getSystemLoadAverage();
    }


    /**
     * 获取操作系统总物理内存大小
     *
     * @return
     * @since 1.0.3
     */
    public static long getTotalPhysicalMemorySize() {
        return ((OperatingSystemMXBean) SystemUtils.getOperatingSystemMXBean()).getTotalPhysicalMemorySize();
    }

    /**
     * 获取操作系统空闲物理内存大小
     *
     * @return
     * @since 1.0.3
     */
    public static long getFreePhysicalMemorySize() {
        return ((OperatingSystemMXBean) SystemUtils.getOperatingSystemMXBean()).getFreePhysicalMemorySize();
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
        return ((OperatingSystemMXBean) SystemUtils.getOperatingSystemMXBean()).getTotalSwapSpaceSize();
    }


    /**
     * 获取操作系统空闲的交换空间大小
     *
     * @return
     * @since 1.0.3
     */
    public static long getFreeSwapSpaceSize() {
        return ((OperatingSystemMXBean) SystemUtils.getOperatingSystemMXBean()).getFreeSwapSpaceSize();
    }
}
