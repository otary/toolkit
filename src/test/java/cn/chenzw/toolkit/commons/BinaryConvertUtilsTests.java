package cn.chenzw.toolkit.commons;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class BinaryConvertUtilsTests {


    @Test
    public void testBytesToHexString() {
        String sHex = BinaryConvertUtils.bytesToHexString("hello".getBytes());
        Assert.assertEquals(sHex, "68656c6c6f");
    }

    @Test
    public void testHexStringToBytes() {
        byte[] bytes = BinaryConvertUtils.hexStringToBytes("68656c6c6f");
        Assert.assertEquals(new String(bytes), "hello");
    }
}
