package cn.chenzw.toolkit.codec;

import cn.chenzw.toolkit.codec.support.aes.AESKeyMeta;
import cn.chenzw.toolkit.codec.support.aes.AESMode;
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
public class AESUtilsTests {

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

        String base64String = AESUtils.encryptAsBase64String(plainText, key);
        Assert.assertEquals("t3W/GjqldI8hPHvB7SP4AA==", base64String);

        byte[] bytes = AESUtils.decryptBase64String(base64String, key);
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
        String hexString = AESUtils.encryptAsHexString(plainText, key);
        Assert.assertEquals("b775bf1a3aa5748f213c7bc1ed23f800", hexString);

        byte[] bytes = AESUtils.decryptHexString(hexString, key);
        Assert.assertEquals(new String(bytes), plainText);

       /* String aaa = AESUtils.encryptAsHexString("aaa", "11111111111111111111111111111111", AESMode.AES_ECB_PKCS5Padding, AESKeyMeta.BIT_128, null);

        System.out.println(aaa);*/

    }


}
