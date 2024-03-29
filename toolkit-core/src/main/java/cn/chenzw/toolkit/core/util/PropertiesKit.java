package cn.chenzw.toolkit.core.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.dataformat.javaprop.JavaPropsMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;

import java.io.IOException;
import java.io.StringWriter;
import java.util.Properties;

/**
 * @author chenzw
 */
public class PropertiesKit {

    /**
     * Properties转Yaml
     *
     * @param properties
     * @return
     * @throws JsonProcessingException
     */
    public static String properties2Yaml(String properties) throws JsonProcessingException {
        JavaPropsMapper javaPropsMapper = JavaPropsMapper.builder().build();
        JsonNode jsonNode = javaPropsMapper.readTree(properties);

        YAMLMapper yamlMapper = new YAMLMapper();
        return yamlMapper.writeValueAsString(jsonNode);
    }

    public static String toString(Properties properties) {
        try (StringWriter sw = new StringWriter()){
            properties.store(sw, null);
            return sw.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
