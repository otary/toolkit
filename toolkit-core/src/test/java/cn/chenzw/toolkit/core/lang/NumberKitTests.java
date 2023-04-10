package cn.chenzw.toolkit.core.lang;

import cn.chenzw.toolkit.core.util.Validator;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

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

}
