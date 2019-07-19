package cn.chenzw.toolkit.commons;

import org.apache.commons.collections.MapUtils;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.HashMap;
import java.util.Map;

@RunWith(JUnit4.class)
public class UriExtUtilsTests {


    @Test
    public void testBuildParams() {
        Map<String, String> params = new HashMap<>();
        params.put("a", "111");
        params.put("b", "222");

        String uri = UriExtUtils.buildParams("http://www.baidu.com", params);
        Assert.assertEquals(uri, "http://www.baidu.com?a=111&b=222");

        String uri2 = UriExtUtils.buildParams("http://www.baidu.com?k=1", params);
        Assert.assertEquals(uri2, "http://www.baidu.com?k=1&a=111&b=222");

        String uri3 = UriExtUtils.buildParams("http://www.baidu.com?", params);
        Assert.assertEquals(uri3, "http://www.baidu.com?a=111&b=222");
    }

    @Test
    public void testGetUriParams() {
        Map<String, String> uriParams = UriExtUtils.getUriParams(
                "http://192.168.17.231:8680/login?client_id=1&redirect_uri=http%3A%2F%2Fwww.baidu.com&authentication_type=oauth");
        Assert.assertEquals(MapUtils.getString(uriParams, "client_id"), "1");
        Assert.assertEquals(MapUtils.getString(uriParams, "redirect_uri"), "http%3A%2F%2Fwww.baidu.com");
        Assert.assertEquals(MapUtils.getString(uriParams, "authentication_type"), "oauth");
    }

}
