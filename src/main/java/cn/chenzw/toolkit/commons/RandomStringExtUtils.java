package cn.chenzw.toolkit.commons;

import cn.chenzw.toolkit.constants.CharacterConstatns;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;

/**
 * RandomStringUtils 扩展类
 * @author chenzw
 */
public class RandomStringExtUtils {


    /**
     * 随机生成count个常用中文字符
     * @param count
     * @return
     */
    public static String randomFrequentlyUsedChinese(final int count) {
        int len = CharacterConstatns.FRQUENTLEY_USED_CHINESE.length();
        StringBuilder buf = new StringBuilder();
        for (int i = 0; i < count; i++) {
            buf.append(CharacterConstatns.FRQUENTLEY_USED_CHINESE.charAt(RandomUtils.nextInt(0, len)));
        }
        return buf.toString();
    }

    /**
     *  随机生成minLengthInclusive至maxLengthExclusive个常用中文字符
     * @param minLengthInclusive
     * @param maxLengthExclusive
     * @return
     */
    public static String randomFrequentlyUsedChinese(final int minLengthInclusive, final int maxLengthExclusive) {
        return randomFrequentlyUsedChinese(RandomUtils.nextInt(minLengthInclusive, maxLengthExclusive));
    }

    /**
     * 随机生成count个中文字符
     * @param count
     * @return
     */
    public static String randomChinese(final int count) {
        return RandomStringUtils.random(count, 0x4e00, 0x9fa5, false, false);
    }

    /**
     * 随机生成minLengthInclusive至maxLengthExclusive个中文字符
     * @param minLengthInclusive
     * @param maxLengthExclusive
     * @return
     */
    public static String randomChinese(final int minLengthInclusive, final int maxLengthExclusive) {
        return randomChinese(RandomUtils.nextInt(minLengthInclusive, maxLengthExclusive));
    }

}
