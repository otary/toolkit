package cn.chenzw.toolkit.commons;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class StringExtUtilsTests {

    @Test
    public void testToUnderscore() {
        Assert.assertEquals("hello_world", StringExtUtils.toUnderscore("HelloWorld"));
        Assert.assertEquals("hello_world", StringExtUtils.toUnderscore("helloWorld"));
        Assert.assertEquals("hello2_world", StringExtUtils.toUnderscore("Hello2World"));
        Assert.assertEquals("h_ello_w_orld", StringExtUtils.toUnderscore("HElloWOrld"));
    }

    @Test
    public void testToCamel() {
        Assert.assertEquals("helloWorld", StringExtUtils.toCamel("hello_world"));
        Assert.assertEquals("helloWorld", StringExtUtils.toCamel("HELLO_WORLD"));
        Assert.assertEquals("helloWorld", StringExtUtils.toCamel("Hello_WoRld"));
    }

    @Test
    public void testToPascal() {
        Assert.assertEquals("HelloWorld", StringExtUtils.toPascal("hello_world"));
        Assert.assertEquals("HelloWorld", StringExtUtils.toPascal("HELLO_WORLD"));
        Assert.assertEquals("HelloWorld", StringExtUtils.toPascal("Hello_WoRld"));
    }

    @Test
    public void testUppercaseSeparate() {
        Assert.assertEquals("hello-world", StringExtUtils.uppercaseSeparate("HelloWorld", "-"));
        Assert.assertEquals("hello@world", StringExtUtils.uppercaseSeparate("helloWorld", "@"));
    }

    @Test
    public void testSubStringFirstAfter() {
        Assert.assertEquals("def", StringExtUtils.subStringFirstAfter("abcdef", "c"));
        Assert.assertEquals("abcdef", StringExtUtils.subStringFirstAfter("abcdef", "g"));
        Assert.assertEquals("defabcdef", StringExtUtils.subStringFirstAfter("abcdefabcdef", "c"));
    }

    @Test
    public void testSubStringFirstBefore() {
        Assert.assertEquals("ab", StringExtUtils.subStringFirstBefore("abcdef", "c"));
        Assert.assertEquals("", StringExtUtils.subStringFirstBefore("abcdef", "g"));
        Assert.assertEquals("ab", StringExtUtils.subStringFirstBefore("abcdefabcdef", "c"));
    }


    @Test
    public void testToInteger() {
        Assert.assertEquals("10", StringExtUtils.toInteger("10", 1).toString());

        // 空值则返回默认值
        Assert.assertEquals("1", StringExtUtils.toInteger(" ", 1).toString());
        Assert.assertEquals("1", StringExtUtils.toInteger(null, 1).toString());

        // 空值返回null
        Assert.assertEquals(null, StringExtUtils.toInteger(null));
        Assert.assertEquals(null, StringExtUtils.toInteger(""));

    }

    @Test
    public void testToLong() {
        Assert.assertEquals("100000000", StringExtUtils.toLong("100000000", 1L).toString());

        // 空值则返回默认值
        Assert.assertEquals("1", StringExtUtils.toLong("", 1L).toString());
        Assert.assertEquals("1", StringExtUtils.toLong(null, 1L).toString());

        // 空值返回null
        Assert.assertEquals(null, StringExtUtils.toLong(null));
        Assert.assertEquals(null, StringExtUtils.toLong(""));
    }

    @Test
    public void testHasAsciiAlphas() {
        Assert.assertEquals(7, StringExtUtils.hasAsciiAlphas("我是xXsdAx数据库Y的"));

        Assert.assertEquals(0, StringExtUtils.hasAsciiAlphas("我是123数据库23的"));
    }

    @Test
    public void testHasCapitalizedAsciiAlphas() {
        Assert.assertEquals(3, StringExtUtils.hasCapitalizedAsciiAlphas("我是xXsdAx数据库Y的"));
    }

    @Test
    public void testHasLowercaseAsciiAlphas() {
        Assert.assertEquals(4, StringExtUtils.hasLowercaseAsciiAlphas("我是xXsdAx数据库Y的"));
        Assert.assertEquals(0, StringExtUtils.hasLowercaseAsciiAlphas("我是XdA数据库Y的"));
    }

    @Test
    public void testContainsLowercaseAsciiAlphas(){
        Assert.assertTrue(StringExtUtils.containsLowercaseAsciiAlphas("我是xXsdAx数据库Y的"));
        Assert.assertFalse(StringExtUtils.containsLowercaseAsciiAlphas("我是XdA数据库Y的"));
    }

    @Test
    public void testHasDigit() {
        Assert.assertEquals(3, StringExtUtils.hasDigit("我是134abc的人"));
    }

    @Test
    public void testContainsAsciiAlpha() {
        Assert.assertTrue(StringExtUtils.containsAsciiAlpha("我是xXsdAx数据库Y的"));

        Assert.assertFalse(StringExtUtils.containsAsciiAlpha("我是123数据库56的"));
    }

    @Test
    public void testContainsCapitalizedAsciiAlpha() {
        Assert.assertTrue(StringExtUtils.containsCapitalizedAsciiAlpha("我是xXsdAx数据库Y的"));

        Assert.assertFalse(StringExtUtils.containsCapitalizedAsciiAlpha("我是xxsdax数据库e的"));
    }

    @Test
    public void testContainsDigit() {
        Assert.assertTrue(StringExtUtils.containsDigit("我是123数据库56的"));

        Assert.assertFalse(StringExtUtils.containsDigit("我是数据库的"));
    }



}
