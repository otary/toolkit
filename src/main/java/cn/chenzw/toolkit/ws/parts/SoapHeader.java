package cn.chenzw.toolkit.ws.parts;

import javax.xml.soap.SOAPElement;
import java.util.ArrayList;
import java.util.List;

/**
 * SOAP头部
 *
 * @author chenzw
 */
public final class SoapHeader {

    private List<SOAPElement> soapHeaderElements = new ArrayList<>();

    public SoapHeader(List<SOAPElement> soapHeaderElements) {
        this.soapHeaderElements = soapHeaderElements;
    }

    public SoapHeader(SOAPElement... soapHeaderElements) {
        for (SOAPElement soapHeaderElement : soapHeaderElements) {
            this.soapHeaderElements.add(soapHeaderElement);
        }
    }

    public List<SOAPElement> getSoapHeaderElements() {
        return soapHeaderElements;
    }

}
