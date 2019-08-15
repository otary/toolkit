package cn.chenzw.toolkit.commons;

import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Hex;
import org.apache.commons.lang3.SerializationUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;

/**
 * 扩展org.springframework.util.SerializationUtils
 *
 * @see {@link org.springframework.util.SerializationUtils}
 * @author chenzw
 */
public class SerializationExtUtils {

    private SerializationExtUtils() {
    }


    /**
     * 序列化对象
     * @param object
     * @return
     */
    public static String serialize(final Serializable object) {
        if (object == null) {
            return null;
        }

        byte[] serializedBytes = SerializationUtils.serialize(object);
        return Hex.encodeHexString(serializedBytes);
    }

    /**
     * 反序列化对象
     * @param hexString
     * @param <T>
     * @return
     */
    public static <T> T deserialize(final String hexString) {
        if (StringUtils.isEmpty(hexString)) {
            return null;
        }
        try {
            return (T) SerializationUtils.deserialize(Hex.decodeHex(hexString.toCharArray()));
        } catch (DecoderException e) {
            e.printStackTrace();
        }
        return null;
    }
}
