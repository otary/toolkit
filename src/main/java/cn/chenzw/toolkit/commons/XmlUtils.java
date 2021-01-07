package cn.chenzw.toolkit.commons;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * XML解析工具类
 *
 * @author chenzw
 * @since 1.0.3
 */

public class XmlUtils {

    /**
     * xml => map
     *
     * @param xml
     * @param <K>
     * @param <V>
     * @return
     * @throws DocumentException
     */
    public static <K, V> Map<K, V> xmlToMap(String xml) throws DocumentException {
        Map<K, V> contextMap = new HashMap<>();

        Document doc = DocumentHelper.parseText(xml);
        if (doc == null) {
            return contextMap;
        }

        recursionXmlToMap(doc.getRootElement(), contextMap);

        return contextMap;
    }

    private static <K, V> void recursionXmlToMap(Element element, Map<K, V> contextMap) {
        List<Element> elements = element.elements();
        if (elements.isEmpty()) {
            // 如果没有子元素,则将其存储进map中
            contextMap.put((K) element.getName(), (V) element.getTextTrim());
        } else {
            Map<K, V> childMap = new HashMap<>();
            elements.forEach(el -> recursionXmlToMap(el, childMap));

            contextMap.put((K) element.getName(), (V) childMap);
        }
    }


}
