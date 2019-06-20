package cn.chenzw.toolkit.commons;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class RegexUtilsTests {


    @Test
    public void testIsEmail() {
        Assert.assertTrue(RegexUtils.isEmail("656469722@qq.com"));
        Assert.assertTrue(RegexUtils.isEmail("chenzw_123@16.com"));

        // 非法字符串"#"
        Assert.assertFalse(RegexUtils.isEmail("chenzw#123@163.com"));
        Assert.assertFalse(RegexUtils.isEmail("chenzw@123@163.com"));

    }

    @Test
    public void testIsIp() {
        Assert.assertTrue(RegexUtils.isIPv4("10.2.2.4"));
        Assert.assertTrue(RegexUtils.isIPv4("192.168.255.255"));

        Assert.assertFalse(RegexUtils.isIPv4("1.1.1.1/12"));
        Assert.assertFalse(RegexUtils.isIPv4("1.1.1"));
    }

    @Test
    public void testIsChinese() {
        Assert.assertTrue(RegexUtils.isChinese("终南山"));

        Assert.assertFalse(RegexUtils.isChinese("终南山."));
        Assert.assertFalse(RegexUtils.isChinese("中zhong国"));
    }

    @Test
    public void testContainsChinese() {
        Assert.assertTrue(RegexUtils.containsChinese("中国.."));

        Assert.assertFalse(RegexUtils.containsChinese("zhongguo.."));
    }

    @Test
    public void testIsPhoneNO() {
        Assert.assertTrue(RegexUtils.isPhoneNO("18012283835"));

        // 少于11位
        Assert.assertFalse(RegexUtils.isPhoneNO("1801228383"));
        // 多余11位
        Assert.assertFalse(RegexUtils.isPhoneNO("180122838356"));
        // 非法字符
        Assert.assertFalse(RegexUtils.isPhoneNO("1801228383."));
    }

    @Test
    public void testIsIdCard() {
        Assert.assertTrue(RegexUtils.isIdCard("350681199910100578"));
        Assert.assertTrue(RegexUtils.isIdCard("130503670401001"));

        // too long
        Assert.assertFalse(RegexUtils.isIdCard("3506811999101005782"));
    }


    @Test
    public void testIsInteger() {
        Assert.assertTrue(RegexUtils.isInteger("56745"));
        Assert.assertTrue(RegexUtils.isInteger("-3545"));

        // 小数
        Assert.assertFalse(RegexUtils.isInteger("56.332"));
        Assert.assertFalse(RegexUtils.isInteger("-333.32"));
    }


    @Test
    public void testIsNumber() {
        Assert.assertTrue(RegexUtils.isNumber("2322"));
        Assert.assertTrue(RegexUtils.isNumber("23.22"));
        Assert.assertTrue(RegexUtils.isNumber("-2322"));
        Assert.assertTrue(RegexUtils.isNumber("-23.22"));


        Assert.assertFalse(RegexUtils.isNumber("1s4"));
    }

}
