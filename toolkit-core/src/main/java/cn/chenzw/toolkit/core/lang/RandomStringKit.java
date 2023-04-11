package cn.chenzw.toolkit.core.lang;

import cn.chenzw.toolkit.core.constants.CharacterConst;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;

/**
 * org.apache.commons.lang3.RandomStringUtils 扩展类
 *
 * @author chenzw
 * @since 1.0.3
 */
public abstract class RandomStringKit {

    private RandomStringKit() {
    }

    /**
     * 随机生成count个常用中文字符
     *
     * @param count
     * @return
     */
    public static String randomFrequentlyUsedChinese(final int count) {
        int len = CharacterConst.FREQUENTLY_USED_CHINESE.length();
        StringBuilder buffer = new StringBuilder();
        for (int i = 0; i < count; i++) {
            buffer.append(
                    CharacterConst.FREQUENTLY_USED_CHINESE.charAt(RandomUtils.nextInt(0, len))
            );
        }
        return buffer.toString();
    }

    /**
     * 随机生成minLengthInclusive至maxLengthExclusive个常用中文字符
     *
     * @param minLengthInclusive
     * @param maxLengthExclusive
     * @return
     */
    public static String randomFrequentlyUsedChinese(final int minLengthInclusive, final int maxLengthExclusive) {
        return randomFrequentlyUsedChinese(RandomUtils.nextInt(minLengthInclusive, maxLengthExclusive));
    }

    /**
     * 随机生成count个中文字符
     *
     * @param count
     * @return
     */
    public static String randomChinese(final int count) {
        return RandomStringUtils.random(count, 0x4e00, 0x9fa5, false, false);
    }

    /**
     * 随机生成minLengthInclusive至maxLengthExclusive个中文字符
     *
     * @param minLengthInclusive
     * @param maxLengthExclusive
     * @return
     */
    public static String randomChinese(final int minLengthInclusive, final int maxLengthExclusive) {
        return randomChinese(RandomUtils.nextInt(minLengthInclusive, maxLengthExclusive));
    }

    /**
     * 随机生成姓名
     *
     * @return
     */
    public static String randomName() {
        // 随机姓氏
        String surName = CharacterConst.SURNAME[RandomUtils.nextInt(0, CharacterConst.SURNAME.length)];
        // 随机名
        String name = randomFrequentlyUsedChinese(RandomUtils.nextInt(1, 3));
        return surName + name;
    }

    /**
     * 从给定的列表项中随机取值
     *
     * @param list
     * @param <T>
     * @return
     */
    public static <T> T randomFromList(T... list) {
        if (list.length == 0) {
            throw new IllegalArgumentException("Parameter list is empty!");
        }
        int i = RandomUtils.nextInt(0, list.length);
        return list[i];
    }

}
