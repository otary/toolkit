package cn.chenzw.toolkit.core.codec;

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

@RunWith(JUnit4.class)
public class AESKitTests {

    /**
     * 测试生成Base64字符串AES加解密
     * @throws NoSuchPaddingException
     * @throws InvalidKeyException
     * @throws NoSuchAlgorithmException
     * @throws IllegalBlockSizeException
     * @throws BadPaddingException
     * @throws InvalidAlgorithmParameterException
     * @throws DecoderException
     */
    @Test
    public void testAsBase64()
            throws NoSuchPaddingException, InvalidKeyException, NoSuchAlgorithmException, IllegalBlockSizeException,
            BadPaddingException, InvalidAlgorithmParameterException, DecoderException {

        String plainText = "hello";
        String key = "123456789abcdefg";

        String base64String = AESKit.encryptAsBase64String(plainText, key);
        Assert.assertEquals("t3W/GjqldI8hPHvB7SP4AA==", base64String);

        byte[] bytes = AESKit.decryptBase64String(base64String, key);
        Assert.assertEquals(new String(bytes), plainText);

    }

    /**
     * 测试生成十六进制字符串AES加解密
     * @throws NoSuchPaddingException
     * @throws InvalidAlgorithmParameterException
     * @throws NoSuchAlgorithmException
     * @throws IllegalBlockSizeException
     * @throws BadPaddingException
     * @throws InvalidKeyException
     * @throws DecoderException
     */
    @Test
    public void testAsHexString()
            throws NoSuchPaddingException, InvalidAlgorithmParameterException, NoSuchAlgorithmException,
            IllegalBlockSizeException, BadPaddingException, InvalidKeyException, DecoderException {

        String plainText = "hello";
        String key = "123456789abcdefg";
        String hexString = AESKit.encryptAsHexString(plainText, key);
        Assert.assertEquals("b775bf1a3aa5748f213c7bc1ed23f800", hexString);

        byte[] bytes = AESKit.decryptHexString(hexString, key);
        Assert.assertEquals(new String(bytes), plainText);

    }


}
