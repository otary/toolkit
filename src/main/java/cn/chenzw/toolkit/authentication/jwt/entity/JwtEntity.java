package cn.chenzw.toolkit.authentication.jwt.entity;

import lombok.Data;

import java.util.Map;

/**
 * @author chenzw
 * @since 1.0.3
 */
@Data
public class JwtEntity {

    /**
     * 头部
     * <p>
     * {
     * "alg": "SHA256",
     * "typ": "JWT"
     * }
     * </p>
     */
    private Map<String, Object> header;

    /**
     * 负载
     * <p>
     * {
     * "sub": "1234546",
     * "name": "John Doe",
     * "admin": true
     * }
     * </p>
     */
    private Map<String, Object> playload;


}
