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
}
