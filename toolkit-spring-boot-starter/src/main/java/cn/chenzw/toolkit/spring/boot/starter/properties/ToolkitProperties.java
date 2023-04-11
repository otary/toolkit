package cn.chenzw.toolkit.spring.boot.starter.properties;

import cn.chenzw.toolkit.spring.boot.starter.ToolkitAutoConfiguration;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author chenzw
 */
@Data
@ConfigurationProperties(prefix = ToolkitAutoConfiguration.PROPERTY_PREFIX)
public class ToolkitProperties {

    private FilterBean appContextHolder = new FilterBean(true, null);

    private FilterBean httpHolder = new FilterBean(true, new String[]{"/*"});

    private FilterBean requestContentCache = new FilterBean(false, new String[]{"/*"});

    @Data
    public static class FilterBean {

        public FilterBean(boolean enable, String[] urlPatterns) {
            this.enable = enable;
            this.urlPatterns = urlPatterns;
        }

        private boolean enable;

        private String[] urlPatterns;


    }


}
