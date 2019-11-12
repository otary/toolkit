package cn.chenzw.toolkit.ws.constants;

import javax.xml.soap.SOAPConstants;

/**
 * SOAP版本
 *
 * @author chenzw
 */
public enum SoapProtocol {


    /**
     * SOAP 1.1协议
     */
    SOAP_1_1(SOAPConstants.SOAP_1_1_PROTOCOL),
    /**
     * SOAP 1.2协议
     */
    SOAP_1_2(SOAPConstants.SOAP_1_2_PROTOCOL);


    SoapProtocol(String value) {
        this.value = value;
    }

    private String value;

    public String getValue() {
        return this.value;
    }

}
