package cn.chenzw.toolkit.core.codec;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.regex.Pattern;

/**
 * Base58工具类
 *
 * @author chenzw
 */
public final class Base58Kit {

    private static final char[] ALPHABET = "123456789ABCDEFGHJKLMNPQRSTUVWXYZabcdefghijkmnopqrstuvwxyz".toCharArray();

    private static final char ALPHABET_ZERO = ALPHABET[0];

    private static final byte[] LOOKUP_TABLE = new byte[128];

    private static final int CHECKSUM_SIZE = 4;

    private static final Pattern BASE58_PATTERN = Pattern.compile("^[A-HJ-NP-Za-km-z1-9]*$");

    static {
        Arrays.fill(LOOKUP_TABLE, (byte) -1);
        for (int i = 0; i < ALPHABET.length; i++) {
            LOOKUP_TABLE[ALPHABET[i]] = (byte) i;
        }
    }

    /**
     * Base58编码
     *
     * @param data
     * @return
     */
    public static String encode(byte[] data) {
        if (data == null) {
            return null;
        }
        if (data.length == 0) {
            return "";
        }

        // 计算开头0的个数
        int zeroCount = 0;
        while (zeroCount < data.length && data[zeroCount] == 0) {
            ++zeroCount;
        }
        // 将256位编码转换为58位编码
        data = Arrays.copyOf(data, data.length); // since we modify it in-place
        final char[] encoded = new char[data.length * 2]; // upper bound
        int outputStart = encoded.length;
        for (int inputStart = zeroCount; inputStart < data.length; ) {
            encoded[--outputStart] = ALPHABET[divmod(data, inputStart, 256, 58)];
            if (data[inputStart] == 0) {
                ++inputStart; // optimization - skip leading zeros
            }
        }
        // Preserve exactly as many leading encoded zeros in output as there were leading zeros in input.
        while (outputStart < encoded.length && encoded[outputStart] == ALPHABET_ZERO) {
            ++outputStart;
        }
        while (--zeroCount >= 0) {
            encoded[--outputStart] = ALPHABET_ZERO;
        }
        // Return encoded string (including encoded leading zeros).
        return new String(encoded, outputStart, encoded.length - outputStart);
    }

    /**
     * Base58解码
     *
     * @param encoded
     * @return
     */
    public static byte[] decode(CharSequence encoded) {
        if (encoded.length() == 0) {
            return new byte[0];
        }
        // Convert the base58-encoded ASCII chars to a base58 byte sequence (base58 digits).
        final byte[] input58 = new byte[encoded.length()];
        for (int i = 0; i < encoded.length(); ++i) {
            char c = encoded.charAt(i);
            int digit = c < 128 ? LOOKUP_TABLE[c] : -1;
            if (digit < 0) {
                throw new IllegalArgumentException("Invalid char '" + c + "' at [" + i + "]");
            }
            input58[i] = (byte) digit;
        }
        // Count leading zeros.
        int zeros = 0;
        while (zeros < input58.length && input58[zeros] == 0) {
            ++zeros;
        }
        // Convert base-58 digits to base-256 digits.
        byte[] decoded = new byte[encoded.length()];
        int outputStart = decoded.length;
        for (int inputStart = zeros; inputStart < input58.length; ) {
            decoded[--outputStart] = divmod(input58, inputStart, 58, 256);
            if (input58[inputStart] == 0) {
                ++inputStart; // optimization - skip leading zeros
            }
        }
        // Ignore extra leading zeroes that were added during the calculation.
        while (outputStart < decoded.length && decoded[outputStart] == 0) {
            ++outputStart;
        }
        // Return decoded data (including original number of leading zeros).
        return Arrays.copyOfRange(decoded, outputStart - zeros, decoded.length);
    }

    /**
     * Base58解码并转换成字符串
     *
     * @param encoded
     * @return
     */
    public static String decodeAsString(CharSequence encoded) {
        return new String(decode(encoded), StandardCharsets.UTF_8);
    }

    /**
     * Base58编码（包含版本位和校验位）
     *
     * @param version 编码版本，{@code null}表示不包含版本位
     * @param data    被编码的数组，添加校验和。
     * @return 编码后的字符串
     */
    public static String encodeChecked(Integer version, byte[] data) {
        return encode(addChecksum(version, data));
    }


    /**
     * Base58解码（解码包含标志位验证和版本呢位去除）
     *
     * @param encoded 被解码的base58字符串
     * @return 解码后的bytes
     */
    public static byte[] decodeChecked(CharSequence encoded) {
        return decodeChecked(encoded, true);
    }

    /**
     * Base58解码（解码包含标志位验证和版本呢位去除）
     *
     * @param encoded     被解码的base58字符串
     * @param withVersion 是否包含版本位
     * @return 解码后的bytes
     */
    public static byte[] decodeChecked(CharSequence encoded, boolean withVersion) {
        byte[] valueWithChecksum = decode(encoded);
        return verifyAndRemoveChecksum(valueWithChecksum, withVersion);
    }

    /**
     * 是否Base58编码字符串
     *
     * @param encoded
     * @return
     */
    public static boolean isBase58(CharSequence encoded) {
        return BASE58_PATTERN.matcher(encoded).matches();
    }

    /**
     * 数据 + 校验码
     *
     * @param version 版本，{@code null}表示不添加版本位
     * @param payload Base58数据（不含校验码）
     * @return Base58数据
     */
    private static byte[] addChecksum(Integer version, byte[] payload) {
        final byte[] addressBytes;
        if (null != version) {
            addressBytes = new byte[1 + payload.length + CHECKSUM_SIZE];
            addressBytes[0] = (byte) version.intValue();
            System.arraycopy(payload, 0, addressBytes, 1, payload.length);
        } else {
            addressBytes = new byte[payload.length + CHECKSUM_SIZE];
            System.arraycopy(payload, 0, addressBytes, 0, payload.length);
        }
        final byte[] checksum = checksum(payload);
        System.arraycopy(checksum, 0, addressBytes, addressBytes.length - CHECKSUM_SIZE, CHECKSUM_SIZE);
        return addressBytes;
    }

    /**
     * 获取校验码<br>
     * 计算规则为对数据进行两次sha256计算，然后取{@link #CHECKSUM_SIZE}长度
     *
     * @param data 数据
     * @return 校验码
     */
    private static byte[] checksum(byte[] data) {
        byte[] hash = hash256(hash256(data));
        return Arrays.copyOfRange(hash, 0, CHECKSUM_SIZE);
    }

    /**
     * 计算数据的SHA-256值
     *
     * @param data 数据
     * @return sha-256值
     */
    private static byte[] hash256(byte[] data) {
        try {
            return MessageDigest.getInstance("SHA-256").digest(data);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 验证并去除验证位和版本位
     *
     * @param data        编码的数据
     * @param withVersion 是否包含版本位
     * @return 载荷数据
     */
    private static byte[] verifyAndRemoveChecksum(byte[] data, boolean withVersion) {
        final byte[] payload = Arrays.copyOfRange(data, withVersion ? 1 : 0, data.length - CHECKSUM_SIZE);
        final byte[] checksum = Arrays.copyOfRange(data, data.length - CHECKSUM_SIZE, data.length);
        final byte[] expectedChecksum = checksum(payload);
        if (false == Arrays.equals(checksum, expectedChecksum)) {
            throw new IllegalStateException("Base58 checksum is invalid");
        }
        return payload;
    }

    /**
     * Divides a number, represented as an array of bytes each containing a single digit
     * in the specified base, by the given divisor. The given number is modified in-place
     * to contain the quotient, and the return value is the remainder.
     *
     * @param number     the number to divide
     * @param firstDigit the index within the array of the first non-zero digit
     *                   (this is used for optimization by skipping the leading zeros)
     * @param base       the base in which the number's digits are represented (up to 256)
     * @param divisor    the number to divide by (up to 256)
     * @return the remainder of the division operation
     */
    private static byte divmod(byte[] number, int firstDigit, int base, int divisor) {
        // this is just long division which accounts for the base of the input digits
        int remainder = 0;
        for (int i = firstDigit; i < number.length; i++) {
            int digit = (int) number[i] & 0xFF;
            int temp = remainder * base + digit;
            number[i] = (byte) (temp / divisor);
            remainder = temp % divisor;
        }
        return (byte) remainder;
    }
}
