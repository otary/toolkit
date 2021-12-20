package cn.chenzw.toolkit.spring.resolver.argument;

import cn.chenzw.toolkit.spring.resolver.argument.annotation.ResponseBodyBase64;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import java.nio.charset.Charset;
import java.util.Base64;

/**
 * 响应对象Base64编码
 *
 * @author chenzw
 * @since 1.0.3
 */
public class ResponseBodyBase64ArgumentResolver implements ResponseBodyAdvice<Object> {

    private ObjectMapper mapper = new ObjectMapper();

    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        return returnType.hasMethodAnnotation(ResponseBodyBase64.class) || returnType.hasParameterAnnotation(ResponseBodyBase64.class);
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType, Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
        String jsonStr;
        try {
            jsonStr = mapper.writeValueAsString(body);
        } catch (JsonProcessingException e) {
            return body;
        }

        return new String(Base64.getEncoder().encode(jsonStr.getBytes()));
    }

}
