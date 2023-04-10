package cn.chenzw.toolkit.core.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author chenzw
 */
public class Validator {

    public static final Pattern IPV4_PATTERN = Pattern.compile("\\b(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\b");

    public static final Pattern IPV6_PATTERN = Pattern.compile("(([0-9a-fA-F]{1,4}:){7,7}[0-9a-fA-F]{1,4}|([0-9a-fA-F]{1,4}:){1,7}:|([0-9a-fA-F]{1,4}:){1,6}:[0-9a-fA-F]{1,4}|([0-9a-fA-F]{1,4}:){1,5}(:[0-9a-fA-F]{1,4}){1,2}|([0-9a-fA-F]{1,4}:){1,4}(:[0-9a-fA-F]{1,4}){1,3}|([0-9a-fA-F]{1,4}:){1,3}(:[0-9a-fA-F]{1,4}){1,4}|([0-9a-fA-F]{1,4}:){1,2}(:[0-9a-fA-F]{1,4}){1,5}|[0-9a-fA-F]{1,4}:((:[0-9a-fA-F]{1,4}){1,6})|:((:[0-9a-fA-F]{1,4}){1,7}|:)|fe80:(:[0-9a-fA-F]{0,4}){0,4}%[0-9a-zA-Z]{1,}|::(ffff(:0{1,4}){0,1}:){0,1}((25[0-5]|(2[0-4]|1{0,1}[0-9]){0,1}[0-9])\\.){3,3}(25[0-5]|(2[0-4]|1{0,1}[0-9]){0,1}[0-9])|([0-9a-fA-F]{1,4}:){1,4}:((25[0-5]|(2[0-4]|1{0,1}[0-9]){0,1}[0-9])\\.){3,3}(25[0-5]|(2[0-4]|1{0,1}[0-9]){0,1}[0-9]))");

    public static final Pattern EMAIL_LOOSE_PATTERN = Pattern.compile("^\\w+@(\\w+\\.){1,2}\\w+$");

    /**
     * 符合RFC 5322规范，正则来自：http://emailregex.com/
     */
    public static final Pattern EMAIL_PATTERN = Pattern.compile("(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)])");

    public static final Pattern QQ_PATTERN = Pattern.compile("^[1-9][0-9]{4,}$");

    public static final Pattern ID_CARD_CN_15_PATTERN = Pattern.compile("^[1-9]\\d{7}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])\\d{3}$");

    public static final Pattern ID_CARD_CN_18_PATTERN = Pattern.compile("^[1-9]\\d{5}[1-9]\\d{3}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])\\d{3}([0-9]|X)$");

    public static final Pattern CHINESE_PATTERN = Pattern.compile("[\\u4e00-\\u9fa5]");

    public static final Pattern MOBILE_CN_PATTERN = Pattern.compile("^(13[0-9]|14[579]|15([0-5]|[6-9])|16[6]|17([013]|[5-8])|18([0-3]|[5-9])|19[89])\\d{8}$");

    public static final Pattern MOBILE_CN_LOOSE_PATTERN = Pattern.compile("(?:0|86|\\+86)?1[3456789]\\d{9}");

    public static final Pattern SPECIAL_CHARACTER_PATTERN = Pattern.compile("[ _`~!@#$%^&*()+\\-=|{}':;',\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]|\n|\r|\t");


    /**
     * 是否IPv4地址
     *
     * @param content
     * @return
     */
    public static boolean isIPv4(String content) {
        return RegexKit.isMatches(IPV4_PATTERN, content);
    }

    /**
     * 是否IPv6地址
     *
     * @param content
     * @return
     */
    public static boolean isIPv6(String content) {
        return RegexKit.isMatches(IPV6_PATTERN, content);
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
     * 是否邮箱地址
     *
     * @param content
     * @return
     */
    public static final boolean isEmail(String content) {
        return RegexKit.isMatches(EMAIL_PATTERN, content);
    }

    /**
     * 是否QQ号码
     *
     * @param content
     * @return
     */
    public static boolean isQQ(String content) {
        // 从10000开始
        return RegexKit.isMatches(QQ_PATTERN, content);
    }

    /**
     * 是否中国身份证号（宽松模式）
     *
     * @param content
     * @return
     */
    public static boolean isChineseIdCard(String content) {
        if (content == null) {
            throw new IllegalArgumentException("Parameter \"content\" must be not null");
        }
        if (content.length() == 15) {
            return RegexKit.isMatches(ID_CARD_CN_15_PATTERN, content);
        }
        if (content.length() == 18) {
            return RegexKit.isMatches(ID_CARD_CN_18_PATTERN, content);
        }
        return false;
    }

    /**
     * 是否中文字符
     *
     * @param content
     * @return
     */
    public static boolean isChinese(String content) {
        return RegexKit.isMatches(CHINESE_PATTERN, content);
        // return Pattern.matches("^[\\u4e00-\\u9fa5]{0,}$", content);
    }

    /**
     * 是否中国手机号
     * <br>
     * <b>支持13、14、15、16、17、18、19号段</b>
     *
     * @param content
     * @return
     */
    public static boolean isChinesePhoneNO(String content) {
        return RegexKit.isMatches(MOBILE_CN_PATTERN, content);
    }

    /**
     * 是否中国手机号(宽松)
     *
     * @param content
     * @return
     */
    public static boolean isChinesePhoneNOLoose(String content) {
        return RegexKit.isMatches(MOBILE_CN_LOOSE_PATTERN, content);
    }

    /**
     * 包含特殊字符的个数
     *
     * @param content
     * @return
     */
    public static int countSpecialCharacters(String content) {
        return RegexKit.count(SPECIAL_CHARACTER_PATTERN, content);
    }

    /**
     * 是否包含特殊字符
     *
     * @param str
     * @return
     */
    public static boolean containsSpecialCharacter(String str) {
        return countSpecialCharacters(str) > 0;
    }


    // @TODO ip带子网掩码 - netmask
    // @TODO URL
}
