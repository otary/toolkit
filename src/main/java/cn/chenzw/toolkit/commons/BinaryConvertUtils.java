package cn.chenzw.toolkit.commons;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;

/**
 * 进制转换
 *
 * @auther chenzw
 */
public abstract class BinaryConvertUtils {


    /**
     * byte数组 => 十六进制字符串
     *
     * @param bytes
     * @return
     */
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
     */
    public static final byte[] hexStringToBytes(String hexString) {
        if (StringUtils.isEmpty(hexString)) {
            return null;
        }
        hexString = hexString.toUpperCase();
        int length = hexString.length() / 2;
        char[] hexChars = hexString.toCharArray();
        byte[] bytes = new byte[length];
        for (int i = 0; i < length; i++) {
            int pos = i * 2;
            bytes[i] = (byte) (charToByte(hexChars[pos]) << 4 | charToByte(hexChars[pos + 1]));
        }
        return bytes;
    }

    private static byte charToByte(char c) {
        return (byte) "0123456789ABCDEF".indexOf(c);
    }

}
