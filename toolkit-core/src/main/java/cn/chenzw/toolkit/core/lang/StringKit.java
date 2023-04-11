package cn.chenzw.toolkit.core.lang;

import cn.chenzw.toolkit.core.collection.ArrayKit;
import cn.chenzw.toolkit.core.util.RegexKit;
import cn.chenzw.toolkit.core.util.Validator;
import org.apache.commons.lang3.StringUtils;

/**
 * @author chenzw
 * @see {@link StringUtils}
 */
public abstract class StringKit {

    private static final int INDEX_NOT_FOUND = -1;

    private StringKit() {
    }

    /**
     * 将大写字符转小写，并添加分隔符
     *
     * <pre>
     * StringKit.uppercaseSeparate("HelloWorld", "-") = "hello-world"
     * StringExtUtils.uppercaseSeparate("helloWorld", "@") = "hello@world"
     * </pre>
     *
     * @param name      字符串
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
     * 将驼峰式命名的字符串转换为下划线格式 - 如果转换前的驼峰式命名的字符串为空，则返回空字符串。
     * <pre>
     * StringKit.toUnderscoreCase("HelloWorld") = "hello_world"
     * StringKit.toUnderscoreCase("helloWorld") = "hello_world"
     * StringKit.toUnderscoreCase("Hello2World") = "hello2_world"
     * StringKit.toUnderscoreCase("HElloWOrld") = "h_ello_w_orld"
     * </pre>
     *
     * @param name 转换前的驼峰式命名的字符串
     * @return 转换后下划线格式命名的字符串
     */
    public static String toUnderscoreCase(String name) {
        return uppercaseSeparate(name, "_");
    }


    /**
     * 将下划线命名的字符串转换为驼峰格式 - 如果转换前的下划线大写方式命名的字符串为空，则返回空字符串。
     *
     * <pre>
     * StringKit.toCamelCase("hello_world", "_", true) = "helloWorld"
     * StringKit.toCamelCase("Hello_world", "_", false) = "HelloWorld"
     * StringKit.toCamelCase("HELLO_WORLD", "_", true) = "helloWorld"
     * StringKit.toCamelCase("hello$$world", "$$", true) = "helloWorld"
     * </pre>
     *
     * @param name                   字符串
     * @param separator              分隔符
     * @param firstLetterCapitalized 首字母是否大写
     * @return 转换后的驼峰式命名的字符串
     */
    public static String toCamelCase(String name, String separator, boolean firstLetterCapitalized) {
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
     * <pre>
     * StringKit.toCamelCase("hello_world") = "helloWorld"
     * StringKit.toCamelCase("HELLO_WORLD") = "helloWorld"
     * StringKit.toCamelCase("Hello_WoRld") = "helloWorld"
     * </pre>
     *
     * @param name
     * @return
     */
    public static String toCamelCase(String name) {
        return toCamelCase(name, "_", false);
    }


    /**
     * 转帕斯卡命名格式
     *
     * <pre>
     * StringKit.toPascalCase("hello_world", "_") = HelloWorld
     * StringKit.toPascalCase("HELLO_WORLD", "_") = HelloWorld
     * StringKit.toPascalCase("Hello_WoRld", "_") = HelloWorld
     * </pre>
     *
     * @param name      字符串
     * @param separator 分隔符
     * @return 帕斯卡格式字符串
     */
    public static String toPascalCase(String name, String separator) {
        String camelName = toCamelCase(name, separator, true);
        if (StringUtils.isEmpty(camelName)) {
            return "";
        }
        return camelName.substring(0, 1).toUpperCase() + camelName.substring(1);
    }

    /**
     * 将下划线命名的字符串转换为帕斯卡格式
     *
     * <pre>
     * StringKit.toPascalCase("hello_world") = HelloWorld
     * StringKit.toPascalCase("HELLO_WORLD") = HelloWorld
     * StringKit.toPascalCase("Hello_WoRld") = HelloWorld
     * </pre>
     *
     * @param name 下划线命名字符串
     * @return 帕斯卡格式字符串
     */
    public static String toPascalCase(String name) {
        return toPascalCase(name, "_");
    }


    /**
     * 获取第一个分离点之后的文本（不存在分离点时返回整个字符串）
     *
     * <pre>
     * StringKit.subStringAfterFirst("abcdef", "c") = def
     * StringKit.subStringAfterFirst("abcdef", "g") = abcdef
     * StringKit.subStringAfterFirst("abcdefabcdef", "c") = defabcdef
     * </pre>
     *
     * @param str       字符串
     * @param separator 分隔字符串
     * @return 第一个分隔后的所有字符串
     */
    public static String subStringAfterFirst(final String str, final String separator) {
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
     * StringExtUtils.subStringBeforeFirst("abcdef", "c") = ab
     * StringExtUtils.subStringBeforeFirst("abcdef", "g") = ""
     * StringExtUtils.subStringBeforeFirst("abcdefabcdef", "c") = ab
     * </pre>
     *
     * @param str
     * @param separator
     * @return
     */
    public static String subStringBeforeFirst(final String str, final String separator) {
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
     * <pre>
     * StringKit.toInteger("10", 1) = 10
     *
     * // 空值返回默认值
     * StringKit.toInteger("", 1) = 1
     * StringKit.toInteger(null, 1) = 1
     *
     * </pre>
     *
     * @param str          字符串
     * @param defaultValue 字符串为空时返回的默认值
     * @return 数字
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
     * <pre>
     * StringKit.toInteger("99") = 99
     * // 空值返回null
     * StringKit.toInteger(null) = null
     * StringKit.toInteger("") = null
     * </pre>
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
     * <pre>
     * StringKit.countAsciiAlphas("我是xXsdAx数据库Y的") = 7
     * StringKit.countAsciiAlphas("我是123数据库23的") = 0
     * </pre>
     *
     * @param str 字符串
     * @return 包含的字母个数
     */
    public static int countAsciiAlphas(String str) {
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
     * <pre>
     * StringKit.countCapitalizedAsciiAlphas("我是xXsdAx数据库Y的") = 3
     * </pre>
     *
     * @param str 字符串
     * @return 包含的大写字母个数
     */
    public static int countCapitalizedAsciiAlphas(String str) {
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
     * <pre>
     * StringKit.countLowercaseAsciiAlphas("我是xXsdAx数据库Y的") = 4
     * </pre>
     *
     * @param str 字符串
     * @return 包含的小写字母个数
     */
    public static int countLowercaseAsciiAlphas(String str) {
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
     * <pre>
     * StringKit.countDigits("你好，9547") = 4
     * </pre>
     *
     * @return 包含的数字个数
     */
    public static int countDigits(String str) {
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
     * <pre>
     * StringKit.containsAsciiAlpha("你好，9547abc") = true
     * </pre>
     */
    public static boolean containsAsciiAlpha(String str) {
        return countAsciiAlphas(str) > 0;
    }


    /**
     * 是否包含大写字母
     * <pre>
     * StringKit.containsCapitalizedAsciiAlpha("你好，9547abc") = false
     * StringKit.containsCapitalizedAsciiAlpha("你好，9547ABC") = true
     * </pre>
     *
     * @param str
     * @return
     */
    public static boolean containsCapitalizedAsciiAlpha(String str) {
        return countCapitalizedAsciiAlphas(str) > 0;
    }

    /**
     * 是否包含小写字母
     *
     * @param str
     * @return
     */
    public static boolean containsLowercaseAsciiAlphas(String str) {
        return countLowercaseAsciiAlphas(str) > 0;
    }


    /**
     * 是否包含数字
     *
     * @param str
     * @return
     */
    public static boolean containsDigit(String str) {
        return countDigits(str) > 0;
    }

    /**
     * 文本拆分并去除前后空格
     * <pre>
     * StringKit.splitTrim(" aa , bb,cc", ",") = ["aa", "bb", "cc"]
     * </pre>
     *
     * @param str            字符串
     * @param separatorChars 分隔字符
     * @return
     */
    public static String[] splitTrim(final String str, final String separatorChars) {
        return ArrayKit.trim(StringUtils.split(str, separatorChars));
    }


    /**
     * 统计中文字符数量
     *
     * @param str
     * @return
     */
    public static int countChineseCharacters(String str) {
        return RegexKit.count(Validator.CHINESE_PATTERN, str);
    }

    /**
     * 是否包含中文字符
     *
     * @param str
     * @return
     */
    public static boolean containsChineseChars(String str) {
        return RegexKit.contains(Validator.CHINESE_PATTERN, str);
    }


}
