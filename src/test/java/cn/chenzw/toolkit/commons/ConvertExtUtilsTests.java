package cn.chenzw.toolkit.commons;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.math.BigDecimal;
import java.util.Date;

@RunWith(JUnit4.class)
public class ConvertExtUtilsTests {

    @Test
    public void testConvert() {
        String v1 = ConvertExtUtils.convert(String.class, 1222L);
        Assert.assertEquals("1222", v1);

        BigDecimal v2 = ConvertExtUtils.convert(BigDecimal.class, 103.35F);
        Assert.assertEquals("103.35", v2.toString());

        Date v3 = ConvertExtUtils.convert(Date.class, "2010-12-23 12:33:11");
        Assert.assertEquals("Thu Dec 23 12:33:11 CST 2010", v3.toString());
    }
}
