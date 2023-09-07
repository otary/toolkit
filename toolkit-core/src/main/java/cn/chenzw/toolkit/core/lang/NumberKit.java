package cn.chenzw.toolkit.core.lang;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.Assert;

import java.math.BigDecimal;
import java.math.RoundingMode;

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

    /**
     * 相加
     *
     * @param values
     * @return
     */
    public static BigDecimal add(BigDecimal... values) {
        if (ArrayUtils.isEmpty(values)) {
            return BigDecimal.ZERO;
        }

        BigDecimal rst = (values[0] == null ? BigDecimal.ZERO : values[0]);
        for (int i = 1; i < values.length; i++) {
            BigDecimal value = values[i];
            if (value != null) {
                rst = rst.add(value);
            }
        }
        return rst;
    }

    /**
     * 相加
     *
     * @param values
     * @return
     */
    public static BigDecimal add(String... values) {
        if (ArrayUtils.isEmpty(values)) {
            return BigDecimal.ZERO;
        }

        BigDecimal rst = (values[0] == null ? BigDecimal.ZERO : new BigDecimal(values[0]));
        for (int i = 1; i < values.length; i++) {
            String value = values[i];
            if (value != null) {
                rst = rst.add(new BigDecimal(value));
            }
        }
        return rst;
    }

    /**
     * 相加
     *
     * @param values
     * @return
     */
    public static BigDecimal add(Number... values) {
        if (ArrayUtils.isEmpty(values)) {
            return BigDecimal.ZERO;
        }

        BigDecimal rst = (values[0] == null ? BigDecimal.ZERO : new BigDecimal(values[0].toString()));
        for (int i = 1; i < values.length; i++) {
            Number value = values[i];
            if (value != null) {
                rst = rst.add(new BigDecimal(value.toString()));
            }
        }
        return rst;
    }

    /**
     * 相加
     *
     * @param a1
     * @param a2
     * @return
     */
    public static double add(double a1, double a2) {
        return add(Double.toString(a1), Double.toString(a2)).doubleValue();
    }


    /**
     * 相减
     *
     * @param values
     * @return
     */
    public static BigDecimal sub(String... values) {
        if (ArrayUtils.isEmpty(values)) {
            return BigDecimal.ZERO;
        }
        BigDecimal rst = (values[0] == null ? BigDecimal.ZERO : new BigDecimal(values[0]));
        for (int i = 1; i < values.length; i++) {
            String value = values[i];
            if (value != null) {
                rst = rst.subtract(new BigDecimal(value));
            }
        }
        return rst;
    }

    /**
     * 相减
     *
     * @param values
     * @return
     */
    public static BigDecimal sub(Number... values) {
        if (ArrayUtils.isEmpty(values)) {
            return BigDecimal.ZERO;
        }
        BigDecimal rst = (values[0] == null ? BigDecimal.ZERO : new BigDecimal(values[0].toString()));
        for (int i = 1; i < values.length; i++) {
            Number value = values[i];
            if (value != null) {
                rst = rst.subtract(new BigDecimal(value.toString()));
            }
        }
        return rst;
    }

    /**
     * 相减
     *
     * @param a1
     * @param a2
     * @return
     */
    public static double sub(double a1, double a2) {
        return sub(Double.toString(a1), Double.toString(a2)).doubleValue();
    }

    /**
     * 相减
     *
     * @param a1
     * @param a2
     * @return
     */
    public static double sub(float a1, double a2) {
        return sub(Float.toString(a1), Double.toString(a2)).doubleValue();
    }

    /**
     * 相减
     *
     * @param a1
     * @param a2
     * @return
     */
    public static double sub(double a1, float a2) {
        return sub(Double.toString(a1), Float.toString(a2)).doubleValue();
    }


    /**
     * 相乘
     *
     * @param values
     * @return
     */
    public static BigDecimal mul(String... values) {
        if (ArrayUtils.isEmpty(values) || ArrayUtils.contains(values, null)) {
            return BigDecimal.ZERO;
        }
        BigDecimal rst = new BigDecimal(1);
        for (String value : values) {
            rst = rst.multiply(new BigDecimal(value));
        }
        return rst;
    }

    /**
     * 相乘
     *
     * @param values
     * @return
     */
    public static BigDecimal mul(BigDecimal... values) {
        if (ArrayUtils.isEmpty(values) || ArrayUtils.contains(values, null)) {
            return BigDecimal.ZERO;
        }
        BigDecimal rst = new BigDecimal(1);
        for (BigDecimal value : values) {
            rst = rst.multiply(value);
        }
        return rst;
    }

    /**
     * 相乘
     *
     * @param values
     * @return
     */
    public static BigDecimal mul(Number... values) {
        if (ArrayUtils.isEmpty(values) || ArrayUtils.contains(values, null)) {
            return BigDecimal.ZERO;
        }

        BigDecimal rst = (values[0] == null ? BigDecimal.ZERO : new BigDecimal(values[0].toString()));
        for (int i = 1; i < values.length; i++) {
            Number value = values[i];
            rst = rst.multiply(new BigDecimal(value.toString()));
        }
        return rst;
    }

    /**
     * 相乘
     *
     * @param a1
     * @param a2
     * @return
     */
    public static double mul(double a1, double a2) {
        return mul(Double.toString(a1), Double.toString(a2)).doubleValue();
    }

    /**
     * 相乘
     *
     * @param a1
     * @param a2
     * @return
     */
    public static double mul(double a1, float a2) {
        return mul(Double.toString(a1), Float.toString(a2)).doubleValue();
    }


    /**
     * 相乘
     *
     * @param a1
     * @param a2
     * @return
     */
    public static double mul(float a1, double a2) {
        return mul(Float.toString(a1), Double.toString(a2)).doubleValue();
    }


    /**
     * 相乘
     *
     * @param a1
     * @param a2
     * @return
     */
    public static double mul(float a1, float a2) {
        return mul(Float.toString(a1), Float.toString(a2)).doubleValue();
    }


    /**
     * 相除
     *
     * @param a1
     * @param a2
     * @param scale
     * @param roundingMode
     * @return
     */
    public static BigDecimal div(BigDecimal a1, BigDecimal a2, int scale, RoundingMode roundingMode) {
        Assert.notNull(a2, "Divisor must be not null !");
        if (a1 == null) {
            return BigDecimal.ZERO;
        }
        if (scale < 0) {
            scale = -scale;
        }
        return a1.divide(a2, scale, roundingMode);
    }

    /**
     * 相除
     *
     * @param a1
     * @param a2
     * @param scale
     * @param roundingMode
     * @return
     */
    public static BigDecimal div(String a1, String a2, int scale, RoundingMode roundingMode) {
        return div(new BigDecimal(a1), new BigDecimal(a2), scale, roundingMode);
    }

    /**
     * 相除
     *
     * @param a1
     * @param a2
     * @param scale
     * @param roundingMode
     * @return
     */
    public static BigDecimal div(Number a1, Number a2, int scale, RoundingMode roundingMode) {
        return div(a1.toString(), a2.toString(), scale, roundingMode);
    }


    /**
     * 相除
     *
     * @param a1
     * @param a2
     * @param scale
     * @param roundingMode
     * @return
     */
    public static double div(Double a1, Double a2, int scale, RoundingMode roundingMode) {
        return div((Number) a1, (Number) a2, scale, roundingMode).doubleValue();
    }

    /**
     * 相除
     *
     * @param a1
     * @param a2
     * @param scale
     * @param roundingMode
     * @return
     */
    public static double div(double a1, double a2, int scale, RoundingMode roundingMode) {
        return div(Double.toString(a1), Double.toString(a2), scale, roundingMode).doubleValue();
    }

    /**
     * 相除
     *
     * @param a1
     * @param a2
     * @param scale
     * @param roundingMode
     * @return
     */
    public static double div(float a1, double a2, int scale, RoundingMode roundingMode) {
        return div(Float.toString(a1), Double.toString(a2), scale, roundingMode).doubleValue();
    }

    /**
     * 相除
     *
     * @param a1
     * @param a2
     * @param scale
     * @param roundingMode
     * @return
     */
    public static double div(double a1, float a2, int scale, RoundingMode roundingMode) {
        return div(Double.toString(a1), Float.toString(a2), scale, roundingMode).doubleValue();
    }

    /**
     * 相除
     *
     * @param a1
     * @param a2
     * @param scale
     * @param roundingMode
     * @return
     */
    public static double div(float a1, float a2, int scale, RoundingMode roundingMode) {
        return div(Float.toString(a1), Float.toString(a2), scale, roundingMode).doubleValue();
    }

    /**
     * 相除
     *
     * @param a1
     * @param a2
     * @param scale
     * @return
     */
    public static BigDecimal div(String a1, String a2, int scale) {
        return div(a1, a2, scale, RoundingMode.HALF_UP);
    }

    /**
     * 相除
     *
     * @param a1
     * @param a2
     * @param scale
     * @return
     */
    public static BigDecimal div(Number a1, Number a2, int scale) {
        return div(a1, a2, scale, RoundingMode.HALF_UP);
    }

    /**
     * 相除
     *
     * @param a1
     * @param a2
     * @param scale
     * @return
     */
    public static double div(double a1, double a2, int scale) {
        return div(a1, a2, scale, RoundingMode.HALF_UP);
    }

    /**
     * 相除
     *
     * @param a1
     * @param a2
     * @return
     */
    public static BigDecimal div(String a1, String a2) {
        return div(a1, a2, 10);
    }

    /**
     * 相除
     *
     * @param a1
     * @param a2
     * @return
     */
    public static BigDecimal div(Number a1, Number a2) {
        return div(a1, a2, 10);
    }

    /**
     * 相除
     *
     * @param a1
     * @param a2
     * @return
     */
    public static double div(double a1, double a2) {
        return div(a1, a2, 10);
    }

    /**
     * 相除
     *
     * @param a1
     * @param a2
     * @return
     */
    public static double div(float a1, float a2) {
        return div(a1, a2, 10);
    }

    /**
     * 相除
     *
     * @param a1
     * @param a2
     * @return
     */
    public static double div(float a1, double a2) {
        return div(a1, a2, 10);
    }


    /**
     * 精度设置
     *
     * @param v
     * @param scale
     * @param roundingMode
     * @return
     */
    public static BigDecimal round(double v, int scale, RoundingMode roundingMode) {
        return round(Double.toString(v), scale, roundingMode);
    }

    /**
     * 精度设置
     *
     * @param n
     * @param scale
     * @param roundingMode
     * @return
     */
    public static BigDecimal round(String n, int scale, RoundingMode roundingMode) {
        if (scale < 0) {
            scale = 0;
        }
        return round(
                (StringUtils.isBlank(n) ? BigDecimal.ZERO : new BigDecimal(n)), scale, roundingMode
        );
    }

    /**
     * 精度设置
     *
     * @param n
     * @param scale
     * @param roundingMode
     * @return
     */
    public static BigDecimal round(BigDecimal n, int scale, RoundingMode roundingMode) {
        if (n == null) {
            n = BigDecimal.ZERO;
        }
        if (scale < 0) {
            scale = 0;
        }
        if (roundingMode == null) {
            roundingMode = RoundingMode.HALF_UP;
        }
        return n.setScale(scale, roundingMode);
    }

    /**
     * 精度设置
     *
     * @param n
     * @param scale
     * @return
     */
    public static BigDecimal round(String n, int scale) {
        return round(n, scale, RoundingMode.HALF_UP);
    }

    /**
     * 精度设置
     *
     * @param n
     * @param scale
     * @return
     */
    public static BigDecimal round(double n, int scale) {
        return round(n, scale, RoundingMode.HALF_UP);
    }

    /**
     * 精度设置
     *
     * @param n
     * @param scale
     * @return
     */
    public static BigDecimal round(BigDecimal n, int scale) {
        return round(n, scale, RoundingMode.HALF_UP);
    }

    /**
     * 精度设置
     *
     * @param n
     * @param scale
     * @return
     */
    public static String roundAsStr(double n, int scale) {
        return round(n, scale).toString();
    }

    /**
     * 精度设置
     *
     * @param n
     * @param scale
     * @return
     */
    public static String roundAsStr(String n, int scale) {
        return round(n, scale).toString();
    }

    /**
     * 精度设置
     *
     * @param n
     * @param scale
     * @param roundingMode
     * @return
     */
    public static String roundAsStr(double n, int scale, RoundingMode roundingMode) {
        return round(n, scale, roundingMode).toString();
    }

    /**
     * 精度设置
     *
     * @param n
     * @param scale
     * @param roundingMode
     * @return
     */
    public static String roundAsStr(String n, int scale, RoundingMode roundingMode) {
        return round(n, scale, roundingMode).toString();
    }


}
