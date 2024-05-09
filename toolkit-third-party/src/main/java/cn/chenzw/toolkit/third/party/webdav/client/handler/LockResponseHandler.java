package cn.chenzw.toolkit.third.party.webdav.client.handler;

import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;

import java.io.IOException;

/**
 * @author chenzw
 */
public class LockResponseHandler implements ResponseHandler<String> {


    @Override
    public String handleResponse(HttpResponse response) throws ClientProtocolException, IOException {
        StatusLine statusLine = response.getStatusLine();

        return null;
    }
}
