package cn.chenzw.toolkit.codec;

import cn.chenzw.toolkit.codec.support.rsa.RSAKeySize;
import cn.chenzw.toolkit.codec.support.rsa.RSASignatureAlgorithm;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.UnsupportedEncodingException;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

/**
 * RSA工具类
 *
 * @author chenzw
 */
public final class RSAUtils {


    /**
     * 生成密钥对
     *
     * @param keySize
     * @return
     * @throws NoSuchAlgorithmException
     */
    public static final KeyPair createKeyPair(RSAKeySize keySize) throws NoSuchAlgorithmException {
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        keyPairGenerator.initialize(keySize.value());
        return keyPairGenerator.generateKeyPair();
    }


    /**
     * 将私钥字符串转换成PrivateKey对象
     *
     * @param privateKey
     * @return
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeySpecException
     */
    public static final PrivateKey parsePrivateKey(String privateKey) throws NoSuchAlgorithmException, InvalidKeySpecException {
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        byte[] decodeBase64 = Base64.getMimeDecoder().decode(privateKey.getBytes());
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(decodeBase64);
        return keyFactory.generatePrivate(keySpec);
    }

    /**
     * 将公钥字符串转换成PublicKey{@link java.security.PublicKey}对象
     *
     * @param publicKey
     * @return
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeySpecException
     */
    public static final PublicKey parsePublicKey(String publicKey) throws NoSuchAlgorithmException, InvalidKeySpecException {
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        byte[] decodedKey = Base64.getMimeDecoder().decode(publicKey.getBytes());
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(decodedKey);
        return keyFactory.generatePublic(keySpec);
    }

    /**
     * 将Key转换成Base64字符串
     *
     * @param key
     * @return
     * @throws UnsupportedEncodingException
     */
    public static final String parseKeyAsBase64String(Key key) throws UnsupportedEncodingException {
        return new String(Base64.getEncoder().encode(key.getEncoded()), "UTF-8");
    }

    /**
     * 公钥转PEM格式
     *
     * @param publicKey
     * @return
     * @throws UnsupportedEncodingException
     */
    public static final String parseAsPEM(PublicKey publicKey) throws UnsupportedEncodingException {
        return "-----BEGIN PUBLIC KEY-----\n" + parseKeyAsBase64String(publicKey) + "\n-----END PUBLIC KEY-----";
    }

    /**
     * 私钥转PEM格式
     *
     * @param privateKey
     * @return
     * @throws UnsupportedEncodingException
     */
    public static final String parseAsPEM(PrivateKey privateKey) throws UnsupportedEncodingException {
        return "-----BEGIN PRIVATE KEY-----\n" + parseKeyAsBase64String(privateKey) + "\n-----END PRIVATE KEY-----";
    }

    /**
     * 加密
     *
     * @param data
     * @param key
     * @return
     * @throws InvalidKeyException
     * @throws NoSuchPaddingException
     * @throws NoSuchAlgorithmException
     * @throws BadPaddingException
     * @throws IllegalBlockSizeException
     */
    public static final byte[] encrypt(byte[] data, Key key) throws InvalidKeyException, NoSuchPaddingException, NoSuchAlgorithmException, BadPaddingException, IllegalBlockSizeException {
        // 初始化加密器
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE, key);
        return cipher.doFinal(data);
    }

    /**
     * 解密
     *
     * @param data
     * @param key
     * @return
     * @throws NoSuchPaddingException
     * @throws NoSuchAlgorithmException
     * @throws BadPaddingException
     * @throws IllegalBlockSizeException
     * @throws InvalidKeyException
     */
    public static final byte[] decrypt(byte[] data, Key key) throws NoSuchPaddingException, NoSuchAlgorithmException, BadPaddingException, IllegalBlockSizeException, InvalidKeyException {
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.DECRYPT_MODE, key);
        return cipher.doFinal(data);
    }

    /**
     * 签名（私钥签名）
     *
     * @param data
     * @param privateKey
     * @param signatureAlgorithm
     * @return
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeyException
     * @throws SignatureException
     */
    public static byte[] sign(byte[] data, PrivateKey privateKey, RSASignatureAlgorithm signatureAlgorithm) throws NoSuchAlgorithmException, InvalidKeyException, SignatureException {
        Signature signature = Signature.getInstance(signatureAlgorithm.value());
        signature.initSign(privateKey);
        signature.update(data);
        return signature.sign();
    }

    /**
     * 验证（私钥验证）
     *
     * @param data               待验证的内容
     * @param signedData
     * @param publicKey
     * @param signatureAlgorithm
     * @return
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeyException
     * @throws SignatureException
     */
    public static boolean verify(byte[] data, byte[] signedData, PublicKey publicKey, RSASignatureAlgorithm signatureAlgorithm) throws NoSuchAlgorithmException, InvalidKeyException, SignatureException {
        Signature signature = Signature.getInstance(signatureAlgorithm.value());
        signature.initVerify(publicKey);
        signature.update(data);
        return signature.verify(signedData);
    }

}
