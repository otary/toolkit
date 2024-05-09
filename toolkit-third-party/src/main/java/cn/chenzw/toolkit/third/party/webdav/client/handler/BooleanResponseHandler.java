package cn.chenzw.toolkit.third.party.webdav.client.handler;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.jackrabbit.webdav.client.methods.BaseDavRequest;

import java.io.IOException;

/**
 * @author chenzw
 */
public class BooleanResponseHandler implements ResponseHandler<Boolean> {

    private BaseDavRequest baseDavRequest;

    public BooleanResponseHandler(BaseDavRequest baseDavRequest) {
        this.baseDavRequest = baseDavRequest;
    }

    @Override
    public Boolean handleResponse(HttpResponse response) throws ClientProtocolException, IOException {
        return baseDavRequest.succeeded(response);
    }
}
