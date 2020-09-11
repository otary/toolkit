package cn.chenzw.toolkit.codec.support.aes;

/**
 * 加密模式
 * <table>
 *     <tr>
 *         <td>算法/模式/填充</td>
 *         <td>16字节加密后数据长度</td>
 *         <td>不满16字节加密后长度</td>
 *     </tr>
 *     <tr>
 *         <td>AES/CBC/NoPadding</td>
 *         <td>16</td>
 *         <td>不支持</td>
 *     </tr>
 *     <tr>
 *         <td>AES/CBC/PKCS5Padding</td>
 *         <td>32</td>
 *         <td>16</td>
 *     </tr>
 *     <tr>
 *         <td>AES/CBC/ISO10126Padding</td>
 *         <td>32</td>
 *         <td>16</td>
 *     </tr>
 *     <tr>
 *         <td>AES/CFB/NoPadding</td>
 *         <td>16</td>
 *         <td>原始长度</td>
 *     </tr>
 *     <tr>
 *         <td>AES/CFB/PKCS5Padding</td>
 *         <td>32</td>
 *         <td>16</td>
 *      </tr>
 *      <tr>
 *          <td>AES/CFB/ISO10126Padding</td>
 *          <td>32</td>
 *          <td>16</td>
 *      </tr>
 *      <tr>
 *          <td>AES/ECB/NoPadding</td>
 *          <td>16</td>
 *          <td>不支持</td>
 *      </tr>
 *      <tr>
 *          <td>AES/ECB/PKCS5Padding</td>
 *          <td>32</td>
 *          <td>16</td>
 *      </tr>
 *      <tr>
 *          <td>AES/ECB/ISO10126Padding</td>
 *          <td>32</td>
 *          <td>16</td>
 *      </tr>
 *      <tr>
 *          <td>AES/OFB/NoPadding</td>
 *          <td>16</td>
 *          <td>原始数据长度</td>
 *      </tr>
 *      <tr>
 *          <td>AES/OFB/PKCS5Padding</td>
 *          <td>32</td>
 *          <td>16</td>
 *      </tr>
 *      <tr>
 *          <td>AES/OFB/ISO10126Padding</td>
 *          <td>32</td>
 *          <td>16</td>
 *      </tr>
 *      <tr>
 *          <td>AES/PCBC/NoPadding</td>
 *          <td>16</td>
 *          <td>不支持</td>
 *      </tr>
 *      <tr>
 *          <td>AES/PCBC/PKCS5Padding</td>
 *          <td>32</td>
 *          <td>16</td>
 *      </tr>
 *      <tr>
 *          <td>AES/PCBC/ISO10126Padding</td>
 *          <td>32</td>
 *          <td>16</td>
 *      </tr>
 * </table>
 *
 * @author chenzw
 */
public enum AESMode {AES_DEFAULT("AES"), AES_CBC_NoPadding("AES/CBC/NoPadding"), AES_CBC_PKCS5Padding(
        "AES/CBC/PKCS5Padding"), AES_CBC_ISO10126Padding("AES/CBC/ISO10126Padding"), AES_CFB_NoPadding(
        "AES/CFB/NoPadding"), AES_CFB_PKCS5Padding("AES/CFB/PKCS5Padding"), AES_CFB_ISO10126Padding(
        "AES/CFB/ISO10126Padding"), AES_ECB_NoPadding("AES/ECB/NoPadding"), AES_ECB_PKCS5Padding(
        "AES/ECB/PKCS5Padding"), AES_ECB_ISO10126Padding("AES/ECB/ISO10126Padding"), AES_OFB_NoPadding(
        "AES/OFB/NoPadding"), AES_OFB_PKCS5Padding("AES/OFB/PKCS5Padding"), AES_OFB_ISO10126Padding(
        "AES/OFB/ISO10126Padding"), AES_PCBC_NoPadding("AES/PCBC/NoPadding"), AES_PCBC_PKCS5Padding(
        "AES/PCBC/PKCS5Padding"), AES_PCBC_ISO10126Padding("AES/PCBC/ISO10126Padding");

    private String modeAndPadding;

    AESMode(String modeAndPadding) {
        this.modeAndPadding = modeAndPadding;
    }

    public String modeAndPadding() {
        return modeAndPadding;
    }

}
