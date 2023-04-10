package cn.chenzw.toolkit.core.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.Consumer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 正则匹配工具
 *
 * @author chenzw
 */
public abstract class RegexKit {

    private RegexKit() {
    }


    /**
     * 内容是否匹配正则
     *
     * <pre>
     * RegexKit.isMatches(Pattern.compile("\\d+"), "999") = true
     * RegexKit.isMatches(Pattern.compile("\\d+"), "a999") = false
     * </pre>
     *
     * @param pattern
     * @param content
     * @return
     */
    public static final boolean isMatches(Pattern pattern, String content) {
        if (pattern == null || content == null) {
            return false;
        }
        return pattern.matcher(content).matches();
    }

    /**
     * 内容是否能在正则中匹配到
     *
     * <pre>
     * RegexKit.contains(Pattern.compile("\\d+"), "ab9") = true
     * RegexKit.contains(Pattern.compile("\\d+"), "abc") = false
     * </pre>
     *
     * @param pattern
     * @param content
     * @return
     */
    public static final boolean contains(Pattern pattern, CharSequence content) {
        if (pattern == null || content == null) {
            return false;
        }
        return pattern.matcher(content).find();
    }


    /**
     * 内容在正则中匹配到的次数
     *
     * <pre>
     * RegexKit.count(Pattern.compile("\\d"), "abc999") = 3
     * </pre>
     *
     * @param pattern
     * @param content
     * @return
     */
    public static final int count(Pattern pattern, CharSequence content) {
        if (pattern == null || content == null) {
            return 0;
        }
        int count = 0;
        final Matcher matcher = pattern.matcher(content);
        while (matcher.find()) {
            count++;
        }
        return count;
    }


    /**
     * 获取指定分组匹配的所有结果
     * <pre>
     * RegexKit.getGroups(Pattern.compile("(\\d+)"), "abc123cde345", 1) = ["123", "345"]
     * </pre>
     * @param pattern
     * @param content
     * @param groupIndex
     * @return
     */
    public static final List<String> getGroups(Pattern pattern, CharSequence content, int groupIndex) {
        if (pattern == null || content == null) {
            return null;
        }
        List<String> result = new ArrayList<>();
        findAll(pattern, content, (matcher) -> result.add(matcher.group(groupIndex)));
        return result;
    }

    /**
     * 获取指定分组匹配的所有结果
     *
     * @param pattern
     * @param content
     * @param groupName
     * @return
     */
    public static final List<String> getGroups(Pattern pattern, CharSequence content, String groupName) {
        if (pattern == null || content == null) {
            return null;
        }
        List<String> result = new ArrayList<>();
        findAll(pattern, content, (matcher) -> result.add(matcher.group(groupName)));
        return result;
    }

    /**
     * 匹配内容并回调结果
     *
     * @param pattern
     * @param content
     * @param callback
     */
    public static final void findAll(Pattern pattern, CharSequence content, Consumer<Matcher> callback) {
        if (pattern == null || content == null) {
            return;
        }
        final Matcher matcher = pattern.matcher(content);
        while (matcher.find()) {
            callback.accept(matcher);
        }
    }

    /**
     * 获取分组0的匹配结果
     *
     * @param pattern
     * @param content
     * @return
     */
    public static final List<String> getGroups0(Pattern pattern, CharSequence content) {
        return getGroups(pattern, content, 0);
    }

    /**
     * 获取分组1的匹配结果
     *
     * @param pattern
     * @param content
     * @return
     */
    public static final List<String> getGroup1(Pattern pattern, CharSequence content) {
        return getGroups(pattern, content, 1);
    }

}
