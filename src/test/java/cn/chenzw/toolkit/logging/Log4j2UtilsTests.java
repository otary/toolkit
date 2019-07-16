package cn.chenzw.toolkit.logging;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.core.config.LoggerConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.List;

@RunWith(JUnit4.class)
public class Log4j2UtilsTests {

    @Test
    public void test() {
        List<LoggerConfig> loggers = Log4j2Utils.getLoggers();
        for (LoggerConfig loggerConfig : loggers) {
            System.out.println(loggerConfig);
        }

        Log4j2Utils.setLogLevel("cn.chenzw.toolkit.logging.Log4j2Utils", Level.DEBUG);

        List<LoggerConfig> loggers2 = Log4j2Utils.getLoggers();
        for (LoggerConfig loggerConfig2 : loggers2) {
            System.out.println(loggerConfig2.getName() + ":" + loggerConfig2.getLevel());
        }
    }
}
