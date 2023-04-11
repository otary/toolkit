package cn.chenzw.toolkit.core.codec.support.aes;

/**
 * @author chenzw
 */
public enum AESKeyMeta {

    BIT_128(128), BIT_192(192), BIT_256(256);

    private int bitLen;

    AESKeyMeta(int value) {
        this.bitLen = value;
    }

    public int bitLen() {
        return bitLen;
    }
}
