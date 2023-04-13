package cn.chenzw.toolkit.spring.encrypt.resolver;

import cn.chenzw.toolkit.core.codec.Base58Kit;
import cn.chenzw.toolkit.core.util.JSONKit;
import cn.chenzw.toolkit.spring.encrypt.annotation.ResponseBodyBase58;
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
import java.nio.charset.StandardCharsets;

/**
 * 请求内容Base58编码
 *
 * @author chenzw
 */
public class RequestBodyBase58ArgumentResolver implements HandlerMethodArgumentResolver {

    private Charset defCharset = StandardCharsets.UTF_8;

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.hasParameterAnnotation(ResponseBodyBase58.class);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        HttpServletRequest request = webRequest.getNativeRequest(HttpServletRequest.class);

        HttpInputMessage message = new ServletServerHttpRequest(request);
        // 获取头部的ContentType编码
        Charset charset = this.getContentCharset(message.getHeaders().getContentType());
        // 转换编码
        String body = FileCopyUtils.copyToString(
                new InputStreamReader(request.getInputStream(), charset)
        );

        //如果是GET请求，body应该是空，重新获取
        if (StringUtils.isEmpty(body)) {
            body = request.getQueryString();
        }

        byte[] content = null;
        if (body != null && body.length() > 0) {
            if (Base58Kit.isBase58(body)) {
                content = Base58Kit.decode(body);
            } else {
                content = body.getBytes(charset);
            }
        }
        return (content == null) ? null : JSONKit.readValue(content, parameter.getParameterType());
    }

    // 获取charset
    public Charset getContentCharset(MediaType mediaType) {
        if (mediaType != null && mediaType.getCharset() != null) {
            return mediaType.getCharset();
        } else {
            return this.defCharset;
        }
    }
}

