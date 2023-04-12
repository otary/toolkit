package cn.chenzw.toolkit.spring.encrypt.advice;

import cn.chenzw.toolkit.core.util.JsonKit;
import cn.chenzw.toolkit.spring.encrypt.annotation.ResponseBodyBase58;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import java.io.UnsupportedEncodingException;
import java.util.Base64;

/**
 * 响应对象Base58编码
 *
 * @author chenzw
 * @since 1.0.3
 */
public class ResponseBodyBase58Advice implements ResponseBodyAdvice<Object> {

    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        return returnType.hasMethodAnnotation(ResponseBodyBase58.class) || returnType.hasParameterAnnotation(ResponseBodyBase58.class);
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType, Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
        String jsonStr;
        try {
            jsonStr = JsonKit.writeValueAsString(body);
        } catch (JsonProcessingException e) {
            return body;
        }
        ResponseBodyBase58 annotation = getAnnotation(returnType);
        try {
            return new String(Base64.getEncoder().encode(jsonStr.getBytes(annotation.charset())), annotation.charset());
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }


    private ResponseBodyBase58 getAnnotation(MethodParameter returnType) {
        if (returnType.hasMethodAnnotation(ResponseBodyBase58.class)) {
            return returnType.getMethodAnnotation(ResponseBodyBase58.class);
        }
        if (returnType.hasParameterAnnotation(ResponseBodyBase58.class)) {
            return returnType.getParameterAnnotation(ResponseBodyBase58.class);
        }
        return null;
    }
}
