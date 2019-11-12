package cn.chenzw.toolkit.ws.exception;

/**
 * SOAP异常
 *
 * @author chenzw
 */
public class SoapException extends RuntimeException {

    public SoapException(String message) {
        super(message);
    }

    public SoapException(String message, Throwable cause) {
        super(message, cause);
    }

    public SoapException(Throwable cause) {
        super(cause);
    }
}
