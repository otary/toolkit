package cn.chenzw.toolkit.ws.parts;

import org.apache.commons.lang3.StringUtils;

import javax.xml.namespace.QName;

/**
 * SOAP头部
 *
 * @author chenzw
 */
public final class SoapHeader {

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

    public SoapHeader(QName qName) {
        this.qName = qName;
    }

    public SoapHeader(QName qName, String actorURI, String roleURI, Boolean mustUnderstand, Boolean relay) {

        if (qName != null) {
            this.qName = qName;
        }

        if (StringUtils.isNotEmpty(actorURI)) {
            this.actorURI = actorURI;
        }

        if (StringUtils.isNotEmpty(roleURI)) {
            this.roleURI = roleURI;
        }

        if (mustUnderstand != null) {
            this.mustUnderstand = mustUnderstand;
        }

        if (relay != null) {
            this.relay = relay;
        }
    }

    public QName getqName() {
        return qName;
    }

    public String getActorURI() {
        return actorURI;
    }

    public String getRoleURI() {
        return roleURI;
    }

    public Boolean getMustUnderstand() {
        return mustUnderstand;
    }

    public Boolean getRelay() {
        return relay;
    }
}
