package cn.chenzw.toolkit.logging.properties;

import cn.chenzw.toolkit.logging.consts.LogField;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author chenzw
 */
@Data
@ConfigurationProperties(prefix = "toolkit.logging")
public class MethodLoggingProperties {

    private LogField[] globalLogFields;
}
