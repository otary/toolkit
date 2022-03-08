package cn.chenzw.toolkit.commons;

import org.apache.commons.lang3.StringUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author chenzw
 * @see {@link org.apache.commons.lang3.StringUtils}
 */
public abstract class StringExtUtils {

    private static final int INDEX_NOT_FOUND = -1;

    private StringExtUtils() {
    }

    /**
     * 分隔大写字符
     *
     * @param name
     * @param separator 分隔符
     * @return
     */
    public static String uppercaseSeparate(String name, String separator) {
        // 空字符直接返回
        if (name == null || name.length() == 0) {
            return name;
        }
        StringBuilder result = new StringBuilder();
        result.append(Character.toLowerCase(name.charAt(0)));
        // 循环处理其余字符
        for (int i = 1; i < name.length(); i++) {
            char c = name.charAt(i);
            // 在大写字母前添加下划线
            if (Character.isUpperCase(c)) {
                result.append(separator);
            }
            // 其他字符直接转成大写
            result.append(Character.toLowerCase(c));
        }
        return result.toString();
    }


    /**
     * 将驼峰式命名的字符串转换为下划线格式 - 如果转换前的驼峰式命名的字符串为空，则返回空字符串。</br>
     * <pre>eg. HelloWorld -> hello_word</pre>
     *
     * @param name 转换前的驼峰式命名的字符串
     * @return 转换后下划线格式命名的字符串
     */
    public static String toUnderscore(String name) {
        return uppercaseSeparate(name, "_");
    }


    /**
     * 将下划线命名的字符串转换为驼峰格式 - 如果转换前的下划线大写方式命名的字符串为空，则返回空字符串。</br>
     * <pre>eg. HELLO_WORLD->HelloWorld</pre>
     *
     * @param name                   转换前的下划线命名的字符串
     * @param separator              分隔符
     * @param firstLetterCapitalized 首字母是否大写
     * @return 转换后的驼峰式命名的字符串
     */
    public static String toCamel(String name, String separator, boolean firstLetterCapitalized) {
        if (name == null || name.length() == 0) {
            return name;
        } else if (!name.contains(separator)) {
            if (firstLetterCapitalized) {
                // 不含下划线，则仅将首字母大写
                return name.substring(0, 1).toUpperCase() + name.substring(1);
            }
            return name;
        }

        StringBuilder result = new StringBuilder();
        // 用下划线将原始字符串分割
        String[] camels = name.split(separator);
        for (String camel : camels) {
            // 跳过原始字符串中开头、结尾的下换线或双重下划线
            if (camel.isEmpty()) {
                continue;
            }
            // 处理真正的驼峰片段
            if (result.length() == 0) {
                // 第一个驼峰片段，全部字母都小写
                result.append(camel.toLowerCase());
            } else {
                // 其他的驼峰片段，首字母大写
                result.append(camel.substring(0, 1).toUpperCase());
                result.append(camel.substring(1).toLowerCase());
            }
        }
        return result.toString();
    }

    /**
     * 将下划线命名的字符串转换为驼峰格式
     *
     * @param name
     * @return
     */
    public static String toCamel(String name) {
        return toCamel(name, "_", false);
    }


    public static String toPascal(String name, String separator) {
        String camelName = toCamel(name, separator, true);
        if (StringUtils.isEmpty(camelName)) {
            return "";
        }
        return camelName.substring(0, 1).toUpperCase() + camelName.substring(1);
    }

    /**
     * 将下划线命名的字符串转换为帕斯卡格式
     *
     * @param name
     * @return
     */
    public static String toPascal(String name) {
        return toPascal(name, "_");
    }


    /**
     * 获取第一个分离点之后的文本（不存在分离点时返回整个字符串）
     *
     * <pre>
     *     StringExtUtils.subStringFirstAfter("abcdef", "c") = def
     *     StringExtUtils.subStringFirstAfter("abcdef", "g") = abcdef
     *     StringExtUtils.subStringFirstAfter("abcdefabcdef", "c") = defabcdef
     * </pre>
     *
     * @param str
     * @param separator
     * @return
     */
    public static String subStringFirstAfter(final String str, final String separator) {
        if (StringUtils.isEmpty(str)) {
            return str;
        }
        if (separator == null) {
            return "";
        }

        final int pos = str.indexOf(separator);
        if (pos == INDEX_NOT_FOUND) {
            return str;
        }
        return str.substring(pos + separator.length());
    }

    /**
     * 获取第一个分离点之前的文本（不存在分离点时返回空）
     *
     * <pre>
     *    StringExtUtils.subStringFirstBefore("abcdef", "c") = ab
     *    StringExtUtils.subStringFirstBefore("abcdef", "g") = ""
     *    StringExtUtils.subStringFirstBefore("abcdefabcdef", "c") = ab
     * </pre>
     *
     * @param str
     * @param separator
     * @return
     */
    public static String subStringFirstBefore(final String str, final String separator) {
        if (StringUtils.isEmpty(str)) {
            return str;
        }
        if (separator == null) {
            return "";
        }

        final int pos = str.indexOf(separator);
        if (pos == INDEX_NOT_FOUND) {
            return "";
        }
        return str.substring(0, pos);
    }


    /**
     * String => Integer
     *
     * @param str
     * @param defaultValue 字符串为空时返回的默认值
     * @return
     */
    public static Integer toInteger(final String str, final Integer defaultValue) {
        if (StringUtils.isEmpty(StringUtils.trim(str))) {
            return defaultValue;
        }

        if (StringUtils.contains(str, ".")) {
            return Double.valueOf(str).intValue();
        }
        return Integer.valueOf(str);
    }

    /**
     * String => Integer
     *
     * @param str
     * @return
     */
    public static Integer toInteger(final String str) {
        return toInteger(str, null);
    }

    /**
     * String => Long
     *
     * @param str
     * @param defaultValue
     * @return
     * @deprecated {@link org.apache.commons.lang3.math.NumberUtils#toLong(String, long)}
     */
    public static Long toLong(final String str, final Long defaultValue) {
        if (StringUtils.isEmpty(StringUtils.trim(str))) {
            return defaultValue;
        }
        return Long.valueOf(str);
    }

    /**
     * String => Long
     *
     * @param str
     * @return
     * @deprecated {@link org.apache.commons.lang3.math.NumberUtils#toLong(String)}
     */
    public static Long toLong(final String str) {
        return toLong(str, null);
    }


    /**
     * String => Float
     *
     * @param str
     * @param defaultValue
     * @return
     * @deprecated {@link org.apache.commons.lang3.math.NumberUtils#toFloat(String, float)}
     */
    public static Float toFloat(final String str, final Float defaultValue) {
        if (StringUtils.isEmpty(StringUtils.trim(str))) {
            return defaultValue;
        }
        return Float.valueOf(str);
    }

    /**
     * String => Float
     *
     * @param str
     * @return
     * @deprecated {@link org.apache.commons.lang3.math.NumberUtils#toFloat(String)}
     */
    public static Float toFloat(final String str) {
        return toFloat(str, null);
    }

    /**
     * String => Double
     *
     * @param str
     * @param defaultValue
     * @return
     * @deprecated {@link org.apache.commons.lang3.math.NumberUtils#toDouble(String, double)}
     */
    public static Double toDouble(final String str, final Double defaultValue) {
        if (StringUtils.isEmpty(StringUtils.trim(str))) {
            return defaultValue;
        }
        return Double.valueOf(str);
    }

    /**
     * String => Double
     *
     * @param str
     * @return
     * @deprecated {@link org.apache.commons.lang3.math.NumberUtils#toDouble(String)}
     */
    public static Double toDouble(final String str) {
        return toDouble(str, null);
    }


    /**
     * 获取字符串中包含的字母个数
     *
     * @return
     */
    public static int hasAsciiAlphas(String str) {
        int count = 0;
        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            if (ch >= 'A' && ch <= 'Z' || ch >= 'a' && ch <= 'z') {
                count++;
            }
        }
        return count;
    }

    /**
     * 获取字符串中包含的大写字母个数
     *
     * @return
     */
    public static int hasCapitalizedAsciiAlphas(String str) {
        int count = 0;
        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            if (ch >= 'A' && ch <= 'Z') {
                count++;
            }
        }
        return count;
    }

    /**
     * 获取字符串中包含的小写字母的个数
     *
     * @param str
     * @return
     */
    public static int hasLowercaseAsciiAlphas(String str) {
        int count = 0;
        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            if (ch >= 'a' && ch <= 'z') {
                count++;
            }
        }
        return count;
    }


    /**
     * 获取字符串中包含的数字个数
     *
     * @return
     */
    public static int hasDigit(String str) {
        int count = 0;
        for (int i = 0; i < str.length(); i++) {
            if (Character.isDigit(str.charAt(i))) {
                count++;
            }
        }
        return count;
    }


    /**
     * 是否包含字母
     *
     * @return
     */
    public static boolean containsAsciiAlpha(String str) {
        return StringExtUtils.hasAsciiAlphas(str) > 0;
    }


    /**
     * 是否包含大写字母
     *
     * @param str
     * @return
     */
    public static boolean containsCapitalizedAsciiAlpha(String str) {
        return StringExtUtils.hasCapitalizedAsciiAlphas(str) > 0;
    }

    /**
     * 是否包含小写字母
     *
     * @param str
     * @return
     */
    public static boolean containsLowercaseAsciiAlphas(String str) {
        return StringExtUtils.hasLowercaseAsciiAlphas(str) > 0;
    }


    /**
     * 是否包含数字
     *
     * @param str
     * @return
     */
    public static boolean containsDigit(String str) {
        return StringExtUtils.hasDigit(str) > 0;
    }

    /**
     * 文本拆分并去除前后空格
     *
     * @param str
     * @param separatorChars
     * @return
     */
    public static String[] splitTrim(final String str, final String separatorChars) {
        return ArrayExtUtils.trim(StringUtils.split(str, separatorChars));
    }

    /**
     * 统计中文字符数量
     * @param str
     * @return
     */
    public static int countChineseCharacters(String str) {
        Matcher matcher = RegexUtils.CHINESE_PATTERN.matcher(str);
        int count = 0;
        while (matcher.find()) {
            count++;
        }
        return count;
    }
}
