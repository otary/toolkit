package cn.chenzw.toolkit.core.lang;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class StringKitTests {

    @Test
    public void testToUnderscore() {
        Assert.assertEquals("hello_world", StringKit.toUnderscoreCase("HelloWorld"));
        Assert.assertEquals("hello_world", StringKit.toUnderscoreCase("helloWorld"));
        Assert.assertEquals("hello2_world", StringKit.toUnderscoreCase("Hello2World"));
        Assert.assertEquals("h_ello_w_orld", StringKit.toUnderscoreCase("HElloWOrld"));
    }

    @Test
    public void testToCamel() {
        Assert.assertEquals("helloWorld", StringKit.toCamelCase("hello_world"));
        Assert.assertEquals("helloWorld", StringKit.toCamelCase("HELLO_WORLD"));
        Assert.assertEquals("helloWorld", StringKit.toCamelCase("Hello_WoRld"));
    }

    @Test
    public void testToPascal() {
        Assert.assertEquals("HelloWorld", StringKit.toPascalCase("hello_world"));
        Assert.assertEquals("HelloWorld", StringKit.toPascalCase("HELLO_WORLD"));
        Assert.assertEquals("HelloWorld", StringKit.toPascalCase("Hello_WoRld"));
    }

    @Test
    public void testUppercaseSeparate() {
        Assert.assertEquals("hello-world", StringKit.uppercaseSeparate("HelloWorld", "-"));
        Assert.assertEquals("hello@world", StringKit.uppercaseSeparate("helloWorld", "@"));
    }

    @Test
    public void testSubStringFirstAfter() {
        Assert.assertEquals("def", StringKit.subStringAfterFirst("abcdef", "c"));
        Assert.assertEquals("abcdef", StringKit.subStringAfterFirst("abcdef", "g"));
        Assert.assertEquals("defabcdef", StringKit.subStringAfterFirst("abcdefabcdef", "c"));
    }

    @Test
    public void testSubStringFirstBefore() {
        Assert.assertEquals("ab", StringKit.subStringBeforeFirst("abcdef", "c"));
        Assert.assertEquals("", StringKit.subStringBeforeFirst("abcdef", "g"));
        Assert.assertEquals("ab", StringKit.subStringBeforeFirst("abcdefabcdef", "c"));
    }


    @Test
    public void testToInteger() {
        Assert.assertEquals("10", StringKit.toInteger("10", 1).toString());

        // 空值则返回默认值
        Assert.assertEquals("1", StringKit.toInteger(" ", 1).toString());
        Assert.assertEquals("1", StringKit.toInteger(null, 1).toString());

        // 空值返回null
        Assert.assertEquals(null, StringKit.toInteger(null));
        Assert.assertEquals(null, StringKit.toInteger(""));
    }


    @Test(expected = NumberFormatException.class)
    public void testToInteger2() {
        StringKit.toInteger("02-238");
    }

    @Test
    public void testToLong() {
        Assert.assertEquals("100000000", StringKit.toLong("100000000", 1L).toString());

        // 空值则返回默认值
        Assert.assertEquals("1", StringKit.toLong("", 1L).toString());
        Assert.assertEquals("1", StringKit.toLong(null, 1L).toString());

        // 空值返回null
        Assert.assertEquals(null, StringKit.toLong(null));
        Assert.assertEquals(null, StringKit.toLong(""));
    }

    @Test
    public void testCountAsciiAlphas() {
        Assert.assertEquals(7, StringKit.countAsciiAlphas("我是xXsdAx数据库Y的"));

        Assert.assertEquals(0, StringKit.countAsciiAlphas("我是123数据库23的"));
    }

    @Test
    public void testCountCapitalizedAsciiAlphas() {
        Assert.assertEquals(3, StringKit.countCapitalizedAsciiAlphas("我是xXsdAx数据库Y的"));
    }

    @Test
    public void testCountLowercaseAsciiAlphas() {
        Assert.assertEquals(4, StringKit.countLowercaseAsciiAlphas("我是xXsdAx数据库Y的"));
        Assert.assertEquals(0, StringKit.countLowercaseAsciiAlphas("我是XDA数据库Y的"));
    }

    @Test
    public void testContainsLowercaseAsciiAlphas() {
        Assert.assertTrue(StringKit.containsLowercaseAsciiAlphas("我是xXsdAx数据库Y的"));
        Assert.assertFalse(StringKit.containsLowercaseAsciiAlphas("我是XDA数据库Y的"));
    }

    @Test
    public void testCountDigit() {
        Assert.assertEquals(3, StringKit.countDigits("我是134abc的人"));
    }

    @Test
    public void testContainsAsciiAlpha() {
        Assert.assertTrue(StringKit.containsAsciiAlpha("我是xXsdAx数据库Y的"));
        Assert.assertFalse(StringKit.containsAsciiAlpha("我是123数据库56的"));
    }

    @Test
    public void testContainsCapitalizedAsciiAlpha() {
        Assert.assertTrue(StringKit.containsCapitalizedAsciiAlpha("我是xXsdAx数据库Y的"));
        Assert.assertFalse(StringKit.containsCapitalizedAsciiAlpha("我是xxsdax数据库e的"));
    }

    @Test
    public void testContainsChinese() {
        Assert.assertTrue(StringKit.containsChineseChars("中国.."));
        Assert.assertFalse(StringKit.containsChineseChars("zhongguo.."));
    }

    @Test
    public void testContainsDigit() {
        Assert.assertTrue(StringKit.containsDigit("我是123数据库56的"));
        Assert.assertFalse(StringKit.containsDigit("我是数据库的"));
    }

    @Test
    public void testSplitTrim() {
        Assert.assertArrayEquals(new String[]{"a", "b", "c", "d", "e", "f"}, StringKit.splitTrim("a, b, c,  d  , e, f", ","));
    }




}
