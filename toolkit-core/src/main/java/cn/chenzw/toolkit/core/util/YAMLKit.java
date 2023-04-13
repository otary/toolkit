package cn.chenzw.toolkit.core.util;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.dataformat.javaprop.JavaPropsMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;

import java.io.IOException;
import java.util.Properties;

/**
 * @author chenzw
 * @since 1.0.3
 */
public class YAMLKit {

    /**
     * Yaml转Properties
     *
     * @param yaml
     * @return
     * @throws IOException
     */
    public static Properties yaml2Properties(String yaml) throws IOException {
        YAMLMapper yamlMapper = new YAMLMapper();
        JsonNode jsonNode = yamlMapper.readTree(yaml);

        JavaPropsMapper javaPropsMapper = JavaPropsMapper.builder().build();
        return javaPropsMapper.writeValueAsProperties(jsonNode);
    }
}
