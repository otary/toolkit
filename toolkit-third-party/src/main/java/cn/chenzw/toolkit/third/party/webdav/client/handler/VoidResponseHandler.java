package cn.chenzw.toolkit.third.party.webdav.client.handler;

import cn.chenzw.toolkit.third.party.webdav.exception.WebdavException;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;

import java.io.IOException;

/**
 * @author chenzw
 */
public class VoidResponseHandler implements ResponseHandler<Void> {

    @Override
    public Void handleResponse(HttpResponse response) throws ClientProtocolException, IOException {
        StatusLine statusLine = response.getStatusLine();
        int statusCode = statusLine.getStatusCode();
        if (statusCode != HttpStatus.SC_OK) {
            throw new WebdavException("error!", statusCode, response.getStatusLine().getReasonPhrase());
        }
        return null;
    }
}
