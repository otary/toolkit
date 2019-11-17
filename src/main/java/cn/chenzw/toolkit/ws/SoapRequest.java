package cn.chenzw.toolkit.ws;


import cn.chenzw.toolkit.ws.constants.SoapProtocol;
import cn.chenzw.toolkit.ws.exception.SoapException;
import cn.chenzw.toolkit.ws.parts.SoapBody;
import cn.chenzw.toolkit.ws.parts.SoapHeader;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;

import javax.xml.namespace.QName;
import javax.xml.soap.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Map;

/**
 * SOAP请求
 *
 * @author chenzw
 */
public class SoapRequest implements Cloneable {

    /**
     * webservice url地址
     */
    private String url;


    /**
     * 编码
     */
    private Charset charset = StandardCharsets.UTF_8;


    /**
     * SOAP消息
     */
    private SOAPMessage message;

    /**
     * soap版本
     */
    private SoapProtocol soapProtocol;

    public String getUrl() {
        return url;
    }

    public Charset getCharset() {
        return charset;
    }

    public SOAPMessage getMessage() {
        return message;
    }


    private SoapRequest(Builder builder) {
        if (StringUtils.isEmpty(builder.url)) {
            throw new IllegalArgumentException("Parameter `url` is empty!");
        }

        this.url = builder.url;

        this.soapProtocol = builder.soapProtocol;

        if (builder.charset != null) {
            this.charset = builder.charset;
        }

        // 初始化
        try {
            MessageFactory factory = MessageFactory.newInstance(this.soapProtocol.getValue());
            this.message = factory.createMessage();

            this.initSoapHeader(message, builder.soapHeader);
            this.initSoapBody(message, builder.soapBody);

            this.message.setProperty(SOAPMessage.CHARACTER_SET_ENCODING, this.charset.toString());
            this.message.setProperty(SOAPMessage.WRITE_XML_DECLARATION, "true");
        } catch (SOAPException e) {
            throw new SoapException("Initialize SOAPMessage width error.", e);
        }

    }


    private void initSoapHeader(SOAPMessage message, SoapHeader soapHeader) throws SOAPException {
        SOAPHeader soapHeaderEntity = message.getSOAPHeader();

        if (soapHeader != null) {
            QName qName = soapHeader.getqName();
            SOAPHeaderElement soapHeaderEle = soapHeaderEntity.addHeaderElement(qName);

            String roleURI = soapHeader.getRoleURI();
            if (StringUtils.isNotEmpty(roleURI)) {
                soapHeaderEle.setRole(roleURI);
            }

            Boolean relay = soapHeader.getRelay();
            if (relay != null) {
                soapHeaderEle.setRelay(relay);
            }

            String actorURI = soapHeader.getActorURI();
            if (StringUtils.isNotEmpty(actorURI)) {
                soapHeaderEle.setActor(actorURI);
            }

            Boolean mustUnderstand = soapHeader.getMustUnderstand();
            if (mustUnderstand != null) {
                soapHeaderEle.setMustUnderstand(mustUnderstand);
            }

        }
    }

    private void initSoapBody(SOAPMessage message, SoapBody soapBody) throws SOAPException {
        if (soapBody != null) {
            SOAPBodyElement soapBodyElement;
            if (StringUtils.contains(soapBody.getMethodName(), ":")) {
                String[] methodNames = StringUtils.split(soapBody.getMethodName(), ":");
                soapBodyElement = message.getSOAPBody().addBodyElement(new QName(ObjectUtils.defaultIfNull(soapBody.getNamespaceURI(), ""), methodNames[1], methodNames[0]));
            } else {
                soapBodyElement = message.getSOAPBody().addBodyElement(new QName(ObjectUtils.defaultIfNull(soapBody.getNamespaceURI(), ""), soapBody.getMethodName()));
            }

            Map<String, Object> params = soapBody.getParams();
            for (Map.Entry<String, Object> paramsEntry : params.entrySet()) {
                addParam(soapBodyElement, paramsEntry.getKey(), paramsEntry.getValue(), soapBody.getUseMethodPrefix());
            }
        }
    }

    private void addParam(SOAPElement soapBodyElement, String name, Object value, Boolean useMethodPrefix) throws SOAPException {

        SOAPElement childElement;
        String prefix = (useMethodPrefix != null && useMethodPrefix) ? soapBodyElement.getPrefix() : "";

        if (StringUtils.isNotEmpty(prefix)) {
            childElement = soapBodyElement.addChildElement(name, prefix);
        } else {
            childElement = soapBodyElement.addChildElement(name);
        }

        if (value != null) {
            if (value instanceof SOAPElement) {
                // 单个子节点
                soapBodyElement.addChildElement((SOAPElement) value);
            } else if (value instanceof Map) {
                // 多个子节点
                Map<String, Object> childParams = (Map) value;
                for (Map.Entry<String, Object> childParamsEntry : childParams.entrySet()) {
                    addParam(childElement, childParamsEntry.getKey(), childParamsEntry.getValue(), useMethodPrefix);
                }
            } else {
                // 单个值
                childElement.setValue(value.toString());
            }
        }
    }

    public static Builder create() {
        return new Builder();
    }


    public static final class Builder {

        /**
         * webservice url地址
         */
        private String url;

        /**
         * 编码
         */
        private Charset charset;

        /**
         * SOAP头部
         */
        private SoapHeader soapHeader;

        /**
         * SOAP消息体
         */
        private SoapBody soapBody;


        /**
         * soap版本
         */
        private SoapProtocol soapProtocol;


        public Builder() {
            this.soapProtocol = SoapProtocol.SOAP_1_1;
        }

        Builder(SoapRequest soapRequest) {
            this.url = soapRequest.url;
            this.charset = soapRequest.charset;
        }


        public Builder url(String url) {
            this.url = url;
            return this;
        }


        /**
         * 设置SOAP头部
         *
         * @param soapHeader
         * @return
         */
        public Builder header(SoapHeader soapHeader) {
            this.soapHeader = soapHeader;
            return this;
        }

        /**
         * 设置SOAP消息体
         *
         * @param soapBody
         * @return
         */
        public Builder body(SoapBody soapBody) {
            this.soapBody = soapBody;
            return this;
        }

        /**
         * 设置编码
         *
         * @param charset
         * @return
         */
        public Builder charset(Charset charset) {
            this.charset = charset;
            return this;
        }

        /**
         * 设置SOAP版本
         *
         * @param soapProtocol
         * @return
         */
        public Builder protocol(SoapProtocol soapProtocol) {
            this.soapProtocol = soapProtocol;
            return this;
        }


        public SoapRequest build() {
            return new SoapRequest(this);
        }
    }


}
