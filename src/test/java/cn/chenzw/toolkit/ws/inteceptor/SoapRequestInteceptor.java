package cn.chenzw.toolkit.ws.inteceptor;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import okio.Buffer;

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

        Buffer buffer = new Buffer();
        request.body().writeTo(buffer);

        System.out.println(buffer.readUtf8());

        System.out.println("uri: " + request.url() + ", body: " + buffer.readUtf8());

        Response response = chain.proceed(request);

        System.out.println("code: " + response.code() + ", message: " + response.peekBody(2048).string());

        return response;
    }
}
