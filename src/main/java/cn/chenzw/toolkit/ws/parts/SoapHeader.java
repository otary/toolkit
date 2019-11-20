package cn.chenzw.toolkit.ws.parts;

import java.util.ArrayList;
import java.util.List;

/**
 * SOAP头部
 *
 * @author chenzw
 */
public final class SoapHeader {

    private List<SoapHeaderElement> soapHeaderElements = new ArrayList<>();

    public SoapHeader(List<SoapHeaderElement> soapHeaderElements) {
        this.soapHeaderElements = soapHeaderElements;
    }

    public List<SoapHeaderElement> getSoapHeaderElements() {
        return soapHeaderElements;
    }

    public void setSoapHeaderElements(List<SoapHeaderElement> soapHeaderElements) {
        this.soapHeaderElements = soapHeaderElements;
    }

    public void addSoapHeaderElements(SoapHeaderElement soapHeaderElement) {
        this.soapHeaderElements.add(soapHeaderElement);
    }
}
