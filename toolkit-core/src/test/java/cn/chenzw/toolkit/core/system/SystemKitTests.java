package cn.chenzw.toolkit.core.system;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.lang.management.*;
import java.util.Arrays;
import java.util.List;

@Slf4j
@RunWith(JUnit4.class)
public class SystemKitTests {

    @Test
    public void testGetOperatingSystemMXBean() {
        OperatingSystemMXBean osMXBean = SystemKit.getOperatingSystemMXBean();
        log.info("系统名称 => {}", osMXBean.getName());
        log.info("操作系统的架构 => {}", osMXBean.getArch());
        log.info("系统版本 => {}", osMXBean.getVersion());
        log.info("可用的内核数 => {}", osMXBean.getAvailableProcessors());
        log.info("系统负载 => {}", osMXBean.getSystemLoadAverage());
    }

    @Test
    public void testGetMemoryMXBean() {
        MemoryMXBean memoryMXBean = SystemKit.getMemoryMXBean();
        // 堆内存
        MemoryUsage heapMemoryUsage = memoryMXBean.getHeapMemoryUsage();
        log.info("堆最大可用内存 => {}", heapMemoryUsage.getMax());
        log.info("堆已使用内存 => {}", heapMemoryUsage.getUsed());
        log.info("堆初始内存 => {}", heapMemoryUsage.getInit());
        log.info("堆已提交内存 => {}", heapMemoryUsage.getCommitted());

        MemoryUsage nonHeapMemoryUsage = memoryMXBean.getNonHeapMemoryUsage();
        log.info("非堆最大可用内存 => {}", nonHeapMemoryUsage.getMax());
        log.info("非堆已使用内存 => {}", nonHeapMemoryUsage.getUsed());
        log.info("非堆初始内存 => {}", nonHeapMemoryUsage.getInit());
        log.info("非堆已提交内存 => {}", nonHeapMemoryUsage.getCommitted());
    }

    @Test
    public void testGetCompilationMXBean() {
        CompilationMXBean compilationMXBean = SystemKit.getCompilationMXBean();
        log.info("JIT编译器名称 => {}", compilationMXBean.getName());

        // 判断jvm是否支持编译时间的监控
        if (compilationMXBean.isCompilationTimeMonitoringSupported()) {
            log.info("JIT编译总耗时 => {}ms", compilationMXBean.getTotalCompilationTime());
        }
    }

    @Test
    public void testGetClassLoadingMXBean() {
        ClassLoadingMXBean classLoadingMXBean = SystemKit.getClassLoadingMXBean();

        log.info("已加载类总数 => {}", classLoadingMXBean.getTotalLoadedClassCount());
        log.info("已加载当前类 => {}", classLoadingMXBean.getLoadedClassCount());
        log.info("已卸载类总数 => {}", classLoadingMXBean.getUnloadedClassCount());
    }

    @Test
    public void testGetRuntimeMXBean() {
        RuntimeMXBean runtimeMXBean = SystemKit.getRuntimeMXBean();
        log.info("进程PID => {}", runtimeMXBean.getName().split("@")[0]);
        log.info("JVM规范名称 => {}", runtimeMXBean.getSpecName());
        log.info("JVM规范运营商 => {}", runtimeMXBean.getSpecVendor());
        log.info("JVM规范版本 => {}", runtimeMXBean.getSpecVersion());
        log.info("JVM启动时间(毫秒) => {}", runtimeMXBean.getStartTime());

        log.info("JVM正常运行时间（毫秒）=> {}", runtimeMXBean.getUptime());
        // 相当于System.getProperty("java.vm.name").
        log.info("JVM名称 => {}", runtimeMXBean.getVmName());
        // 相当于System.getProperty("java.vm.vendor")
        log.info("JVM运营商 => {}", runtimeMXBean.getVmVendor());
        // 相当于System.getProperty("java.vm.version")
        log.info("JVM实现版本 => {}", runtimeMXBean.getVmVersion());

        List<String> inputArguments = runtimeMXBean.getInputArguments();
        if (inputArguments != null && !inputArguments.isEmpty()) {
            for (String inputArgument : inputArguments) {
                log.info("vm参数 => {}", inputArgument);
            }
        }
        log.info("类路径 => {}", runtimeMXBean.getClassPath());
        log.info("引导类路径 => {}", runtimeMXBean.getBootClassPath());
        log.info("库路径 => {}", runtimeMXBean.getLibraryPath());
    }

    @Test
    public void testGetMemoryManagerMXBeans() {
        List<MemoryManagerMXBean> memoryManagerMXBeans = SystemKit.getMemoryManagerMXBeans();
        if (memoryManagerMXBeans != null && !memoryManagerMXBeans.isEmpty()) {
            for (MemoryManagerMXBean memoryManagerMXBean : memoryManagerMXBeans) {
                log.info("vm内存管理器 => 名称: {}, 管理的内存区: {}, ObjectName: {}", memoryManagerMXBean.getName(),
                        Arrays.deepToString(memoryManagerMXBean.getMemoryPoolNames()), memoryManagerMXBean.getObjectName()
                );
            }
        }
    }

    @Test
    public void testGetGarbageCollectorMXBeans() {
        List<GarbageCollectorMXBean> garbageCollectorMXBeans = SystemKit.getGarbageCollectorMXBeans();
        for (GarbageCollectorMXBean garbageCollectorMXBean : garbageCollectorMXBeans) {
            log.info("垃圾收集器 => 名称: {}, 收集: {}, 总花费时间: {}, 内存区名称: {}",
                    garbageCollectorMXBean.getName(),
                    garbageCollectorMXBean.getCollectionCount(),
                    garbageCollectorMXBean.getCollectionTime(),
                    Arrays.deepToString(garbageCollectorMXBean.getMemoryPoolNames())
            );
        }
    }

    @Test
    public void testGetMemoryPoolMXBeans() {
        List<MemoryPoolMXBean> memoryPoolMXBeans = SystemKit.getMemoryPoolMXBeans();
        if (memoryPoolMXBeans != null && !memoryPoolMXBeans.isEmpty()) {
            for (MemoryPoolMXBean memoryPoolMXBean : memoryPoolMXBeans) {
                log.info("vm内存区 => 名称: {}, 所属内存管理者: {}, ObjectName: {},初始大小: {}, 最大(上限): {}, 已使用最大: {}, 已提交(已申请): {}",
                        memoryPoolMXBean.getName(),
                        Arrays.deepToString(memoryPoolMXBean.getMemoryManagerNames()),
                        memoryPoolMXBean.getObjectName(),
                        memoryPoolMXBean.getUsage().getInit(),
                        memoryPoolMXBean.getUsage().getMax(),
                        memoryPoolMXBean.getUsage().getUsed(),
                        memoryPoolMXBean.getUsage().getCommitted()
                );
            }
        }
    }

    @Test
    public void testGetThreadMXBean() {
        ThreadMXBean threadMXBean = SystemKit.getThreadMXBean();
        log.info("ObjectName => {}", threadMXBean.getObjectName());
        log.info("活动的线程总数 => {}", threadMXBean.getThreadCount());
        log.info("峰值 => {}", threadMXBean.getPeakThreadCount());
        log.info("线程总数（被创建并执行过的线程总数） => {}", threadMXBean.getTotalStartedThreadCount());
        log.info("活动的守护线程（daemonThread）总数 => {}", threadMXBean.getDaemonThreadCount());

        // 检查是否有死锁的线程存在
        long[] deadlockedThreadIds = threadMXBean.findDeadlockedThreads();
        if (deadlockedThreadIds != null && deadlockedThreadIds.length > 0) {
            ThreadInfo[] deadlockInfos = threadMXBean.getThreadInfo(deadlockedThreadIds);

            for (ThreadInfo deadlockInfo : deadlockInfos) {
                log.info("死锁线程信息 => 线程ID: {}, 线程名称: {}, 状态: {}, 发生时间: {}, 等待时间: {}, 堆栈: {} ",
                        deadlockInfo.getThreadId(),
                        deadlockInfo.getThreadName(),
                        deadlockInfo.getThreadState(),
                        deadlockInfo.getBlockedTime(),
                        deadlockInfo.getWaitedTime(),
                        deadlockInfo.getStackTrace().toString()
                );
            }
        }
    }



}
