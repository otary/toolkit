package cn.chenzw.toolkit.commons;

import cn.chenzw.toolkit.domain.entity.Book;
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
        Assert.assertEquals("http://www.baidu.com?a=111&b=222", uri);

        String uri2 = UriExtUtils.buildParams("http://www.baidu.com?k=1", params);
        Assert.assertEquals("http://www.baidu.com?k=1&a=111&b=222", uri2);

        String uri3 = UriExtUtils.buildParams("http://www.baidu.com?", params);
        Assert.assertEquals("http://www.baidu.com?a=111&b=222", uri3);
    }

    @Test
    public void testGetUriParams() {
        Map<String, String> uriParams = UriExtUtils.getUriParams(
                "http://192.168.17.231:8680/login?client_id=1&redirect_uri=http%3A%2F%2Fwww.baidu.com&authentication_type=oauth");
        Assert.assertEquals("1", MapUtils.getString(uriParams, "client_id"));
        Assert.assertEquals("http%3A%2F%2Fwww.baidu.com", MapUtils.getString(uriParams, "redirect_uri"));
        Assert.assertEquals("oauth", MapUtils.getString(uriParams, "authentication_type"));

        Map<String, String> uriParams2 = UriExtUtils.getUriParams("http://192.168.17.231:8680/login");
        Assert.assertTrue(MapUtils.isEmpty(uriParams2));

        Map<String, String> uriParams3 = UriExtUtils.getUriParams("http://192.168.17.231:8680/login?");
        Assert.assertTrue(MapUtils.isEmpty(uriParams3));

        Map<String, String> uriParams4 = UriExtUtils
                .getUriParams("http://192.168.17.231:8680/login?client_id=1&redirect_uri=&authentication_type");
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

        String uri = UriExtUtils.buildParams("http://www.baidu.com", book);

        Assert.assertEquals("http://www.baidu.com?isbn=ISN12345&name=hello&id=1", uri);
    }

}
