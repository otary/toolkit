package cn.chenzw.toolkit.http;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class ResponseUtilsTests {

    @Test
    public void testBuildHtmlMsg() {
        Assert.assertEquals("<div style=\"position: relative; width: 100%; height: 500px; text-align:center;\"><div style=\" width: 300px; margin: 100px auto; padding: 10px; border: 1px solid #ccc; border-radius: 5px; overflow: hidden;\"><div style=\"padding: 10px 3px; border-bottom: 1px solid #ccc; font-weight: bold;\">提示</div><div style=\"padding: 10px 3px;\">系统异常</div></div></div>", ResponseUtils.buildHtmlMsg("提示", "系统异常"));

        Assert.assertEquals("<div style=\"position: relative; width: 100%; height: 500px; text-align:center;\"><div style=\" width: 300px; margin: 100px auto; padding: 10px; border: 1px solid #ccc; border-radius: 5px; overflow: hidden;\"><div style=\"padding: 10px 3px; border-bottom: 1px solid #ccc; font-weight: bold;\">提示</div><div style=\"padding: 10px 3px;\">【内容:】: 系统异常</div></div></div>", ResponseUtils.buildHtmlMsg("提示", "内容:", "系统异常"));
    }



}
