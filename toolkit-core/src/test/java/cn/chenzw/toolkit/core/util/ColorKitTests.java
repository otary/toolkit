package cn.chenzw.toolkit.core.util;

import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.awt.*;

@Slf4j
@RunWith(JUnit4.class)
public class ColorKitTests {

    @Test
    public void testHexToRgb() {
        Color color = ColorKit.hexToRgb("70AD47");
        Assert.assertEquals(112, color.getRed());
        Assert.assertEquals(173, color.getGreen());
        Assert.assertEquals(71, color.getBlue());
    }

    @Test
    public void testHexToRgb2() {
        Color color = ColorKit.hexToRgb("#70AD47");

        Assert.assertEquals(112, color.getRed());
        Assert.assertEquals(173, color.getGreen());
        Assert.assertEquals(71, color.getBlue());
    }

    @Test
    public void testRandomColor() {
        for (int i = 0; i < 5; i++) {
            Color color = ColorKit.randomColor(0, 255);
            log.info("生成随机颜色值 => {}", color);
        }
    }
}
