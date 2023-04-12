package cn.chenzw.toolkit.logging.configurer;
import cn.chenzw.toolkit.spring.aop.JoinPointWrapper;

import java.util.UUID;

/**
 * @author chenzw
 */
public class MethodLoggingAdaptor implements MethodLoggingConfigurer {

    @Override
    public String generateLogId() {
        return UUID.randomUUID().toString();
    }

    @Override
    public String getExt(JoinPointWrapper jpw) {
        return "";
    }
}
