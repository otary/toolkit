package cn.chenzw.toolkit.websocket.util;

import cn.chenzw.toolkit.commons.ReflectExtUtils;
import cn.chenzw.toolkit.commons.exception.FieldNotExistException;

import javax.websocket.RemoteEndpoint;
import javax.websocket.Session;
import javax.websocket.server.HandshakeRequest;
import java.net.InetSocketAddress;
import java.util.List;
import java.util.Map;

/**
 * WebSocket工具类
 *
 * @author chenzw
 * @since 1.0.3
 */
public final class WebSocketUtils {

    private static final String[] CLIENT_IP_HEADER_NAMES = new String[]{
            "x-forwarded-for", "Proxy-Client-realAddress", "WL-Proxy-Client-realAddress"
    };

    /**
     * 获取客户端IP
     *
     * @param session
     * @return
     */
    public static final InetSocketAddress getRemoteAddress(Session session) throws FieldNotExistException, IllegalAccessException {
        if (session == null) {
            return null;
        }
        RemoteEndpoint.Async remote = session.getAsyncRemote();
        // for Tomcat 8.0.x
        // Object nestedFieldValue = ReflectExtUtils.getNestedFieldValue(remote, "base#sos#socketWrapper#socket#sc#remoteAddress");
        // for Tomcat 8.5+
        Object nestedFieldValue = ReflectExtUtils.getNestedFieldValue(remote, "base#socketWrapper#socket#sc#remoteAddress");
        if (nestedFieldValue == null) {
            return null;
        }
        if (nestedFieldValue instanceof InetSocketAddress) {
            return (InetSocketAddress) nestedFieldValue;
        }
        return null;
    }

    /**
     * 从头部获取客户端IP
     *
     * @param request
     * @return
     */
    public static String getHeaderRemoteIp(HandshakeRequest request) {
        Map<String, List<String>> headers = request.getHeaders();
        for (String headerName : CLIENT_IP_HEADER_NAMES) {
            List<String> values = headers.get(headerName);
            if (values != null && values.size() > 0) {
                return values.get(0);
            }
        }
        return "";
    }
}
