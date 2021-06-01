package cn.chenzw.toolkit.authentication.jwt;

import cn.chenzw.toolkit.authentication.jwt.entity.JwtEntity;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.StringUtils;

import java.util.Base64;
import java.util.Map;

/**
 * Jwt简易工具类
 *
 * @author chenzw
 * @since 1.0.3
 */
public final class JwtTokenUtils {

    private static String BEARER_TOKEN_PREFIX = "Bearer ";

    private static ObjectMapper objectMapper = new ObjectMapper();

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
    public static JwtEntity parseWithoutKey(String token) {
        String[] tokenSegments = token.split("\\.");

        String tokenHeader = tokenSegments[0];
        String tokenPlayload = tokenSegments[1];

        JwtEntity jwtEntity = new JwtEntity();
        String tokenHeaderPlaintext = new String(Base64.getMimeDecoder().decode(tokenHeader));
        try {
            jwtEntity.setHeader(objectMapper.readValue(tokenHeaderPlaintext, Map.class));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        String tokenPlayloadPlaintext = new String(Base64.getMimeDecoder().decode(tokenPlayload));
        try {
            jwtEntity.setPlayload(objectMapper.readValue(tokenPlayloadPlaintext, Map.class));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return jwtEntity;
    }


    /**
     * 是否Bearer Token
     *
     * @param token
     * @return
     */
    public boolean isBearerToken(String token) {
        return StringUtils.startsWith(token, BEARER_TOKEN_PREFIX);
    }


}
