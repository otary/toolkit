package cn.chenzw.toolkit.core.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;

import java.io.IOException;
import java.util.Map;

/**
 * JSON工具类
 *
 * @author chenzw
 * @since 1.0.3
 */
public final class JsonKit {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    /**
     * JSON转Xml
     *
     * @param json
     * @return
     */
    public static String json2Xml(String json) throws IOException {
        return XmlKit.map2Xml(
                objectMapper.readValue(json, Map.class)
        );
    }

    /**
     * JSON 转 Yaml
     *
     * @param json
     * @return
     */
    public static String json2Yaml(String json) throws IOException {
        return json2Yaml(json, new YAMLFactory());
    }

    /**
     * JSON 转 Yaml
     *
     * @param json
     * @return
     */
    public static String json2Yaml(String json, YAMLFactory yamlFactory) throws IOException {
        return new YAMLMapper(yamlFactory).writeValueAsString(
                objectMapper.readTree(json)
        );
    }


}
