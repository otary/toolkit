package cn.chenzw.toolkit.core.util;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

@RunWith(JUnit4.class)
public class RegexKitTests {

    @Test
    public void testIsMatches() {
        Assert.assertTrue(
                RegexKit.isMatches(
                        Pattern.compile("\\d+"), "999"
                )
        );

        Assert.assertFalse(
                RegexKit.isMatches(
                        Pattern.compile("\\d+"), "a999"
                )
        );
    }

    @Test
    public void testContains() {
        Assert.assertTrue(
                RegexKit.contains(
                        Pattern.compile("\\d+"), "ab9"
                )
        );
        Assert.assertFalse(
                RegexKit.contains(
                        Pattern.compile("\\d+"), "abc"
                )
        );
    }


    @Test
    public void testCount() {
        Assert.assertEquals(3,
                RegexKit.count(
                        Pattern.compile("\\d"), "abc999"
                )
        );
    }

    @Test
    public void testGetGroups() {
        // 获取分组1的值集合
        Assert.assertEquals(
                Arrays.asList("123", "345"),
                RegexKit.getGroups(
                        Pattern.compile("(\\d+)"), "abc123cde345", 1
                )
        );
    }
}
