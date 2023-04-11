package cn.chenzw.toolkit.core.system;

import java.lang.management.*;
import java.util.List;

/**
 * 系统信息工具类
 * @author chenzw
 */
public class SystemKit {


    /**
     * 获取Java虚拟机运行下的操作系统相关信息
     *
     * @return {@link OperatingSystemMXBean}
     * @since 1.0.3
     */
    public static OperatingSystemMXBean getOperatingSystemMXBean() {
        return ManagementFactory.getOperatingSystemMXBean();
    }


    /**
     * 获取Java虚拟机类加载相关属性
     *
     * @return {@link ClassLoadingMXBean}
     * @since 1.0.3
     */
    public static ClassLoadingMXBean getClassLoadingMXBean() {
        return ManagementFactory.getClassLoadingMXBean();
    }

    /**
     * 获取Java虚拟机内存相关属性
     *
     * @return {@link MemoryMXBean}
     * @since 1.0.3
     */
    public static MemoryMXBean getMemoryMXBean() {
        return ManagementFactory.getMemoryMXBean();
    }

    /**
     * 获取Java虚拟机线程相关属性
     *
     * @return {@link ThreadMXBean}
     * @since 1.0.3
     */
    public static ThreadMXBean getThreadMXBean() {
        return ManagementFactory.getThreadMXBean();
    }

    /**
     * 获取Java虚拟机运行时相关属性
     *
     * @return {@link RuntimeMXBean}
     * @since 1.0.3
     */
    public static RuntimeMXBean getRuntimeMXBean() {
        return ManagementFactory.getRuntimeMXBean();
    }

    /**
     * 获取Java虚拟机编译系统相关属性<br>
     * <p>如果没有编译系统，则返回<code>null</code></p>
     *
     * @return {@link CompilationMXBean}
     * @since 1.0.3
     */
    public static CompilationMXBean getCompilationMXBean() {
        return ManagementFactory.getCompilationMXBean();
    }

    /**
     * 获取Java虚拟机中的{@link MemoryPoolMXBean}列表
     *
     * @return a list of <tt>MemoryPoolMXBean</tt> objects.
     */
    public static List<MemoryPoolMXBean> getMemoryPoolMXBeans() {
        return ManagementFactory.getMemoryPoolMXBeans();
    }

    /**
     * 获取Java虚拟机中的{@link MemoryManagerMXBean}列表<br>
     *
     * @return a list of <tt>MemoryManagerMXBean</tt> objects.
     * @since 1.0.3
     */
    public static List<MemoryManagerMXBean> getMemoryManagerMXBeans() {
        return ManagementFactory.getMemoryManagerMXBeans();
    }

    /**
     * 获取Java虚拟机的{@link GarbageCollectorMXBean}列表
     *
     * @return {@link GarbageCollectorMXBean}列表
     * @since 1.0.3
     */
    public static List<GarbageCollectorMXBean> getGarbageCollectorMXBeans() {
        return ManagementFactory.getGarbageCollectorMXBeans();
    }



}
