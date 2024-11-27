package cn.chenzw.toolkit.core.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

/**
 * @author chenzw
 */
@Slf4j
@RunWith(JUnit4.class)
public class PropertiesKitTests {

    @Test
    public void testProperties2Json() throws JsonProcessingException {
        String json = PropertiesKit.toJson("spring.datasource.driver-class-name=org.h2.Driver\n" +
                "spring.datasource.url=jdbc:h2:mem:test\n" +
                "spring.datasource.username=sa\n" +
                "spring.datasource.password=");
        log.info("Properties2Json => {}", json);
    }

}
