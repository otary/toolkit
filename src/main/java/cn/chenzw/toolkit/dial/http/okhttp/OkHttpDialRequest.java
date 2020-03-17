package cn.chenzw.toolkit.dial.http.okhttp;

import cn.chenzw.toolkit.dial.http.DialHttpRequest;
import okhttp3.Request;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.Map;

/**
 * 拨测请求（Okhttp）
 *
 * @author chenzw
 * @since 1.0.3
 */
public class OkHttpDialRequest implements DialHttpRequest {

    private String url;
    private String method;
    private Map<String, String> headers;
    private Object data;

    /**
     * 生成Rokhttp3.Request对象
     *
     * @param okHttpDialRequest
     * @return {@link okhttp3.Request}
     */
    public static Request of(OkHttpDialRequest okHttpDialRequest) {
        Request.Builder builder = new Request.Builder();

        if (StringUtils.isNotEmpty(okHttpDialRequest.getUrl())) {
            builder.url(okHttpDialRequest.getUrl());
        }

        if (StringUtils.isNotEmpty(okHttpDialRequest.getMethod())) {
            builder.method(okHttpDialRequest.getMethod(), null);
        }

        if (MapUtils.isNotEmpty(okHttpDialRequest.getHeaders())) {
            Map<String, String> headers = okHttpDialRequest.getHeaders();
            headers.forEach((k, v) -> {
                builder.addHeader(k, v);
            });
        }
        return builder.build();
    }


    public OkHttpDialRequest(String url, String method, Map<String, String> headers, Object data) {
        this.url = url;
        this.method = method;
        this.headers = headers;
        this.data = data;
    }

    @Override
    public String getUrl() {
        return this.url;
    }

    @Override
    public String getMethod() {
        return this.method;
    }

    @Override
    public Map<String, String> getHeaders() {
        return this.headers;
    }

    @Override
    public Object getData() {
        return data;
    }
}
