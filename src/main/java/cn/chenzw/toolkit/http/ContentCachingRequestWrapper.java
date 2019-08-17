package cn.chenzw.toolkit.http;


import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * request内容缓存
 *
 * @author chenzw
 */
public class ContentCachingRequestWrapper extends HttpServletRequestWrapper {

    private static final Logger logger = LoggerFactory.getLogger(ContentCachingRequestWrapper.class);

    private ByteArrayOutputStream baos = new ByteArrayOutputStream();

    public ContentCachingRequestWrapper(HttpServletRequest request) {
        super(request);

        try {
            IOUtils.copy(request.getInputStream(), baos);
        } catch (IOException e) {
            logger.error("HttpRequest caching with error!", e);
        }
    }

    @Override
    public ServletInputStream getInputStream() {
        ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
        return new ServletInputStream() {
            @Override
            public boolean isFinished() {
                return false;
            }

            @Override
            public boolean isReady() {
                return false;
            }

            @Override
            public void setReadListener(ReadListener readListener) {
                // Nothing
            }

            @Override
            public int read() {
                return bais.read();
            }
        };
    }
}
