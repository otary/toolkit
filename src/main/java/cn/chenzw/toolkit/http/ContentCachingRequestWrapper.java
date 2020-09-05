package cn.chenzw.toolkit.http;


import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.*;
import java.util.Collections;
import java.util.Enumeration;
import java.util.Map;

/**
 * request内容缓存
 *
 * @author chenzw
 */
public class ContentCachingRequestWrapper extends HttpServletRequestWrapper {

    private static final Logger logger = LoggerFactory.getLogger(ContentCachingRequestWrapper.class);

    private ByteArrayOutputStream baos = new ByteArrayOutputStream();

    private Map<String, String[]> parameterMap;

    public ContentCachingRequestWrapper(HttpServletRequest request) {
        super(request);

        // Fix getParameter() 和 getInputStream() 互斥问题
        this.parameterMap = request.getParameterMap();

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

    @Override
    public BufferedReader getReader() throws IOException {
        return new BufferedReader(new InputStreamReader(getInputStream()));
    }

    @Override
    public String getParameter(String name) {
        String[] pvs = getParameterValues(name);
        if (pvs == null) {
            return null;
        }
        return pvs[0];
    }

    @Override
    public Enumeration getParameterNames() {
        return Collections.enumeration(parameterMap.keySet());
    }

    @Override
    public String[] getParameterValues(String name) {
        String[] vs = parameterMap.get(name);
        if (vs == null || vs.length < 1)
            return null;
        return vs;
    }


}
