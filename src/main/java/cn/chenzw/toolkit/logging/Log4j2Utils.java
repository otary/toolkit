package cn.chenzw.toolkit.logging;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.LoggerContext;
import org.apache.logging.log4j.core.config.Configuration;
import org.apache.logging.log4j.core.config.LoggerConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

import static org.slf4j.Logger.ROOT_LOGGER_NAME;

/**
 * @author chenzw
 */
public class Log4j2Utils {

    private Log4j2Utils() {
    }

    /**
     * 获取LoggerContext对象
     *
     * @return
     */
    public static LoggerContext getLoggerContext() {
        return (LoggerContext) LogManager.getContext();
    }

    /**
     * 获取所有Logger
     *
     * @return
     */
    public static List<LoggerConfig> getLoggers() {
        List<LoggerConfig> loggerConfigs = new ArrayList<>();
        Configuration configuration = getLoggerContext().getConfiguration();
        for (LoggerConfig loggerConfig : configuration.getLoggers().values()) {
            loggerConfigs.add(loggerConfig);
        }
        return loggerConfigs;
    }

    /**
     * 获取根Logger
     *
     * @return
     */
    public static LoggerConfig getRootLogger() {
        return getLogger(ROOT_LOGGER_NAME);
    }

    /**
     * 获取指定名称的Logger
     *
     * @param loggerName
     * @return
     */
    public static LoggerConfig getLogger(String loggerName) {
        if (!StringUtils.hasLength(loggerName) || ROOT_LOGGER_NAME.equals(loggerName)) {
            loggerName = LogManager.ROOT_LOGGER_NAME;
        }
        return getLoggerContext().getConfiguration().getLoggers().get(loggerName);
    }

    /**
     * 设置Logger的级别
     *
     * @param loggerName
     * @param level
     */
    public static void setLogLevel(String loggerName, Level level) {
        LoggerConfig loggerConfig = getLogger(loggerName);
        if (loggerConfig == null) {
            loggerConfig = new LoggerConfig(loggerName, level, true);
            getLoggerContext().getConfiguration().addLogger(loggerName, loggerConfig);
        } else {
            loggerConfig.setLevel(level);
        }
        getLoggerContext().updateLoggers();
    }


}
