package cn.chenzw.toolkit.commons;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@Slf4j
@RunWith(JUnit4.class)
public class HtmlUtilsTests {


    @Test
    public void testGetText() {
        String text = HtmlUtils.getText("<html><div>xxxx</div><a>yyyy</a></html>");

        log.info("getText => {}", text);
    }
}
