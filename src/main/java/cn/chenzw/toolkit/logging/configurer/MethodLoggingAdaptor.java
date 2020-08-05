package cn.chenzw.toolkit.logging.configurer;
import java.util.UUID;

/**
 * @author chenzw
 */
public class MethodLoggingAdaptor implements MethodLoggingConfigurer {

    @Override
    public String generateLogId() {
        return UUID.randomUUID().toString();
    }
}
