package cn.chenzw.toolkit.http;

import cn.chenzw.toolkit.spring.config.AppConfig;
import cn.chenzw.toolkit.spring.config.WebConfig;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = {AppConfig.class, WebConfig.class})
public class RequestUtilsTests {

    @Autowired
    MockHttpServletRequest request;


    @Test
    public void testGetFirstParamter() {
        request.addParameter("aaa", "bbb");
        request.addParameter("aaa", "cccc");

        Assert.assertEquals("bbb", RequestUtils.getFirstParamter(request.getParameter("aaa")));
    }
}
