package cn.chenzw.toolkit.ws.builder;

import cn.chenzw.toolkit.ws.parts.SoapHeader;
import cn.chenzw.toolkit.ws.parts.SoapHeaderElement;

import javax.xml.namespace.QName;
import java.util.ArrayList;
import java.util.List;

/**
 * SOAP头部构建器
 *
 * @author chenzw
 */
public final class SoapHeaderBuilders {

    private List<SoapHeaderElement> soapHeaderElements = new ArrayList<>();


    private SoapHeaderBuilders() {
    }

    public static SoapHeaderBuilders create() {
        return new SoapHeaderBuilders();
    }

    public SoapHeaderBuilders qName(QName qName) {
        this.soapHeaderElements.add(new SoapHeaderElement(qName));
        return this;
    }

    public SoapHeader build() {
        return new SoapHeader(soapHeaderElements);
    }
}
