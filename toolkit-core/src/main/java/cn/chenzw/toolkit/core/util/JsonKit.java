package cn.chenzw.toolkit.core.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.SerializationFeature;
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

    static {
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
                .configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false)
                .setPropertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE);
    }

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


    public static <T> T readValue(String content, Class<T> tClass) throws JsonProcessingException {
        return objectMapper.readValue(content, tClass);
    }

    public static <T> T readValueQuietly(String content, Class<T> tClass) {
        try {
            return objectMapper.readValue(content, tClass);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static <T> T readValue(byte[] bytes, Class<T> tClass) throws IOException {
        return objectMapper.readValue(bytes, tClass);
    }

    public static String writeValueAsString(Object value) throws JsonProcessingException {
        return objectMapper.writeValueAsString(value);
    }

    public static <T> T readValue(String content, TypeReference<T> typeReference) throws JsonProcessingException {
        return objectMapper.readValue(content, typeReference);
    }

}
