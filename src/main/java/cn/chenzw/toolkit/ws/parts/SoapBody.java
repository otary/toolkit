package cn.chenzw.toolkit.ws.parts;

import java.util.Map;

public final class SoapBody {

    private String methodName;

    private String namespaceURI;

    private Boolean useMethodPrefix;

    private Map<String, Object> params ;

    public SoapBody(String methodName, String namespaceURI) {
        this.methodName = methodName;
        this.namespaceURI = namespaceURI;
    }

    public SoapBody(String methodName, String namespaceURI, Boolean useMethodPrefix, Map<String, Object> params) {
        this.methodName = methodName;
        this.namespaceURI = namespaceURI;
        this.useMethodPrefix = useMethodPrefix;
        this.params = params;
    }

    public String getMethodName() {
        return methodName;
    }

    public String getNamespaceURI() {
        return namespaceURI;
    }

    public Boolean getUseMethodPrefix() {
        return useMethodPrefix;
    }

    public Map<String, Object> getParams() {
        return params;
    }
}
