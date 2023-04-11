package cn.chenzw.toolkit.core.util;

import cn.chenzw.toolkit.core.entity.attr.XmlToMapAttr;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.StringUtils;
import org.dom4j.*;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * XML解析工具类
 *
 * @author chenzw
 * @since 1.0.3
 */

public final class XmlKit {

    private XmlKit() {

    }

    private static final ObjectMapper objectMapper = new ObjectMapper();

    /**
     * XML => Map
     *
     * @param xml
     * @param <K>
     * @param <V>
     * @return
     * @throws DocumentException
     */
    public static <K, V> Map<K, V> xmlToMap(String xml) throws DocumentException {
        return xmlToMap(xml, new XmlToMapAttr());
    }

    /**
     * Map => XML
     * @param map
     * @return
     */
    public static final String map2Xml(Map<String, Object> map) {
        return recursionItem2Xml(map, null);
    }

    /**
     * XML => Map
     *
     * @param xml
     * @param xmlToMapAttr
     * @param <K>
     * @param <V>
     * @return
     * @throws DocumentException
     */
    public static <K, V> Map<K, V> xmlToMap(String xml, XmlToMapAttr xmlToMapAttr) throws DocumentException {
        Map<K, V> contextMap = new HashMap<>();

        Document doc = DocumentHelper.parseText(xml);
        if (doc == null) {
            return contextMap;
        }

        // 遍历
        recursionXmlToMap(doc.getRootElement(), contextMap, xmlToMapAttr);

        return contextMap;
    }

    /**
     * XML => JSON
     *
     * @param xml
     * @return
     * @throws DocumentException
     */
    public static String xml2Json(String xml, XmlToMapAttr xmlToMapAttr) throws DocumentException {
        Map<Object, Object> map = xmlToMap(xml, xmlToMapAttr);
        try {
            return objectMapper.writeValueAsString(map);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * XML => JSON
     *
     * @param xml
     * @return
     * @throws DocumentException
     */
    public static String xml2Json(String xml) throws DocumentException {
        return xml2Json(xml, new XmlToMapAttr());
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

    private static Map<String, String> buildAttrMap(List<Attribute> attributes) {
        Map<String, String> attrsMap = new LinkedHashMap<>();
        for (Attribute attribute : attributes) {
            attrsMap.put(attribute.getName(), attribute.getValue());
        }
        return attrsMap;
    }

    private static <K, V> void recursionXmlToMap(Element element, Map<K, V> contextMap, XmlToMapAttr xmlToMapAttr) {
        List<Element> elements = element.elements();

        if (elements.isEmpty()) {
            Map<K, V> elementMap = new LinkedHashMap<>();
            // 包含属性字段
            if (xmlToMapAttr.isIncludeAttrs()) {
                Map<String, String> attrsMap = buildAttrMap(element.attributes());
                if (!attrsMap.isEmpty()) {
                    elementMap.put((K) xmlToMapAttr.getAttrKey(), (V) attrsMap);
                }
            }

            // 使用独立文本字段
            if (xmlToMapAttr.isUseTextAttr()) {
                if (!StringUtils.isBlank(element.getTextTrim())) {
                    elementMap.put((K) xmlToMapAttr.getTextKey(), (V) element.getText());
                }
            }

            if (!elementMap.isEmpty()) {
                contextMap.put((K) element.getName(), (V) elementMap);
            } else {
                contextMap.put((K) element.getName(), (V) element.getText());
            }
        } else {
            Map<K, V> childMap = new HashMap<>();
            elements.forEach(el -> recursionXmlToMap(el, childMap, xmlToMapAttr));

            if (xmlToMapAttr.isIncludeAttrs()) {
                Map<String, String> attrsMap = buildAttrMap(element.attributes());
                if (!attrsMap.isEmpty()) {
                    childMap.put((K) xmlToMapAttr.getAttrKey(), (V) attrsMap);
                }
            }

            if (!StringUtils.isBlank(element.getTextTrim())) {
                childMap.put((K) xmlToMapAttr.getTextKey(), (V) element.getText());
            }
            contextMap.put((K) element.getName(), (V) childMap);
        }

    }
}
