package cn.chenzw.toolkit.ws.builder;

import cn.chenzw.toolkit.ws.parts.SoapBody;

import java.util.HashMap;
import java.util.Map;

public final class SoapBodyBuilders {

    private String methodName;

    private String namespaceURI;

    private Boolean useMethodPrefix;

    private Map<String, Object> params = new HashMap<>();

    private SoapBodyBuilders() {
    }

    public static SoapBodyBuilders create() {
        return new SoapBodyBuilders();
    }

    public SoapBodyBuilders methodName(String methodName) {
        this.methodName = methodName;
        return this;
    }

    public SoapBodyBuilders namespaceURI(String namespaceURI) {
        this.namespaceURI = namespaceURI;
        return this;
    }

    public SoapBodyBuilders useMethodPrefix(Boolean useMethodPrefix) {
        this.useMethodPrefix = useMethodPrefix;
        return this;
    }

    public SoapBodyBuilders addMethodParameter(String name, Object value) {
        params.put(name, value);
        return this;
    }

    public SoapBody build() {
        return new SoapBody(methodName, namespaceURI, useMethodPrefix, params);
    }

}
