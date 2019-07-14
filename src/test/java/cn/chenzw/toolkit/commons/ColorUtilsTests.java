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
        Assert.assertEquals(color.getRed(), 112);
        Assert.assertEquals(color.getGreen(), 173);
        Assert.assertEquals(color.getBlue(), 71);
    }

    @Test
    public void testHexToRgb2() {
        Color color = ColorUtils.hexToRgb("#70AD47");

        Assert.assertEquals(color.getRed(), 112);
        Assert.assertEquals(color.getGreen(), 173);
        Assert.assertEquals(color.getBlue(), 71);
    }
}
