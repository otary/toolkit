package cn.chenzw.toolkit.dial.http.okhttp;

import cn.chenzw.toolkit.dial.core.support.DialResponseResolver;
import okhttp3.Response;

/**
 * 响应对象解析器（OkHttp）
 *
 * @author chenzw
 * @since 1.0.3
 */
public class OkHttpDialResponseResolver implements DialResponseResolver {

    private Response response;

    public OkHttpDialResponseResolver(Response response) {
        this.response = response;
    }

    @Override
    public boolean isSuccessful() {
        if (this.response.isSuccessful()) {
            return true;
        }
        return false;
    }
}
