package cn.chenzw.toolkit.third.party.webdav.exception;

/**
 * @author chenzw
 */
public class WebdavException extends RuntimeException {

    private String reasonPhrase;

    private int code;

    public WebdavException(String message, int code, String reasonPhrase) {
        super(message);
        this.code = code;
        this.reasonPhrase = reasonPhrase;
    }

}
