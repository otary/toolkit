package cn.chenzw.toolkit.core.lang;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.math.BigDecimal;

/**
 * @author chenzw
 */
@RunWith(JUnit4.class)
public class NumberKitTests {

    @Test
    public void testIsInteger() {
        Assert.assertTrue(NumberKit.isInteger("56745"));
        Assert.assertTrue(NumberKit.isInteger("-3545"));

        // 小数
        Assert.assertFalse(NumberKit.isInteger("56.332"));
        Assert.assertFalse(NumberKit.isInteger("-333.32"));
    }


    @Test
    public void testIsNumber() {
        Assert.assertTrue(NumberKit.isNumber("2322"));
        Assert.assertTrue(NumberKit.isNumber("23.22"));
        Assert.assertTrue(NumberKit.isNumber("-2322"));
        Assert.assertTrue(NumberKit.isNumber("-23.22"));
        Assert.assertFalse(NumberKit.isNumber("1s4"));
    }

    @Test
    public void testSub() {
        Assert.assertEquals(new BigDecimal("4.89"), NumberKit.sub("10.32", "4.23", "1.2"));
        Assert.assertEquals(8.02d, NumberKit.sub(20.34d, 12.32d), 2);
    }

    @Test
    public void testMul() {
        Assert.assertEquals(new BigDecimal("2.76"), NumberKit.mul("2.3", "1.2"));
    }
}
