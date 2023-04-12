package cn.chenzw.toolkit.logging.configurer;


import cn.chenzw.toolkit.spring.aop.JoinPointWrapper;

/**
 * 自定义配置接口
 *
 * @author chenzw
 */
public interface MethodLoggingConfigurer {


    String generateLogId();

    String getExt(JoinPointWrapper jpw);
}
