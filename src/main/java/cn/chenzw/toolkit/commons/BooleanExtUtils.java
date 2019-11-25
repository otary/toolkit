package cn.chenzw.toolkit.commons;

/**
 * Boolean类型工具类
 *
 * @author chenzw
 */
public class BooleanExtUtils {


    /**
     * boolean => int
     *
     * @param value
     * @return
     */
    public static int toInt(boolean value) {
        return value ? 1 : 0;
    }

    /**
     * boolean => Integer
     *
     * @param value
     * @return
     */
    public static Integer toInteger(boolean value) {
        return Integer.valueOf(toInt(value));
    }

    /**
     * boolean => byte
     *
     * @param value
     * @return
     */
    public static byte tobyte(boolean value) {
        return (byte) toInt(value);
    }


    /**
     * boolean => Byte
     *
     * @param value
     * @return
     */
    public static Byte toByte(boolean value) {
        return Byte.valueOf(toByte(value));
    }

    /**
     * boolean => long
     *
     * @param value
     * @return
     */
    public static long tolong(boolean value) {
        return (long) toInt(value);
    }

    /**
     * boolean => Long
     *
     * @param value
     * @return
     */
    public static Long toLong(boolean value) {
        return Long.valueOf(tolong(value));
    }

    /**
     * boolean => short
     *
     * @param value
     * @return
     */
    public static short toshort(boolean value) {
        return (short) toInt(value);
    }

    /**
     * boolean => Short
     *
     * @param value
     * @return
     */
    public static Short toShort(boolean value) {
        return Short.valueOf(toShort(value));
    }

    /**
     * boolean => float
     *
     * @param value
     * @return
     */
    public static float tofloat(boolean value) {
        return (float) toInt(value);
    }

    /**
     * boolean => Float
     *
     * @param value
     * @return
     */
    public static Float toFloat(boolean value) {
        return Float.valueOf(tofloat(value));
    }

    /**
     * boolean => double
     *
     * @param value
     * @return
     */
    public static double todouble(boolean value) {
        return (double) toInt(value);
    }

    /**
     * boolean => Double
     *
     * @param value
     * @return
     */
    public static Double toDouble(boolean value) {
        return Double.valueOf(todouble(value));
    }


    /**
     * boolean => char
     *
     * @param value
     * @return
     */
    public static char toChar(boolean value) {
        return (char) toInt(value);
    }

    /**
     * boolean => Character
     *
     * @param value
     * @return
     */
    public static Character toCharacter(boolean value) {
        return Character.valueOf(toChar(value));
    }
}
