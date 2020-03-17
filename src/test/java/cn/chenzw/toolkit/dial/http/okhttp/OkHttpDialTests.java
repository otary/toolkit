package cn.chenzw.toolkit.dial.http.okhttp;

import cn.chenzw.toolkit.dial.core.DialBuilder;
import cn.chenzw.toolkit.dial.http.DialHttpRequest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;


@RunWith(JUnit4.class)
public class OkHttpDialTests {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Test
    public void testBasic() {

        // 拨测构建器
        DialBuilder dialBuilder = new DialBuilder.Builder(new OkHttpDialProcessor()).success((request, response) -> {
            // 成功回调
            DialHttpRequest dialHttpRequest = (DialHttpRequest) request;
            logger.info("Request url [{}] with data [{}] success!", dialHttpRequest.getUrl(), dialHttpRequest.getData());
        }).failure((request, response) -> {
            // 失败回调
            DialHttpRequest dialHttpRequest = (DialHttpRequest) request;
            logger.warn("Request url [{}] with data [{}] failure!", dialHttpRequest.getUrl(), dialHttpRequest.getData());
        }).exception((request, response, e) -> {
            // 异常回调
            DialHttpRequest dialHttpRequest = (DialHttpRequest) request;
            logger.warn("Request url [{}] with exception!", dialHttpRequest.getUrl(), e);
        }).build();

        Map<String, String> data = new HashMap<String, String>() {{
            put("x", "1");
            put("y", "2");
        }};
        // 一次请求
        OkHttpDialRequest okHttpDialRequest = new OkHttpDialRequestBuilder.Builder().get()
                .url("https://www.baidu.com/")
                .addHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/79.0.3945.88 Safari/537.36")
                .addHeader("cookie", "locale=en-US;")
                .data(data)
                .build();
        dialBuilder.execute(okHttpDialRequest);
    }
}
