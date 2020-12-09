package cn.chenzw.toolkit.system;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@Slf4j
@RunWith(JUnit4.class)
public class OSUtilsTests {

    @Test
    public void test() {
        log.info("操作系统名称 => {}", OSUtils.getName());
        log.info("操作系统版本 => {}", OSUtils.getVersion());
        log.info("操作系统架构 => {}", OSUtils.getArch());
        log.info("操作系统可用核心数 => {}", OSUtils.getAvailableProcessors());
        log.info("操作系统负载 => {}", OSUtils.getSystemLoadAverage());

        log.info("操作系统物理内存大小 => {}", OSUtils.getTotalPhysicalMemorySize());
        log.info("操作系统空闲物理内存大小 => {}", OSUtils.getFreePhysicalMemorySize());
        log.info("操作系统已使用物理内存大小 => {}", OSUtils.getUsedPhysicalMemorySize());
        log.info("操作系统交换空间大小 => {}", OSUtils.getTotalSwapSpaceSize());
        log.info("操作系统空闲交换空间大小 => {}", OSUtils.getFreeSwapSpaceSize());
    }
}
