package cn.chenzw.toolkit.codec;

import cn.chenzw.toolkit.codec.support.rsa.RSAKeySize;
import cn.chenzw.toolkit.codec.support.rsa.RSASignatureAlgorithm;
import org.bouncycastle.asn1.ASN1Encodable;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.pkcs.PrivateKeyInfo;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
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
     * @return 默认生成 PKCS#8 格式的私钥 及 X.509 格式的公钥
     * @throws NoSuchAlgorithmException
     */
    public static final KeyPair createKeyPair(RSAKeySize keySize) throws NoSuchAlgorithmException {
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        keyPairGenerator.initialize(keySize.value());
        return keyPairGenerator.generateKeyPair();
    }


    /**
     * 将私钥字符串转换成PrivateKey对象{@link java.security.PrivateKey}对象
     *
     * @param privateKey
     * @return {@link java.security.PrivateKey}对象
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeySpecException
     */
    public static final PrivateKey parsePrivateKey(String privateKey) throws NoSuchAlgorithmException, InvalidKeySpecException {
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(decodeBase64(privateKey.getBytes()));
        return keyFactory.generatePrivate(keySpec);
    }

    /**
     * 将PEM格式字符串转换成PrivateKey对象{@link java.security.PrivateKey}对象
     *
     * @param privateKey
     * @return {@link java.security.PrivateKey}对象
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeySpecException
     */
    public static final PrivateKey parsePEMAsPrivateKey(String privateKey) throws NoSuchAlgorithmException, InvalidKeySpecException {
        privateKey = privateKey.replaceAll("BEGIN PRIVATE KEY", "")
                .replaceAll("END PRIVATE KEY", "")
                .replaceAll("-", "");
        return parsePrivateKey(privateKey);
    }

    /**
     * 将公钥字符串转换成PublicKey{@link java.security.PublicKey}对象
     *
     * @param publicKey
     * @return {@link java.security.PublicKey}对象
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeySpecException
     */
    public static final PublicKey parsePublicKey(String publicKey) throws NoSuchAlgorithmException, InvalidKeySpecException {
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(decodeBase64(publicKey.getBytes()));
        return keyFactory.generatePublic(keySpec);
    }

    /**
     * 将PEM格式字符串转换成PublicKey对象{@link java.security.PublicKey}对象
     *
     * @param publicKey
     * @return {@link java.security.PublicKey}对象
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeySpecException
     */
    public static final PublicKey parsePEMAsPublicKey(String publicKey) throws NoSuchAlgorithmException, InvalidKeySpecException {
        publicKey = publicKey.replaceAll("BEGIN PUBLIC KEY", "")
                .replaceAll("END PUBLIC KEY", "")
                .replaceAll("-", "");
        return parsePublicKey(publicKey);
    }

    /**
     * 将Key转换成Base64字符串
     *
     * @param key
     * @return
     */
    public static final String parseKeyAsBase64String(Key key) {
        return new String(encodeBase64(key.getEncoded()), StandardCharsets.UTF_8);
    }

    /**
     * 公钥 转 PEM格式
     *
     * @param publicKey
     * @return
     */
    public static final String parseAsPEM(PublicKey publicKey) {
        return paseAsPublicKeyPEM(publicKey.getEncoded());
    }

    /**
     * 生成公钥 PEM 格式
     *
     * @param keyBytes
     * @return
     */
    public static final String paseAsPublicKeyPEM(byte[] keyBytes) {
        return "-----BEGIN PUBLIC KEY-----\n" + encodeBase64AsString(keyBytes) + "\n-----END PUBLIC KEY-----";
    }

    public static final String parseAsPrivateKeyPEM(byte[] keyBytes) {
        return "-----BEGIN PRIVATE KEY-----\n" + encodeBase64AsString(keyBytes) + "\n-----END PRIVATE KEY-----";
    }

    /**
     * 私钥 转 PEM格式
     *
     * @param privateKey
     * @return
     * @throws UnsupportedEncodingException
     */
    public static final String parseAsPEM(PrivateKey privateKey) {
        return parseAsPrivateKeyPEM(privateKey.getEncoded());
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

    /**
     * 将 PKCS#8 格式私钥 转换为 PKCS#1 格式
     *
     * @param pkcs8PrivateKeyBytes
     * @return
     * @throws IOException
     */
    public static byte[] convertPkcs8ToPkcs1(byte[] pkcs8PrivateKeyBytes) throws IOException {
        PrivateKeyInfo pkInfo = PrivateKeyInfo.getInstance(pkcs8PrivateKeyBytes);
        return pkInfo.parsePrivateKey().toASN1Primitive().getEncoded();
    }


    private final static byte[] decodeBase64(byte[] base64Bytes) {
        return Base64.getMimeDecoder().decode(base64Bytes);
    }

    private final static byte[] encodeBase64(byte[] base64Bytes) {
        return Base64.getEncoder().encode(base64Bytes);
    }

    private final static String encodeBase64AsString(byte[] base64Bytes) {
        return new String(encodeBase64(base64Bytes), StandardCharsets.UTF_8);
    }


}
