package cn.chenzw.toolkit.core.jwt;

import cn.chenzw.toolkit.core.jwt.entity.JWTEntity;
import cn.chenzw.toolkit.core.util.JSONKit;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.apache.commons.lang3.StringUtils;

import java.util.Base64;
import java.util.Map;

/**
 * Jwt简易工具类
 *
 * @author chenzw
 * @since 1.0.3
 */
public final class JWTTokenKit {

    private static String BEARER_TOKEN_PREFIX = "Bearer ";

    /**
     * 是否Jwt Token
     *
     * @param token
     * @return
     */
    public static boolean isJwtToken(String token) {
        int count = StringUtils.countMatches(token, ".");
        return count == 2;
    }

    /**
     * 无密钥解析Token
     *
     * @param token
     * @return
     */
    public static JWTEntity parseWithoutKey(String token) {
        String[] tokenSegments = token.split("\\.");

        String tokenHeader = tokenSegments[0];
        String tokenPayload = tokenSegments[1];

        JWTEntity jwtEntity = new JWTEntity();
        String tokenHeaderPlaintext = new String(
                Base64.getMimeDecoder().decode(tokenHeader)
        );
        try {
            jwtEntity.setHeader(
                    JSONKit.readValue(tokenHeaderPlaintext, Map.class)
            );
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        String tokenPayloadPlaintext = new String(
                Base64.getMimeDecoder().decode(tokenPayload)
        );
        try {
            jwtEntity.setPayload(
                    JSONKit.readValue(tokenPayloadPlaintext, Map.class)
            );
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return jwtEntity;
    }


    /**
     * 是否 Bearer Token
     *
     * @param token
     * @return
     */
    public boolean isBearerToken(String token) {
        return StringUtils.startsWith(token, BEARER_TOKEN_PREFIX);
    }


}
