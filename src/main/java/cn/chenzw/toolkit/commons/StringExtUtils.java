package cn.chenzw.toolkit.commons;

import org.apache.commons.lang3.StringUtils;

/**
 * @author chenzw
 * @see {@link org.apache.commons.lang3.StringUtils}
 */
public abstract class StringExtUtils {

    public static final int INDEX_NOT_FOUND = -1;

    private StringExtUtils() {
    }

    /**
     * 分隔大写字符
     *
     * @param name
     * @param separator
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
     * @param name 转换前的下划线命名的字符串
     * @return 转换后的驼峰式命名的字符串
     */
    public static String toCamel(String name, String separator, boolean firstLetterCapitalized) {
        if (name == null || name.length() == 0) {
            return name;
        } else if (!name.contains(separator)) {
            if (firstLetterCapitalized) {
                // 不含下划线，则仅将首字母小写
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

    /**
     * 获取第一个分离点之后的文本
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
}
