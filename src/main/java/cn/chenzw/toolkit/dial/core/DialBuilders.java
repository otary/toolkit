package cn.chenzw.toolkit.dial.core;

/**
 * 拨测构建器
 *
 * @author chenzw
 * @since 1.0.3
 */
public final class DialBuilders {

    public static DialBuilder.Builder createOkHttpBuilder() {
        return new DialBuilder.Builder(new cn.chenzw.toolkit.dial.http.okhttp.OkHttpDialProcessor());
    }


    public static DialBuilder.Builder createOkHttpBuilder(okhttp3.OkHttpClient okHttpClient) {
        return new DialBuilder.Builder(new cn.chenzw.toolkit.dial.http.okhttp.OkHttpDialProcessor(okHttpClient));
    }
}
