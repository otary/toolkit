package cn.chenzw.toolkit.db.sql.exception;

/**
 * Sql解析异常
 *
 * @author chenzw
 * @since 1.0.3
 */
public class SqlParseException extends Exception {

    public SqlParseException(String message) {
        super(message);
    }

    public SqlParseException(String message, Throwable cause) {
        super(message, cause);
    }
}
