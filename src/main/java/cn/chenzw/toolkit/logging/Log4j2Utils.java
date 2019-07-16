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

public class Log4j2Utils {

    private static final Logger logger = LoggerFactory.getLogger(Log4j2Utils.class);
    private static final String ROOT_LOGGER_NAME = "ROOT";

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
     * @return
     */
    public static List<LoggerConfig> getLoggerConfigs() {
        List<LoggerConfig> loggerConfigs = new ArrayList<>();
        Configuration configuration = getLoggerContext().getConfiguration();
        for (LoggerConfig loggerConfig : configuration.getLoggers().values()) {
            loggerConfigs.add(loggerConfig);
        }
        return loggerConfigs;
    }

    /**
     * @param loggerName
     * @return
     */
    public static LoggerConfig getLoggerConfig(String loggerName) {
       /* Configuration configuration = getLoggerContext().getConfiguration();
        return configuration.getLoggerConfig(loggerName);*/

        if (!StringUtils.hasLength(loggerName) || ROOT_LOGGER_NAME.equals(loggerName)) {
            loggerName = LogManager.ROOT_LOGGER_NAME;
        }
        return getLoggerContext().getConfiguration().getLoggers().get(loggerName);
    }

    /**
     *
     * @param loggerName
     * @param level
     */
    public static void setLogLevel(String loggerName, Level level) {
        LoggerConfig loggerConfig = getLoggerConfig(loggerName);
        if (loggerConfig == null) {
            loggerConfig = new LoggerConfig(loggerName, level, true);
            getLoggerContext().getConfiguration().addLogger(loggerName, loggerConfig);
        } else {
            loggerConfig.setLevel(level);
        }
        getLoggerContext().updateLoggers();
    }


}
