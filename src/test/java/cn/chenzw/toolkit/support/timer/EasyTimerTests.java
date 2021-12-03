package cn.chenzw.toolkit.support.timer;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.time.Duration;

@Slf4j
@RunWith(JUnit4.class)
public class EasyTimerTests {

    @Test
    public void test() throws InterruptedException {
        EasyTimer easyTimer = new EasyTimer(Duration.ofSeconds(5));
        easyTimer.start();
        long t1 = System.currentTimeMillis();
        while (!easyTimer.isTimeout()) {
            log.info("wait....");
            Thread.sleep(100);
        }
        long t2 = System.currentTimeMillis();
        log.info("计时结束, 耗时;{}ms!", (t2 - t1));

        log.info("重新计时...");
        easyTimer.restart();
        long t3 = System.currentTimeMillis();
        while (!easyTimer.isTimeout()) {
            log.info("wait....");
            Thread.sleep(100);
        }
        long t4 = System.currentTimeMillis();
        log.info("第二次计时结束, 耗时: {}ms!", (t4 - t3));

        log.info("重新计时，定时3秒...");
        easyTimer.restart(Duration.ofSeconds(3));
        long t5 = System.currentTimeMillis();
        while (!easyTimer.isTimeout()) {
            log.info("wait....");
            Thread.sleep(100);
        }
        long t6 = System.currentTimeMillis();
        log.info("第三次计时结束, 耗时: {}ms!", (t6 - t5));

    }
}
