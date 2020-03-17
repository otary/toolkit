package cn.chenzw.toolkit.dial.core;

import cn.chenzw.toolkit.dial.core.support.*;
import cn.chenzw.toolkit.dial.http.DialHttpRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * 拨测构建器
 *
 * @author chenzw
 * @since 1.0.3
 */
public class DialBuilder {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    private DialProcessor dialProcessor;

    private Callback successCallback;

    private Callback failureCallback;

    private ExceptionCallback exceptionCallback;

    private DialResponseResolver dialResponseResolver;

    DialBuilder(Builder builder) {
        this.dialProcessor = builder.dialProcessor;
        this.successCallback = builder.successCallback;
        this.failureCallback = builder.failureCallback;
        this.exceptionCallback = builder.exceptionCallback;
        this.dialResponseResolver = builder.dialResponseResolver;
    }


    public void execute(DialRequest dialRequest) {
        DialResponse dialResponse = null;
        try {
            dialResponse = this.dialProcessor.execute(dialRequest, dialResponseResolver);
        } catch (Exception e) {
            if (this.exceptionCallback != null) {
                this.exceptionCallback.call(dialRequest, dialResponse, e);
            } else {
                if (dialRequest instanceof DialHttpRequest) {
                    DialHttpRequest dialHttpRequest = (DialHttpRequest) dialRequest;
                    logger.error("[{}] process url [{}] with exception!", this.dialProcessor.getClass().getSimpleName(), dialHttpRequest.getUrl(), e);
                } else {
                    logger.error("[{}] process with exception!", this.dialProcessor.getClass().getSimpleName(), e);
                }
            }
            return;
        }

        if (dialResponse.isSuccessful()) {
            if (this.successCallback != null) {
                this.successCallback.call(dialRequest, dialResponse);
            }
        } else {
            if (this.failureCallback != null) {
                this.failureCallback.call(dialRequest, dialResponse);
            } else {
                if (dialRequest instanceof DialHttpRequest) {
                    DialHttpRequest dialHttpRequest = (DialHttpRequest) dialRequest;
                    logger.error("[{}] process url [{}] with exception!", this.dialProcessor.getClass().getSimpleName(), dialHttpRequest.getUrl());
                } else {
                    logger.warn("[{}] process fail!", this.dialProcessor.getClass().getSimpleName());
                }
            }
        }

        if (dialResponse != null) {
            dialResponse.close();
        }
    }


    /**
     * 构建器
     */
    public static final class Builder {

        public Builder(DialProcessor dialProcessor) {
            this.dialProcessor = dialProcessor;
        }

        private DialProcessor dialProcessor;

        private Callback successCallback;

        private Callback failureCallback;

        private ExceptionCallback exceptionCallback;

        private DialResponseResolver dialResponseResolver;

        /**
         * 拨测成功回调
         *
         * @param successCallback
         * @return
         */
        public Builder success(Callback successCallback) {
            this.successCallback = successCallback;
            return this;
        }

        /**
         * 拨测失败回调
         *
         * @param failureCallback
         * @return
         */
        public Builder failure(Callback failureCallback) {
            this.failureCallback = failureCallback;
            return this;
        }

        /**
         * 拨测异常回调
         *
         * @param exceptionCallback
         * @return
         */
        public Builder exception(ExceptionCallback exceptionCallback) {
            this.exceptionCallback = exceptionCallback;
            return this;
        }

        /**
         * 响应解析器
         *
         * @param dialResponseResolver
         * @return
         */
        public Builder responseResolver(DialResponseResolver dialResponseResolver) {
            this.dialResponseResolver = dialResponseResolver;
            return this;
        }

        public DialBuilder build() {
            return new DialBuilder(this);
        }
    }
}
