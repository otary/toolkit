package cn.chenzw.toolkit.core.exception;

/**
 * 转化异常
 *
 * @author chenzw
 * @since 1.0.3
 */
public class ConvertException extends RuntimeException {

    public ConvertException() {
        super();
    }

    public ConvertException(String message) {
        super(message);
    }

    public ConvertException(String message, Throwable cause) {
        super(message, cause);
    }

    public ConvertException(Throwable cause) {
        super(cause);
    }

    protected ConvertException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
