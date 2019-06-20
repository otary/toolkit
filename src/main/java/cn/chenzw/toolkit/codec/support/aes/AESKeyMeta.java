package cn.chenzw.toolkit.codec.support.aes;

public enum AESKeyMeta {

    BIT_128(128), BIT_192(192), BIT_256(256);

    private int bitLen;

    private AESKeyMeta(int value) {
        this.bitLen = value;
    }

    public int getBitLen() {
        return bitLen;
    }

    public void setBitLen(int bitLen) {
        this.bitLen = bitLen;
    }
}
