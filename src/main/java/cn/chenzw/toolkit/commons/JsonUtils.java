package cn.chenzw.toolkit.commons;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.StringUtils;

import java.util.List;
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
     */
    public static String json2Xml(String json) {
        Map map = null;
        try {
            map = objectMapper.readValue(json, Map.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return map2Xml(map);
    }

    private static String map2Xml(Map<String, Object> map) {
        return recursionItem2Xml(map, null);
    }

    private static String recursionItem2Xml(Object item, String key) {
        StringBuilder resultBuilder = new StringBuilder();
        if (item instanceof List) {
            for (Object item2 : (List) item) {
                resultBuilder.append("<").append(key).append(">")
                        .append(recursionItem2Xml(item2, ""))
                        .append("</").append(key).append(">");
            }
        } else if (item instanceof Map) {
            if (StringUtils.isNotBlank(key)) {
                resultBuilder.append("<").append(key).append(">");
            }
            for (Map.Entry<String, Object> entry : ((Map<String, Object>) item).entrySet()) {
                resultBuilder
                        .append(recursionItem2Xml(entry.getValue(), entry.getKey()));
            }
            if (StringUtils.isNotBlank(key)) {
                resultBuilder.append("</").append(key).append(">");
            }
        } else {
            resultBuilder.append("<").append(key).append(">")
                    .append(item)
                    .append("</").append(key).append(">");
        }
        return resultBuilder.toString();
    }
}
