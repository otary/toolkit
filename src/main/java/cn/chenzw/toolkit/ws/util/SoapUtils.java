package cn.chenzw.toolkit.ws.util;

import cn.chenzw.toolkit.ws.exception.SoapException;
import org.apache.commons.lang3.StringUtils;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;

import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.StringReader;
import java.nio.charset.Charset;

/**
 * SOAP工具类
 *
 * @author chenzw
 */
public final class SoapUtils {

    private static final String SOAP_BODY_XPATH = "/Envelope/*[local-name()='Body']";

    private static final String SOAP_CHILD_XPATH = "*/*";

    /**
     * SOAPMessage 转 String 字符串
     *
     * @param message
     * @param charset
     * @return
     */
    public static String toString(SOAPMessage message, Charset charset) {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        try {
            message.writeTo(out);
            return out.toString(charset.toString());
        } catch (SOAPException | IOException e) {
            throw new SoapException("SOAPMessage fail to transform to string.", e);
        }
    }

    /**
     * 获取内容节点
     *
     * @param soapResponse
     * @return
     */
    public static Node getContentNode(String soapResponse) {
        if (StringUtils.isEmpty(soapResponse)) {
            return null;
        }
        SAXReader reader = new SAXReader();
        Document doc = null;
        try {
            doc = reader.read(new StringReader(soapResponse));
        } catch (DocumentException e) {
            throw new SoapException("Parse soap response with error:[" + soapResponse + "].", e);
        }
        Element root = doc.getRootElement();
        if (root != null) {
            Node bodyNode = root.selectSingleNode(SOAP_BODY_XPATH);
            if (bodyNode != null) {
                return bodyNode.selectSingleNode(SOAP_CHILD_XPATH);
            }
        }
        return null;
    }

    /**
     * 获取内容XML文本
     *
     * @param soapResponse
     * @return
     */
    public static String getContent(String soapResponse) {
        Node contentNode = getContentNode(soapResponse);
        if (contentNode != null) {
            return contentNode.asXML();
        }
        return null;
    }


    public static <T> T parse(String soapResponse) {
        getContent(soapResponse);
        return null;
    }
}
