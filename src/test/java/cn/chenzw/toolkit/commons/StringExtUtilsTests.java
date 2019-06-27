package cn.chenzw.toolkit.commons;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class StringExtUtilsTests {

    @Test
    public void testToUnderscore() {
        Assert.assertEquals(StringExtUtils.toUnderscore("HelloWorld"), "hello_world");
        Assert.assertEquals(StringExtUtils.toUnderscore("helloWorld"), "hello_world");
        Assert.assertEquals(StringExtUtils.toUnderscore("Hello2World"), "hello2_world");
        Assert.assertEquals(StringExtUtils.toUnderscore("HElloWOrld"), "h_ello_w_orld");
    }

    @Test
    public void testToCamel() {
        Assert.assertEquals(StringExtUtils.toCamel("hello_world"), "helloWorld");
        Assert.assertEquals(StringExtUtils.toCamel("HELLO_WORLD"), "helloWorld");
        Assert.assertEquals(StringExtUtils.toCamel("Hello_WoRld"), "helloWorld");
    }

    @Test
    public void testUppercaseSeparate() {
        Assert.assertEquals(StringExtUtils.uppercaseSeparate("HelloWorld", "-"), "hello-world");
        Assert.assertEquals(StringExtUtils.uppercaseSeparate("helloWorld", "@"), "hello@world");
    }
}
