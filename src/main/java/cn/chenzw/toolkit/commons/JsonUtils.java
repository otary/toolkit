package cn.chenzw.toolkit.commons;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Map;

/**
 * JSON工具类
 *
 * @author chenzw
 * @since 1.0.3
 */
public final class JsonUtils {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    /**
     * JSON转Xml
     *
     * @param json
     * @return
     * @throws JsonProcessingException
     */
    public static String json2Xml(String json) throws JsonProcessingException {
        Map map = objectMapper.readValue(json, Map.class);
        return map2Xml(map);
    }

    private static String map2Xml(Map<String, Object> map) {
        return recursionMap2Xml(map);
    }

    private static String recursionMap2Xml(Map<String, Object> map) {
        StringBuilder resultBuilder = new StringBuilder();
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            if (entry.getValue() instanceof Map) {
                resultBuilder.append("<").append(entry.getKey()).append(">")
                        .append(recursionMap2Xml((Map<String, Object>) entry.getValue()))
                        .append("</").append(entry.getKey()).append(">");
            } else {
                resultBuilder.append("<").append(entry.getKey()).append(">")
                        .append(entry.getValue())
                        .append("</").append(entry.getKey()).append(">");
            }
        }
        return resultBuilder.toString();
    }

}
