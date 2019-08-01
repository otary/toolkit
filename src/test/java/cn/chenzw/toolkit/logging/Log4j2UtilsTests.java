package cn.chenzw.toolkit.logging;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.core.config.LoggerConfig;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@RunWith(JUnit4.class)
public class Log4j2UtilsTests {

    private static final Logger logger = LoggerFactory.getLogger(Log4j2UtilsTests.class);

    @Test
    public void test() {
        List<LoggerConfig> loggers = Log4j2Utils.getLoggers();
        for (LoggerConfig loggerConfig : loggers) {
            System.out.println(loggerConfig);
        }

        Log4j2Utils.setLogLevel("cn.chenzw.toolkit.logging.Log4j2Utils", Level.WARN);
        LoggerConfig logger = Log4j2Utils.getLogger("cn.chenzw.toolkit.logging.Log4j2Utils");
        Assert.assertEquals(Level.WARN, logger.getLevel());

    }
}
