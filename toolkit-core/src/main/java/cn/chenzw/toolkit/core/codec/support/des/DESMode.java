package cn.chenzw.toolkit.core.codec.support.des;

/**
 * DES加密模式
 *
 * @author chenzw
 */
public enum DESMode {

    /**
     * 无模式
     */
    NONE,
    /**
     * 密码分组连接模式（Cipher Block Chaining）
     */
    CBC,
    /**
     * 密文反馈模式（Cipher Feedback）
     */
    CFB,
    /**
     * 计数器模式（A simplification of OFB）
     */
    CTR,
    /**
     * Cipher Text Stealing
     */
    CTS,
    /**
     * 电子密码本模式（Electronic CodeBook）
     */
    ECB,
    /**
     * 输出反馈模式（Output Feedback）
     */
    OFB,
    /**
     * Propagating Cipher Block
     */
    PCBC;


}
