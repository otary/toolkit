package cn.chenzw.toolkit.commons;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.awt.*;

@RunWith(JUnit4.class)
public class ColorUtilsTests {

    @Test
    public void testHexToRgb() {
        Color color = ColorUtils.hexToRgb("70AD47");
        Assert.assertEquals(112, color.getRed());
        Assert.assertEquals(173, color.getGreen());
        Assert.assertEquals(71, color.getBlue());
    }

    @Test
    public void testHexToRgb2() {
        Color color = ColorUtils.hexToRgb("#70AD47");

        Assert.assertEquals(112, color.getRed());
        Assert.assertEquals(173, color.getGreen());
        Assert.assertEquals(71, color.getBlue());
    }
}
