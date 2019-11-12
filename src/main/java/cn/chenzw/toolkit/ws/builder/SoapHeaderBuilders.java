package cn.chenzw.toolkit.ws.builder;

import cn.chenzw.toolkit.ws.parts.SoapHeader;

import javax.xml.namespace.QName;

/**
 * SOAP头部构建器
 *
 * @author chenzw
 */
public final class SoapHeaderBuilders {

    /**
     * 头头标签名
     */
    private QName qName;

    /**
     * 中间的消息接收者
     */
    private String actorURI;

    private String roleURI;

    /**
     * 标题项对于接收者来说是强制的还是可选的
     */
    private Boolean mustUnderstand;

    private Boolean relay;

    private SoapHeaderBuilders() {
    }

    public static SoapHeaderBuilders create() {
        return new SoapHeaderBuilders();
    }

    public SoapHeaderBuilders qName(QName qName) {
        this.qName = qName;
        return this;
    }

    public SoapHeaderBuilders actorURI(String actorURI) {
        this.actorURI = actorURI;
        return this;
    }

    public SoapHeaderBuilders roleURI(String roleURI) {
        this.roleURI = roleURI;
        return this;
    }

    public SoapHeaderBuilders mustUnderstand(Boolean mustUnderstand) {
        this.mustUnderstand = mustUnderstand;
        return this;
    }

    public SoapHeaderBuilders relay(Boolean relay) {
        this.relay = relay;
        return this;
    }

    public SoapHeader build() {
        return new SoapHeader(this.qName, this.actorURI, this.roleURI, this.mustUnderstand, this.relay);
    }
}
