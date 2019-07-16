package cn.chenzw.toolkit.logging;

import ch.qos.logback.classic.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.List;

@RunWith(JUnit4.class)
public class LogbackUtilsTests {

    @Test
    public void test(){
        List<Logger> loggers = LogbackUtils.getLoggers();
        for (Logger logger : loggers) {
            System.out.println(logger);
        }
    }
}
