package cn.chenzw.toolkit.core.codec.support.des;

/**
 * DES补码方式
 *
 * @author chenzw
 */
public enum DESPadding {

    /**
     * 无补码
     */
    NoPadding,
    /**
     * 0补码，即不满block长度时使用0填充
     */
    ZeroPadding,

    /**
     *
     */
    ISO10126Padding,

    /**
     *
     */
    OAEPPadding,

    /**
     *
     */
    PKCS1Padding,

    /**
     *
     */
    PKCS5Padding,

    PKCS7Padding,

    SSL3Padding
}
