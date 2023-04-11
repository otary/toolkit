package cn.chenzw.toolkit.core.system;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@Slf4j
@RunWith(JUnit4.class)
public class OSKitTests {

    @Test
    public void testBasic() {
        log.info("操作系统名称 => {}", OSKit.getName());
        log.info("操作系统版本 => {}", OSKit.getVersion());
        log.info("操作系统架构 => {}", OSKit.getArch());
        log.info("操作系统可用核心数 => {}", OSKit.getAvailableProcessors());
        log.info("操作系统负载 => {}", OSKit.getSystemLoadAverage());

        log.info("操作系统物理内存大小 => {}", OSKit.getTotalPhysicalMemorySize());
        log.info("操作系统空闲物理内存大小 => {}", OSKit.getFreePhysicalMemorySize());
        log.info("操作系统已使用物理内存大小 => {}", OSKit.getUsedPhysicalMemorySize());
        log.info("操作系统交换空间大小 => {}", OSKit.getTotalSwapSpaceSize());
        log.info("操作系统空闲交换空间大小 => {}", OSKit.getFreeSwapSpaceSize());
    }
}
