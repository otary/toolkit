package cn.chenzw.toolkit.core.net;

import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@Slf4j
@RunWith(JUnit4.class)
public class HtmlKitTests {

    @Test
    public void testGetText() {
        String text = HtmlKit.getText("<html><div>xxxx</div><a>yyyy</a></html>");
        Assert.assertEquals("xxxxyyyy", text);
    }
}
