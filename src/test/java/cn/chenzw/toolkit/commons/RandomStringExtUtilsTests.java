package cn.chenzw.toolkit.commons;

import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@Slf4j
@RunWith(JUnit4.class)
public class RandomStringExtUtilsTests {

    @Test
    public void testRandomFrequentlyUsedChinese() {
        for (int i = 0; i < 3; i++) {
            String chineseCharacters = RandomStringExtUtils.randomFrequentlyUsedChinese(5);
            Assert.assertEquals(5, chineseCharacters.length());

            log.info("随机生成5个常用中文字符 => {}", chineseCharacters);
        }
    }

    @Test
    public void testRandomFrequentlyUsedChinese2() {
        for (int i = 0; i < 3; i++) {
            String chineseCharacters = RandomStringExtUtils.randomFrequentlyUsedChinese(3, 5);
            Assert.assertTrue(chineseCharacters.length() >= 3 && chineseCharacters.length() < 5);

            log.info("随机生成3~5个常用中文字符 => {}", chineseCharacters);
        }
    }

    @Test
    public void testRandomChinese() {
        for (int i = 0; i < 3; i++) {
            String chineseCharacters = RandomStringExtUtils.randomChinese(5);
            Assert.assertEquals(5, chineseCharacters.length());

            log.info("随机生成5个中文字符 => {}", chineseCharacters);
        }
    }

    @Test
    public void testRandomChinese2() {
        for (int i = 0; i < 3; i++) {
            String chineseCharacters = RandomStringExtUtils.randomChinese(3, 5);
            Assert.assertTrue(chineseCharacters.length() >= 3 && chineseCharacters.length() < 5);

            log.info("随机生成3~5个中文字符 => {}", chineseCharacters);
        }
    }

    @Test
    public void testRandomName() {
        for (int i = 0; i < 3; i++) {
            String name = RandomStringExtUtils.randomName();
            Assert.assertNotNull(name);

            log.info("随机生成姓名 => {}", name);
        }

    }

    @Test
    public void testRandomFromList() {
        log.info("列表值: 张三, 李四, 王五, 赵六");

        for (int i = 0; i < 3; i++) {
            String name = RandomStringExtUtils.randomFromList("张三", "李四", "王五", "赵六");
            log.info("随机从列表中取值 => {}", name);
        }
    }
}
