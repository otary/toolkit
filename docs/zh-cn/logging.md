
## logging

### Log4j2Utils

log4j2工具类

``` java
// 获取所有Logger对象
List<LoggerConfig> loggers = Log4j2Utils.getLoggers(); 
for (LoggerConfig loggerConfig : loggers) {
    System.out.println(loggerConfig);
}

// 动态设置某个类的日志输出级别
Log4j2Utils.setLogLevel("cn.chenzw.toolkit.logging.Log4j2Utils", Level.WARN); 
LoggerConfig logger = Log4j2Utils.getLogger("cn.chenzw.toolkit.logging.Log4j2Utils");  // => WARN级别
```

### LogbackUtils

logback工具类

``` java
// 获取所有Logger对象
List<Logger> loggers = LogbackUtils.getLoggers();
for (Logger logger : loggers) {
    System.out.println(logger.getName() + ":" + logger.getLevel());
}

// 动态设置某个类的日志输出级别
LogbackUtils.setLogLevel("cn.chenzw.toolkit.logging.LogbackUtilsTests", Level.WARN);
Logger logger = LogbackUtils.getLogger("cn.chenzw.toolkit.logging.LogbackUtilsTests");  // => WARN

// 获取根Root
Logger rootLogger = LogbackUtils.getRootLogger();
```

---