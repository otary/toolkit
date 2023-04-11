package cn.chenzw.toolkit.core.lang;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;

/**
 * 进制工具
 *
 * @author chenzw
 */
public abstract class RadixKit {



    /**
     * byte数组 => 十六进制字符串
     *
     * @param bytes
     * @return
     * @deprecated use {@link org.apache.commons.codec.binary.Hex#encodeHexString(byte[])}
     */
    @Deprecated
    public static final String bytesToHexString(byte[] bytes) {
        StringBuilder buf = new StringBuilder();
        if (ArrayUtils.isEmpty(bytes)) {
            return null;
        }
        for (int i = 0; i < bytes.length; i++) {
            String hv = Integer.toHexString(bytes[i] & 0xFF);
            if (hv.length() < 2) {
                buf.append(0);
            }
            buf.append(hv);
        }
        return buf.toString();
    }


    /**
     * 十六进制字符串 => byte数组
     *
     * @return
     * @deprecated use {@link org.apache.commons.codec.binary.Hex#decodeHex(java.lang.String)}
     */
    @Deprecated
    public static final byte[] hexStringToBytes(String hexString) {
        if (StringUtils.isEmpty(hexString)) {
            return new byte[0];
        }
        hexString = hexString.toUpperCase();
        int length = hexString.length() / 2;
        char[] hexChars = hexString.toCharArray();
        byte[] bytes = new byte[length];
        for (int i = 0; i < length; i++) {
            int pos = i * 2;
            bytes[i] = (byte) (charToByte(hexChars[pos]) << 4 | (charToByte(hexChars[pos + 1]) & 0xff));
        }
        return bytes;
    }

    private static byte charToByte(char c) {
        return (byte) "0123456789ABCDEF".indexOf(c);
    }
}
