package cn.chenzw.toolkit.logging;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.LoggerContext;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.ILoggerFactory;
import org.slf4j.impl.StaticLoggerBinder;
import org.springframework.util.Assert;

import java.security.CodeSource;
import java.security.ProtectionDomain;
import java.util.List;

import static org.slf4j.Logger.ROOT_LOGGER_NAME;

/**
 * @author chenzw
 */
public class LogbackUtils {

    private LogbackUtils() {
    }

    /**
     * @return
     */
    public static LoggerContext getLoggerContext() {
        ILoggerFactory factory = StaticLoggerBinder.getSingleton().getLoggerFactory();
        Assert.isInstanceOf(LoggerContext.class, factory,
                String.format(
                        "LoggerFactory is not a Logback LoggerContext but Logback is on "
                                + "the classpath. Either remove Logback or the competing "
                                + "implementation (%s loaded from %s). If you are using "
                                + "WebLogic you will need to add 'org.slf4j' to "
                                + "prefer-application-packages in WEB-INF/weblogic.xml",
                        factory.getClass(), getLocation(factory)));
        return (LoggerContext) factory;
    }

    private static Object getLocation(ILoggerFactory factory) {
        try {
            ProtectionDomain protectionDomain = factory.getClass().getProtectionDomain();
            CodeSource codeSource = protectionDomain.getCodeSource();
            if (codeSource != null) {
                return codeSource.getLocation();
            }
        } catch (SecurityException ex) {
            // Unable to determine location
            ex.printStackTrace();
        }
        return "unknown location";
    }

    /**
     * 获取根Logger
     *
     * @return
     */
    public static Logger getRootLogger() {
        return getLogger(ROOT_LOGGER_NAME);
    }

    /**
     * 获取所有Logger
     *
     * @return
     */
    public static List<Logger> getLoggers() {
        return getLoggerContext().getLoggerList();
    }

    /**
     * 获取指定名称的Logger
     *
     * @param loggerName
     * @return
     */
    public static Logger getLogger(String loggerName) {
        LoggerContext factory = getLoggerContext();
        if (StringUtils.isEmpty(loggerName) || ROOT_LOGGER_NAME.equals(loggerName)) {
            loggerName = ROOT_LOGGER_NAME;
        }
        return factory.getLogger(loggerName);
    }

    /**
     * 设置Logger的级别
     *
     * @param loggerName
     * @param level
     */
    public static void setLogLevel(String loggerName, Level level) {
        Logger logger = getLogger(loggerName);
        if (logger != null) {
            logger.setLevel(level);
        }
    }
}
