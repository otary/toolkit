package cn.chenzw.toolkit.third.party.webdav.client.handler;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;

import java.io.IOException;

/**
 * @author chenzw
 */
public class CheckConnectableResponseHandler implements ResponseHandler<Boolean> {

    @Override
    public Boolean handleResponse(HttpResponse response) throws ClientProtocolException, IOException {
        StatusLine statusLine = response.getStatusLine();
        return statusLine.getStatusCode() == HttpStatus.SC_OK;
    }
}
