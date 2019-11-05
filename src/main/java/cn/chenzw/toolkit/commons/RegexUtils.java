package cn.chenzw.toolkit.commons;

import org.apache.commons.lang3.StringUtils;

import java.util.regex.Pattern;

/**
 * 正则匹配工具
 *
 * @author chenzw
 */
public abstract class RegexUtils {

    private static final Pattern CHINESE_PATTERN = Pattern.compile("[\\u4e00-\\u9fa5]");

    private RegexUtils() {
    }

    /**
     * 是否IPv4地址
     *
     * @param ipv4
     * @return
     */
    public static boolean isIPv4(String ipv4) {
        return Pattern.matches(
                "\\b(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\b",
                ipv4);
    }

    /**
     * 是否IPv6地址
     *
     * @param ipv6
     * @return
     */
    public static boolean isIPv6(String ipv6) {
        return Pattern.matches(
                "(([0-9a-fA-F]{1,4}:){7,7}[0-9a-fA-F]{1,4}|([0-9a-fA-F]{1,4}:){1,7}:|([0-9a-fA-F]{1,4}:){1,6}:[0-9a-fA-F]{1,4}|([0-9a-fA-F]{1,4}:){1,5}(:[0-9a-fA-F]{1,4}){1,2}|([0-9a-fA-F]{1,4}:){1,4}(:[0-9a-fA-F]{1,4}){1,3}|([0-9a-fA-F]{1,4}:){1,3}(:[0-9a-fA-F]{1,4}){1,4}|([0-9a-fA-F]{1,4}:){1,2}(:[0-9a-fA-F]{1,4}){1,5}|[0-9a-fA-F]{1,4}:((:[0-9a-fA-F]{1,4}){1,6})|:((:[0-9a-fA-F]{1,4}){1,7}|:)|fe80:(:[0-9a-fA-F]{0,4}){0,4}%[0-9a-zA-Z]{1,}|::(ffff(:0{1,4}){0,1}:){0,1}((25[0-5]|(2[0-4]|1{0,1}[0-9]){0,1}[0-9])\\.){3,3}(25[0-5]|(2[0-4]|1{0,1}[0-9]){0,1}[0-9])|([0-9a-fA-F]{1,4}:){1,4}:((25[0-5]|(2[0-4]|1{0,1}[0-9]){0,1}[0-9])\\.){3,3}(25[0-5]|(2[0-4]|1{0,1}[0-9]){0,1}[0-9]))",
                ipv6);
    }

    /**
     * 是否IP地址(包含IPv4和IPv6)
     *
     * @param ip
     * @return
     */
    public static boolean isIp(String ip) {
        return (isIPv4(ip) || isIPv6(ip));
    }

    /**
     * 是否邮箱地址（宽松模式）
     *
     * @param email
     * @return
     */
    public static final boolean isEmail(String email) {
        return Pattern.matches("^\\w+@(\\w+\\.){1,2}\\w+$", email);
    }

    /**
     * 是否QQ号码
     *
     * @param qq
     * @return
     */
    public static boolean isQQ(String qq) {
        // 从10000开始
        return Pattern.matches("^[1-9][0-9]{4,}$", qq);
    }

    /**
     * 是否身份证号（宽松模式）
     *
     * @param idCard
     * @return
     */
    public static boolean isIdCard(String idCard) {
        if (StringUtils.isBlank(idCard)) {
            throw new IllegalArgumentException("Parameter \"idCard\" must be not null");
        }
        if (idCard.length() == 15) {
            return Pattern.matches("^[1-9]\\d{7}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])\\d{3}$", idCard);
        }

        if (idCard.length() == 18) {
            return Pattern
                    .matches("^[1-9]\\d{5}[1-9]\\d{3}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])\\d{3}([0-9]|X)$", idCard);
        }
        return false;
    }

    /**
     * 是否包含中文字符
     *
     * @return
     */
    public static boolean containsChinese(String chinese) {
        return CHINESE_PATTERN.matcher(chinese).find();
    }

    /**
     * 是否中文字符
     *
     * @param chinese
     * @return
     */
    public static boolean isChinese(String chinese) {
        return Pattern.matches("^[\\u4e00-\\u9fa5]{0,}$", chinese);
    }

    /**
     * 是否手机号
     * <br>
     * <b>支持13、14、15、16、17、18、19号段</b>
     *
     * @param phoneNo
     * @return
     */
    public static boolean isPhoneNO(String phoneNo) {
        return Pattern.matches("^(13[0-9]|14[579]|15([0-5]|[6-9])|16[6]|17([013]|[5-8])|18([0-3]|[5-9])|19[89])\\d{8}$",
                phoneNo);
    }

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


    // @TODO ip带子网掩码 - netmask
    // @TODO 电话好
    // @TODO URL
}
