package cn.chenzw.toolkit.codec;

import cn.chenzw.toolkit.codec.support.des.DESMode;
import cn.chenzw.toolkit.codec.support.des.DESPadding;
import org.apache.commons.codec.DecoderException;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

@RunWith(JUnit4.class)
public class DESUtilsTests {

    @Test
    public void testEncryptAsBase64String() throws NoSuchPaddingException, InvalidKeyException, NoSuchAlgorithmException, IllegalBlockSizeException, BadPaddingException, InvalidAlgorithmParameterException, InvalidKeySpecException {
        String result = DESUtils.encryptAsBase64String(DESMode.CBC, DESPadding.PKCS5Padding, "abc".getBytes(), "12345678", "12345678".getBytes());

        Assert.assertEquals("9YR6ZPdZufM=", result);
    }

    @Test
    public void testEncryptAsHexString() throws NoSuchPaddingException, InvalidKeyException, NoSuchAlgorithmException, IllegalBlockSizeException, BadPaddingException, InvalidAlgorithmParameterException, InvalidKeySpecException {
        String result = DESUtils.encryptAsHexString(DESMode.CBC, DESPadding.PKCS5Padding, "abc".getBytes(), "12345678", "12345678".getBytes());

        Assert.assertEquals("f5847a64f759b9f3", result);
    }

    @Test
    public void testDecryptBase64String() throws NoSuchPaddingException, InvalidKeyException, NoSuchAlgorithmException, IllegalBlockSizeException, BadPaddingException, InvalidAlgorithmParameterException, InvalidKeySpecException {
        byte[] bytes = DESUtils.decryptBase64String("9YR6ZPdZufM=", "12345678", DESMode.CBC, DESPadding.PKCS5Padding, "12345678".getBytes());

        Assert.assertEquals("abc", new String(bytes));
    }

    @Test
    public void testDecryptHexString() throws NoSuchPaddingException, InvalidKeyException, DecoderException, IllegalBlockSizeException, BadPaddingException, NoSuchAlgorithmException, InvalidAlgorithmParameterException, InvalidKeySpecException {
        byte[] bytes = DESUtils.decryptHexString("f5847a64f759b9f3", "12345678", DESMode.CBC, DESPadding.PKCS5Padding, "12345678".getBytes());

        Assert.assertEquals("abc", new String(bytes));
    }
}
