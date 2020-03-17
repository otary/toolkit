package cn.chenzw.toolkit.dial.http.okhttp;

import cn.chenzw.toolkit.dial.core.support.DialProcessor;
import cn.chenzw.toolkit.dial.core.support.DialRequest;
import cn.chenzw.toolkit.dial.core.support.DialResponse;
import cn.chenzw.toolkit.dial.core.support.DialResponseResolver;
import okhttp3.OkHttpClient;
import okhttp3.Response;

import java.io.IOException;

/**
 * 拨测处理器（OkHttp）
 *
 * @author chenzw
 * @since 1.0.3
 */
public class OkHttpDialProcessor implements DialProcessor {

    private OkHttpClient okHttpClient;

    public OkHttpDialProcessor() {
        this.okHttpClient = new OkHttpClient.Builder().build();
    }

    public OkHttpDialProcessor(OkHttpClient okHttpClient) {
        this.okHttpClient = okHttpClient;
    }

    @Override
    public DialResponse execute(DialRequest request, DialResponseResolver dialResponseResolver) throws IOException {
        Response response = okHttpClient.newCall(OkHttpDialRequest.of((OkHttpDialRequest) request)).execute();
        if (dialResponseResolver == null) {
            return new OkHttpDialResponse(response);
        }
        return new OkHttpDialResponse(response, dialResponseResolver);
    }
}
