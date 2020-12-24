package cn.chenzw.toolkit.http.okhttp;

import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.HttpUrl;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * okHttp持久化Cookie
 *
 * @author chenzw
 * @since 1.0.3
 */
public class PersistenceCookieJar implements CookieJar {

    private final Map<String, List<Cookie>> cookieStore = new HashMap<>();

    @Override
    public void saveFromResponse(HttpUrl url, List<Cookie> cookies) {
        cookieStore.put(url.host(), cookies);
    }

    @Override
    public List<Cookie> loadForRequest(HttpUrl url) {
        List<Cookie> cookies = cookieStore.get(url.host());

        if (cookies == null) {
            return Collections.EMPTY_LIST;
        }
        return cookies;
    }

}
