package cn.chenzw.toolkit.core.net;

import cn.chenzw.toolkit.core.domain.entity.Book;
import org.apache.commons.collections4.MapUtils;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.HashMap;
import java.util.Map;

@RunWith(JUnit4.class)
public class URLKitTests {

    @Test
    public void testBuildParams() {
        Map<String, String> params = new HashMap<String, String>() {
            {
                put("a", "111");
                put("b", "222");
            }
        };

        String url = URLKit.buildParams("http://www.baidu.com", params);
        Assert.assertEquals("http://www.baidu.com?a=111&b=222", url);

        String url2 = URLKit.buildParams("http://www.baidu.com?k=1", params);
        Assert.assertEquals("http://www.baidu.com?k=1&a=111&b=222", url2);

        String url3 = URLKit.buildParams("http://www.baidu.com?", params);
        Assert.assertEquals("http://www.baidu.com?a=111&b=222", url3);
    }

    @Test
    public void testGetUriParams() {
        Map<String, String> uriParams = URLKit.getParams(
                "http://192.168.17.231:8680/login?client_id=1&redirect_uri=http%3A%2F%2Fwww.baidu.com&authentication_type=oauth");
        Assert.assertEquals("1", MapUtils.getString(uriParams, "client_id"));
        Assert.assertEquals("http%3A%2F%2Fwww.baidu.com", MapUtils.getString(uriParams, "redirect_uri"));
        Assert.assertEquals("oauth", MapUtils.getString(uriParams, "authentication_type"));

        Map<String, String> uriParams2 = URLKit.getParams("http://192.168.17.231:8680/login");
        Assert.assertTrue(MapUtils.isEmpty(uriParams2));

        Map<String, String> uriParams3 = URLKit.getParams("http://192.168.17.231:8680/login?");
        Assert.assertTrue(MapUtils.isEmpty(uriParams3));

        Map<String, String> uriParams4 = URLKit
                .getParams("http://192.168.17.231:8680/login?client_id=1&redirect_uri=&authentication_type");
        Assert.assertEquals("1", MapUtils.getString(uriParams4, "client_id"));
        Assert.assertEquals("", MapUtils.getString(uriParams4, "redirect_uri"));
        Assert.assertEquals("", MapUtils.getString(uriParams4, "authentication_type"));

    }

    @Test
    public void testBuildParams2() throws IllegalAccessException {
        Book book = new Book();
        book.setId(1L);
        book.setIsbn("ISN12345");
        book.setName("hello");

        String url = URLKit.buildParams("http://www.baidu.com", book);

        Assert.assertEquals("http://www.baidu.com?id=1&isbn=ISN12345&name=hello", url);
    }

    @Test
    public void testBuildParams3() throws IllegalAccessException {
        Book book = new Book();
        book.setId(1L);
        book.setIsbn("ISN12345");
        book.setName("hello");

        String url = URLKit.buildParams(book);
        Assert.assertEquals("id=1&isbn=ISN12345&name=hello", url);
    }

    @Test
    public void testRemoveAllParams() {
        String uri = URLKit.removeAllParams("https://www.baidu.com?q=xx&s=ttt");

        Assert.assertEquals("https://www.baidu.com", uri);
    }


    @Test
    public void testRemoveParam() {
        String uri = URLKit.removeParams("https://www.baidu.com?q=xx&s=ttt", "s");

        Assert.assertEquals("https://www.baidu.com?q=xx", uri);
    }
}
