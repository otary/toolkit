package cn.chenzw.toolkit.dial.http.okhttp;

import java.util.HashMap;
import java.util.Map;

/**
 * 请求构建器（OkHttp）
 *
 * @author chenzw
 * @since 1.0.3
 */
public final class OkHttpDialRequestBuilder {

    public static final class Builder {
        private String url;
        private String method;
        private Map<String, String> headers = new HashMap<>();
        private Object data;

        public Builder url(String url) {
            if (url == null) throw new NullPointerException("url == null");

            // Silently replace web socket URLs with HTTP URLs.
            if (url.regionMatches(true, 0, "ws:", 0, 3)) {
                this.url = "http:" + url.substring(3);
            } else if (url.regionMatches(true, 0, "wss:", 0, 4)) {
                this.url = "https:" + url.substring(4);
            } else {
                this.url = url;
            }
            return this;
        }

        public Builder addHeader(String name, String value) {
            headers.put(name, value);
            return this;
        }

        public Builder get() {
            this.method = "GET";
            return this;
        }

        public Builder data(Object data) {
            this.data = data;
            return this;
        }

        public OkHttpDialRequest build() {
            return new OkHttpDialRequest(url, method, headers, data);
        }
    }
}
