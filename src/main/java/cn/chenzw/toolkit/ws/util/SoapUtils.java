package cn.chenzw.toolkit.ws.util;

import cn.chenzw.toolkit.ws.exception.SoapException;

import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.Charset;

/**
 * SOAP工具类
 *
 * @author chenzw
 */
public final class SoapUtils {

    public static String toString(SOAPMessage message, Charset charset) {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        try {
            message.writeTo(out);
            return out.toString(charset.toString());
        } catch (SOAPException | IOException e) {
            throw new SoapException("SOAPMessage fail to transform to string.", e);
        }
    }
}
