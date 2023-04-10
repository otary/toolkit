package cn.chenzw.toolkit.core.util;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

/**
 * @author chenzw
 */
@RunWith(JUnit4.class)
public class ValidatorTests {

    @Test
    public void testIsEmail() {
        Assert.assertTrue(Validator.isEmail("656469722@qq.com"));
        Assert.assertTrue(Validator.isEmail("chenzw_123@16.com"));

        // 非法字符串"#@"
        Assert.assertFalse(Validator.isEmail("chenzw#123@163.com"));
        Assert.assertFalse(Validator.isEmail("chenzw@123@163.com"));

    }

    @Test
    public void testIsIp() {
        Assert.assertTrue(Validator.isIPv4("10.2.2.4"));
        Assert.assertTrue(Validator.isIPv4("192.168.255.255"));

        Assert.assertFalse(Validator.isIPv4("1.1.1.1/12"));
        Assert.assertFalse(Validator.isIPv4("1.1.1"));
    }

    @Test
    public void testIsChinese() {
        Assert.assertTrue(Validator.isChinese("终南山"));

        Assert.assertFalse(Validator.isChinese("终南山."));
        Assert.assertFalse(Validator.isChinese("中zhong国"));
    }



    @Test
    public void testIsPhoneNO() {
        Assert.assertTrue(Validator.isChinesePhoneNO("18012283835"));

        // 少于11位
        Assert.assertFalse(Validator.isChinesePhoneNO("1801228383"));
        // 多余11位
        Assert.assertFalse(Validator.isChinesePhoneNO("180122838356"));
        // 非法字符
        Assert.assertFalse(Validator.isChinesePhoneNO("1801228383."));
    }

    @Test
    public void testIsIdCard() {
        Assert.assertTrue(Validator.isChineseIdCard("350681199910100578"));
        Assert.assertTrue(Validator.isChineseIdCard("130503670401001"));

        // too long
        Assert.assertFalse(Validator.isChineseIdCard("3506811999101005782"));


    }


    @Test
    public void testIsQQ() {
        Assert.assertTrue(Validator.isQQ("6746062"));
        Assert.assertFalse(Validator.isQQ("674a6062"));
    }

    @Test
    public void testIsLooseMobile() {
        Assert.assertTrue(Validator.isChinesePhoneNOLoose("18046048666"));
        Assert.assertTrue(Validator.isChinesePhoneNOLoose("018046048666"));
        Assert.assertTrue(Validator.isChinesePhoneNOLoose("8618046048666"));
        Assert.assertTrue(Validator.isChinesePhoneNOLoose("+8618046048666"));
        Assert.assertFalse(Validator.isChinesePhoneNOLoose("12342345679"));
    }

    @Test
    public void testHasSpecialCharacters() {
        Assert.assertEquals(4, Validator.countSpecialCharacters("我是xsx123-+ss@#"));
        Assert.assertEquals(0, Validator.countSpecialCharacters("我是xsx123ss我"));
    }

    @Test
    public void testContainsSpecialCharacter() {
        Assert.assertTrue(Validator.containsSpecialCharacter("我是+012-1s一颗韭菜"));
        Assert.assertFalse(Validator.containsSpecialCharacter("我是0121s一颗韭菜"));
    }
}
