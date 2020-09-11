package cn.chenzw.toolkit.codec.support.rsa;

/**
 * RSA 密钥位数
 *
 * @author chenzw
 */
public enum RSAKeySize {

    BIT_512(512),
    BIT_1024(1024),
    BIT_2048(2048),
    BIT_4096(4096);

    private int keySize;

    RSAKeySize(int keySize) {
        this.keySize = keySize;
    }

    public int value() {
        return keySize;
    }
}
