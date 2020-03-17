package cn.chenzw.toolkit.dial.http.okhttp;

import cn.chenzw.toolkit.dial.core.support.DialResponseResolver;
import cn.chenzw.toolkit.dial.http.DialHttpResponse;
import okhttp3.Headers;
import okhttp3.Response;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * 拨测响应对象（OkHttp）
 *
 * @author chenzw
 * @since 1.0.3
 */
public class OkHttpDialResponse implements DialHttpResponse {

    private Response response;

    private DialResponseResolver dialResponseResolver;

    public OkHttpDialResponse(Response response) {
        this.response = response;
        this.dialResponseResolver = new OkHttpDialResponseResolver(response);
    }

    public OkHttpDialResponse(Response response, DialResponseResolver dialResponseResolver) {
        this.response = response;

        if (dialResponseResolver == null) {
            this.dialResponseResolver = new OkHttpDialResponseResolver(response);
        } else {
            this.dialResponseResolver = dialResponseResolver;
        }
    }

    @Override
    public DialResponseResolver getResponseResolver() {
        return this.dialResponseResolver;
    }

    @Override
    public boolean isSuccessful() {
        return this.dialResponseResolver.isSuccessful();
    }

    @Override
    public void close() {
        this.response.close();
    }

    @Override
    public int code() {
        return this.response.code();
    }

    @Override
    public String message() {
        return this.response.message();
    }

    @Override
    public String body() {
        try {
            return this.response.body().string();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

    @Override
    public Map<String, String> headers() {
        Map<String, String> headerMap = new HashMap<>();
        Headers headers = this.response.headers();
        Set<String> names = headers.names();
        for (String name : names) {
            headerMap.put(name, headers.get(name));
        }
        return headerMap;
    }


    @Override
    public String toString() {
        return "OkHttpDialResponse{" +
                "code=" + code() +
                ", message=" + message() +
                '}';
    }
}
