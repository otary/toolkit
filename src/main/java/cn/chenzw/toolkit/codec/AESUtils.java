package cn.chenzw.toolkit.codec;


import cn.chenzw.toolkit.codec.support.aes.AESKeyMeta;
import cn.chenzw.toolkit.codec.support.aes.AESMode;
import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.Hex;
import org.apache.commons.lang3.StringUtils;

import javax.crypto.*;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

/**
 *  AES加密算法工具类
 *
 * @author chenzw
 */
public abstract class AESUtils {

    private static final String AES_ALGORITHM = "AES";

    private AESUtils() {
    }

    /**
     * AES加解密
     * @param mode {@link javax.crypto.Cipher#ENCRYPT_MODE}、{@link javax.crypto.Cipher#DECRYPT_MODE}
     * @param data 待加密数据
     * @param key 密钥
     * @param aesMode 模式/填充 {@link cn.chenzw.toolkit.codec.support.aes.AESMode}
     * @param aesKeyMeta 密钥长度 {@link cn.chenzw.toolkit.codec.support.aes.AESKeyMeta}
     * @param iv 初始向量(Initialization vector, 必须是16位)
     * @return
     * @throws NoSuchAlgorithmException
     * @throws NoSuchPaddingException
     * @throws InvalidAlgorithmParameterException
     * @throws InvalidKeyException
     * @throws BadPaddingException
     * @throws IllegalBlockSizeException
     */
    public static byte[] deigest(int mode, byte[] data, String key, AESMode aesMode, AESKeyMeta aesKeyMeta, byte[] iv)
            throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidAlgorithmParameterException,
            InvalidKeyException, BadPaddingException, IllegalBlockSizeException {

        if (data == null) {
            throw new IllegalArgumentException("Parameter \"data\" must not be null");
        }

        if (StringUtils.isBlank(key)) {
            throw new IllegalArgumentException("Parameter \"key\" must not be null");
        }

        if (aesMode == null) {
            throw new IllegalArgumentException("Parameter \"aesMod\" must not be null");
        }

        if (aesKeyMeta == null) {
            throw new IllegalArgumentException("Parameter \"aesKeyMeta\" must not be null");
        }

        // 解决Liux、Mac系统异常
        SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
        random.setSeed(key.getBytes());

        KeyGenerator kgen = KeyGenerator.getInstance(AES_ALGORITHM);
        kgen.init(aesKeyMeta.getBitLen(), random);
        SecretKey secretKey = kgen.generateKey();
        byte[] enCodeFormat = secretKey.getEncoded();
        SecretKeySpec keySpec = new SecretKeySpec(enCodeFormat, AES_ALGORITHM);

        // 创建密码器
        Cipher cipher = Cipher.getInstance(aesMode.getModeAndPadding());
        if (iv != null) {
            // 指定初始化向量(Initialization vector, 必须是16位)
            cipher.init(mode, keySpec, new IvParameterSpec(iv));
        } else {
            cipher.init(mode, keySpec);
        }
        return cipher.doFinal(data);
    }

    /**
     * AES加密并转换成十六进制字符串
     * @param data 待加密数据
     * @param key 密钥
     * @param aesMode 模式/填充 {@link cn.chenzw.toolkit.codec.support.aes.AESMode}
     * @param aesKeyMeta 密钥长度 {@link cn.chenzw.toolkit.codec.support.aes.AESKeyMeta}
     * @param iv
     * @return
     * @throws NoSuchPaddingException
     * @throws InvalidKeyException
     * @throws NoSuchAlgorithmException
     * @throws IllegalBlockSizeException
     * @throws BadPaddingException
     * @throws InvalidAlgorithmParameterException
     */
    public static String encryptAsHexString(String data, String key, AESMode aesMode, AESKeyMeta aesKeyMeta, byte[] iv)
            throws NoSuchPaddingException, InvalidKeyException, NoSuchAlgorithmException, IllegalBlockSizeException,
            BadPaddingException, InvalidAlgorithmParameterException {
        byte[] bytes = deigest(Cipher.ENCRYPT_MODE, data.getBytes(), key, aesMode, aesKeyMeta, iv);
        return Hex.encodeHexString(bytes);
    }

    /**
     * AES加解密
     * @param data 待加密数据
     * @param key 密钥
     * @return
     * @throws NoSuchPaddingException
     * @throws InvalidKeyException
     * @throws NoSuchAlgorithmException
     * @throws IllegalBlockSizeException
     * @throws BadPaddingException
     * @throws InvalidAlgorithmParameterException
     */
    public static String encryptAsHexString(String data, String key)
            throws NoSuchPaddingException, InvalidKeyException, NoSuchAlgorithmException, IllegalBlockSizeException,
            BadPaddingException, InvalidAlgorithmParameterException {
        return encryptAsHexString(data, key, AESMode.AES_DEFAULT, AESKeyMeta.BIT_256, null);
    }

    /**
     * AES解密十六进制字符串
     * @param hexString  十六进制字符串
     * @param key  密钥
     * @param aesMode 模式/填充 {@link cn.chenzw.toolkit.codec.support.aes.AESMode}
     * @param aesKeyMeta 密钥长度 {@link cn.chenzw.toolkit.codec.support.aes.AESKeyMeta}
     * @param iv 初始向量(Initialization vector, 必须是16位)
     * @return
     * @throws NoSuchPaddingException
     * @throws InvalidKeyException
     * @throws NoSuchAlgorithmException
     * @throws IllegalBlockSizeException
     * @throws BadPaddingException
     * @throws InvalidAlgorithmParameterException
     * @throws DecoderException
     */
    public static byte[] decryptHexString(String hexString, String key, AESMode aesMode, AESKeyMeta aesKeyMeta,
            byte[] iv)
            throws NoSuchPaddingException, InvalidKeyException, NoSuchAlgorithmException, IllegalBlockSizeException,
            BadPaddingException, InvalidAlgorithmParameterException, DecoderException {
        return deigest(Cipher.DECRYPT_MODE, Hex.decodeHex(hexString), key, aesMode, aesKeyMeta, iv);
    }

    /**
     * AES解密十六进制字符串
     * @param hexString  十六进制字符串
     * @param key  密钥
     * @return
     * @throws NoSuchPaddingException
     * @throws InvalidAlgorithmParameterException
     * @throws NoSuchAlgorithmException
     * @throws IllegalBlockSizeException
     * @throws BadPaddingException
     * @throws InvalidKeyException
     * @throws DecoderException
     */
    public static byte[] decryptHexString(String hexString, String key)
            throws NoSuchPaddingException, InvalidAlgorithmParameterException, NoSuchAlgorithmException,
            IllegalBlockSizeException, BadPaddingException, InvalidKeyException, DecoderException {
        return decryptHexString(hexString, key, AESMode.AES_DEFAULT, AESKeyMeta.BIT_256, null);
    }

    /**
     * AES加密并转换成Base64字符串
     * @param data 待加密数据
     * @param key 密钥
     * @param aesMode 模式/填充 {@link cn.chenzw.toolkit.codec.support.aes.AESMode}
     * @param aesKeyMeta 密钥长度 {@link cn.chenzw.toolkit.codec.support.aes.AESKeyMeta}
     * @param iv 初始向量(Initialization vector, 必须是16位)
     * @return
     * @throws NoSuchPaddingException
     * @throws InvalidKeyException
     * @throws NoSuchAlgorithmException
     * @throws IllegalBlockSizeException
     * @throws BadPaddingException
     * @throws InvalidAlgorithmParameterException
     */
    public static String encryptAsBase64String(String data, String key, AESMode aesMode, AESKeyMeta aesKeyMeta,
            byte[] iv)
            throws NoSuchPaddingException, InvalidKeyException, NoSuchAlgorithmException, IllegalBlockSizeException,
            BadPaddingException, InvalidAlgorithmParameterException {
        byte[] bytes = deigest(Cipher.ENCRYPT_MODE, data.getBytes(), key, aesMode, aesKeyMeta, iv);
        return Base64.encodeBase64String(bytes);
    }

    /**
     * AES加密并转换成Base64字符串
     * @param data 待加密数据
     * @param key 密钥
     * @return
     * @throws NoSuchPaddingException
     * @throws InvalidAlgorithmParameterException
     * @throws NoSuchAlgorithmException
     * @throws IllegalBlockSizeException
     * @throws BadPaddingException
     * @throws InvalidKeyException
     */
    public static String encryptAsBase64String(String data, String key)
            throws NoSuchPaddingException, InvalidAlgorithmParameterException, NoSuchAlgorithmException,
            IllegalBlockSizeException, BadPaddingException, InvalidKeyException {
        return encryptAsBase64String(data, key, AESMode.AES_DEFAULT, AESKeyMeta.BIT_256, null);
    }

    /**
     * AES解密Base64编码字符串
     * @param base64String Base64编码的字符串
     * @param key 密钥
     * @param aesMode 模式/填充 {@link AESMode}
     * @param aesKeyMeta 密钥长度 {@link AESKeyMeta}
     * @param iv 初始向量(Initialization vector, 必须是16位)
     * @return
     * @throws NoSuchPaddingException
     * @throws InvalidKeyException
     * @throws NoSuchAlgorithmException
     * @throws IllegalBlockSizeException
     * @throws BadPaddingException
     * @throws InvalidAlgorithmParameterException
     */
    public static byte[] decryptBase64String(String base64String, String key, AESMode aesMode, AESKeyMeta aesKeyMeta,
            byte[] iv)
            throws NoSuchPaddingException, InvalidKeyException, NoSuchAlgorithmException, IllegalBlockSizeException,
            BadPaddingException, InvalidAlgorithmParameterException {
        return deigest(Cipher.DECRYPT_MODE, Base64.decodeBase64(base64String), key, aesMode, aesKeyMeta, iv);
    }

    /**
     * AES解密Base64编码字符串
     * @param base64String Base64编码的字符串
     * @param key 密钥
     * @return
     */
    public static byte[] decryptBase64String(String base64String, String key)
            throws NoSuchPaddingException, InvalidAlgorithmParameterException, NoSuchAlgorithmException,
            IllegalBlockSizeException, BadPaddingException, InvalidKeyException {
        return decryptBase64String(base64String, key, AESMode.AES_DEFAULT, AESKeyMeta.BIT_256, null);
    }

}
