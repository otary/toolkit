package cn.chenzw.toolkit.commons;

import cn.chenzw.toolkit.commons.RandomStringExtUtils;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class RandomStringExtUtilsTests {

    @Test
    public void testRandomFrequentlyUsedChinese() {
        String chineseCharacters = RandomStringExtUtils.randomFrequentlyUsedChinese(5);
        Assert.assertEquals(chineseCharacters.length(), 5);

        System.out.println("随机生成5个常用中文字符:" + chineseCharacters);
    }

    @Test
    public void testRandomFrequentlyUsedChinese2() {
        String chineseCharacters = RandomStringExtUtils.randomFrequentlyUsedChinese(3, 5);
        Assert.assertTrue(chineseCharacters.length() > 3 && chineseCharacters.length() < 5);

        System.out.println("随机生成3~5个常用中文字符:" + chineseCharacters);
    }

    @Test
    public void testRandomChinese() {
        String chineseCharacters = RandomStringExtUtils.randomChinese(5);
        Assert.assertEquals(chineseCharacters.length(), 5);

        System.out.println("随机生成5个中文字符:" + chineseCharacters);
    }

    @Test
    public void testRandomChinese2() {
        String chineseCharacters = RandomStringExtUtils.randomChinese(3, 5);
        Assert.assertTrue(chineseCharacters.length() > 3 && chineseCharacters.length() < 5);

        System.out.println("随机生成3~5个中文字符:" + chineseCharacters);
    }

}
