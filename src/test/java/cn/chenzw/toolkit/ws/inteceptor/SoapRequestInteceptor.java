package cn.chenzw.toolkit.ws.inteceptor;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;

/**
 * 拦截器
 *
 * @author chenzw
 */
public class SoapRequestInteceptor implements Interceptor {

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        System.out.println("uri: " + request.url() + ", body: " + request.body().toString());

        Response response = chain.proceed(request);

        System.out.println("code: " + response.code() + ", message: " + response.peekBody(2048).string());

        return response;
    }
}
