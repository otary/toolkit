package cn.chenzw.toolkit.core.lang;

/**
 * 数值工具类
 *
 * @author chenzw
 */
public abstract class NumberKit {

    /**
     * 是否整数（正整数 + 负整数）
     *
     * @return
     */
    public static boolean isInteger(String str) {
        try {
            Integer.parseInt(str);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    /**
     * 是否数值（包括小数）
     *
     * @param number
     * @return
     */
    public static boolean isNumber(String number) {
        try {
            Double.parseDouble(number);
        } catch (Exception e) {
            return false;
        }
        return true;
    }


}
