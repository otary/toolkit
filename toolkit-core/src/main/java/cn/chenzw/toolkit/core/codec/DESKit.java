package cn.chenzw.toolkit.core.codec;


import cn.chenzw.toolkit.core.codec.support.des.DESMode;
import cn.chenzw.toolkit.core.codec.support.des.DESPadding;
import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Hex;

import javax.crypto.*;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.util.Base64;

/**
 * DES算法工具类
 *
 * @author
 */
public final class DESKit {

    private static final String DES_ALGORITHM = "DES";

    public static byte[] deigest(int mode, String desMode, String desPadding, byte[] data, String key, byte[] iv) throws NoSuchAlgorithmException, InvalidKeyException, InvalidKeySpecException, NoSuchPaddingException, InvalidAlgorithmParameterException, BadPaddingException, IllegalBlockSizeException {
        // 解决Liux、Mac系统异常
        SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
        random.setSeed(key.getBytes());

        DESKeySpec desKey = new DESKeySpec(key.getBytes());
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(DES_ALGORITHM);
        SecretKey secureKey = keyFactory.generateSecret(desKey);

        Cipher cipher = Cipher.getInstance(createModePadding(desMode, desPadding));

        IvParameterSpec ivParamSpec = null;
        if (!"ECB".equals(mode)) {
            // ECB不需要偏移量
            ivParamSpec = new IvParameterSpec(iv);
        }
        cipher.init(mode, secureKey, ivParamSpec);
        return cipher.doFinal(data);
    }


    /**
     * DES加密并转换成Base64字符串
     *
     * @param desMode    模式
     * @param desPadding 填充模式
     * @param data       待加密的数据
     * @param key        密码
     * @param iv         偏移量
     * @return
     * @throws NoSuchPaddingException
     * @throws InvalidAlgorithmParameterException
     * @throws NoSuchAlgorithmException
     * @throws IllegalBlockSizeException
     * @throws BadPaddingException
     * @throws InvalidKeyException
     * @throws InvalidKeySpecException
     */
    public static String encryptAsBase64String(DESMode desMode, DESPadding desPadding, byte[] data, String key, byte[] iv) throws NoSuchPaddingException, InvalidAlgorithmParameterException, NoSuchAlgorithmException, IllegalBlockSizeException, BadPaddingException, InvalidKeyException, InvalidKeySpecException {
        return new String(Base64.getEncoder().encode(deigest(Cipher.ENCRYPT_MODE, desMode.name(), desPadding.name(), data, key, iv)));
    }

    /**
     * DES加密并转换成十六进制字符串
     *
     * @param desMode    模式
     * @param desPadding 填充模式
     * @param data       待加密的数据
     * @param key        密码
     * @param iv         偏移量
     * @return
     * @throws NoSuchPaddingException
     * @throws InvalidAlgorithmParameterException
     * @throws NoSuchAlgorithmException
     * @throws IllegalBlockSizeException
     * @throws BadPaddingException
     * @throws InvalidKeyException
     * @throws InvalidKeySpecException
     */
    public static String encryptAsHexString(DESMode desMode, DESPadding desPadding, byte[] data, String key, byte[] iv) throws NoSuchPaddingException, InvalidAlgorithmParameterException, NoSuchAlgorithmException, IllegalBlockSizeException, BadPaddingException, InvalidKeyException, InvalidKeySpecException {
        return Hex.encodeHexString(deigest(Cipher.ENCRYPT_MODE, desMode.name(), desPadding.name(), data, key, iv));
    }

    /**
     * AES解密十六进制字符串
     *
     * @param hexString  十六进制字符串
     * @param key        密码
     * @param desMode    模式
     * @param desPadding 填充模式
     * @param iv         偏移量
     * @return
     * @throws DecoderException
     * @throws NoSuchPaddingException
     * @throws InvalidAlgorithmParameterException
     * @throws NoSuchAlgorithmException
     * @throws IllegalBlockSizeException
     * @throws BadPaddingException
     * @throws InvalidKeyException
     * @throws InvalidKeySpecException
     */
    public static byte[] decryptHexString(String hexString, String key, DESMode desMode, DESPadding desPadding,
                                          byte[] iv) throws DecoderException, NoSuchPaddingException, InvalidAlgorithmParameterException, NoSuchAlgorithmException, IllegalBlockSizeException, BadPaddingException, InvalidKeyException, InvalidKeySpecException {
        return deigest(Cipher.DECRYPT_MODE, desMode.name(), desPadding.name(), Hex.decodeHex(hexString), key, iv);
    }


    /**
     * AES解密Base64编码字符串
     *
     * @param base64String
     * @param key
     * @param desMode
     * @param desPadding
     * @param iv
     * @return
     * @throws NoSuchPaddingException
     * @throws InvalidAlgorithmParameterException
     * @throws NoSuchAlgorithmException
     * @throws IllegalBlockSizeException
     * @throws BadPaddingException
     * @throws InvalidKeyException
     * @throws InvalidKeySpecException
     */
    public static byte[] decryptBase64String(String base64String, String key, DESMode desMode, DESPadding desPadding,
                                             byte[] iv) throws NoSuchPaddingException, InvalidAlgorithmParameterException, NoSuchAlgorithmException, IllegalBlockSizeException, BadPaddingException, InvalidKeyException, InvalidKeySpecException {
        return deigest(Cipher.DECRYPT_MODE, desMode.name(), desPadding.name(), Base64.getDecoder().decode(base64String), key, iv);
    }

    private static String createModePadding(String mode, String padding) {
        return String.format("%s/%s/%s", DES_ALGORITHM, mode, padding);
    }


}
