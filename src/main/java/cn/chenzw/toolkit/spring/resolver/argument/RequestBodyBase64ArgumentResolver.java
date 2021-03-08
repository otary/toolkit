package cn.chenzw.toolkit.spring.resolver.argument;

import cn.chenzw.toolkit.spring.resolver.argument.annotation.RequestBodyBase64;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.StringUtils;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpServletRequest;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

/**
 * Base64自动转码
 *
 * @author chenzw
 * @since 1.0.3
 */
public class RequestBodyBase64ArgumentResolver implements HandlerMethodArgumentResolver {

    private ObjectMapper objectMapper = new ObjectMapper();

    private Charset defaultCharset = Charset.forName("UTF-8");

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.hasParameterAnnotation(RequestBodyBase64.class);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        HttpServletRequest request = webRequest.getNativeRequest(HttpServletRequest.class);

        HttpInputMessage message = new ServletServerHttpRequest(request);
        // 获取头部的ContentType编码
        Charset charset = this.getContentCharset(message.getHeaders().getContentType());

        // 转换编码
        String body = FileCopyUtils.copyToString(new InputStreamReader(request.getInputStream(), charset));

        //如果是GET请求，body应该是空，重新获取
        if (StringUtils.isEmpty(body)) {
            body = request.getQueryString();
        }

        byte[] content = null;
        if (body != null && body.length() > 0) {
            if (Base64.isBase64(body)) {
                content = Base64.decodeBase64(body);
            } else {
                content = body.getBytes(charset);
            }
        }
        return (content == null) ? null : objectMapper.readValue(content, parameter.getParameterType());
    }

    // 获取charset
    public Charset getContentCharset(MediaType mediaType) {
        if (mediaType != null && mediaType.getCharset() != null) {
            return mediaType.getCharset();
        } else {
            return this.defaultCharset;
        }
    }
}
