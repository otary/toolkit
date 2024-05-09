package cn.chenzw.toolkit.third.party.webdav.client.handler;

import cn.chenzw.toolkit.core.io.IOKit;
import cn.chenzw.toolkit.third.party.webdav.exception.WebdavException;
import org.apache.commons.io.IOUtils;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author chenzw
 */
public class GetContentResponseHandler implements ResponseHandler<InputStream> {

    @Override
    public InputStream handleResponse(HttpResponse response) throws ClientProtocolException, IOException {
        StatusLine statusLine = response.getStatusLine();
        int statusCode = statusLine.getStatusCode();
        if (statusCode != HttpStatus.SC_OK) {
            throw new WebdavException("Get content error!", statusCode, response.getStatusLine().getReasonPhrase());
        }
        return IOKit.copy(new BufferedInputStream(response.getEntity().getContent()));
    }
}
