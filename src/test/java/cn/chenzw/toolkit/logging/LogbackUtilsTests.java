package cn.chenzw.toolkit.logging;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.slf4j.LoggerFactory;

import java.util.List;

@RunWith(JUnit4.class)
public class LogbackUtilsTests {

    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(LogbackUtilsTests.class);

    @Test
    @Ignore
    public void test() {
        List<Logger> loggers = LogbackUtils.getLoggers();
        for (Logger logger : loggers) {
            System.out.println(logger.getName() + ":" + logger.getLevel());
        }

        LogbackUtils.setLogLevel("cn.chenzw.toolkit.logging.LogbackUtilsTests", Level.WARN);
        Logger logger = LogbackUtils.getLogger("cn.chenzw.toolkit.logging.LogbackUtilsTests");
        Assert.assertEquals(Level.WARN, logger.getLevel());

        Logger rootLogger = LogbackUtils.getRootLogger();
        Assert.assertEquals(Level.DEBUG, rootLogger.getLevel());
        Assert.assertEquals("ROOT", rootLogger.getName());
    }
}
