package cn.chenzw.toolkit.commons;

import org.apache.commons.lang3.ObjectUtils;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 简易字符模版类
 *
 * @author chenzw
 * @since 1.0.3
 */
public final class StringTemplateUtils {

    private static Pattern pattern = Pattern.compile("\\$\\{\\w+\\}");


    public static final String processTemplate(String template, Map<String, Object> args) {
        StringBuffer result = new StringBuffer();
        Matcher matcher = pattern.matcher(template);

        while (matcher.find()) {
            String param = matcher.group();
            Object value = args.get(param.substring(2, param.length() - 1));
            matcher.appendReplacement(result, ObjectUtils.defaultIfNull(value, "").toString());
        }
        matcher.appendTail(result);
        return result.toString();
    }


}
