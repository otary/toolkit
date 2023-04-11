package cn.chenzw.toolkit.core.lang;

import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Hex;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class RadixKitTests {

    @Test
    public void testBytesToHexString() {
        String sHex = RadixKit.bytesToHexString("hello".getBytes());
        Assert.assertEquals("68656c6c6f", sHex);

        String sHex2 = Hex.encodeHexString("hello".getBytes());
        Assert.assertEquals("68656c6c6f", sHex2);
    }

    @Test
    public void testHexStringToBytes() throws DecoderException {
        byte[] bytes = RadixKit.hexStringToBytes("68656c6c6f");
        Assert.assertEquals("hello", new String(bytes));

        byte[] bytes2 = Hex.decodeHex("68656c6c6f");
        Assert.assertEquals("hello", new String(bytes2));
    }
}
