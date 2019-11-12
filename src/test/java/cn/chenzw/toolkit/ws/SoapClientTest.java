package cn.chenzw.toolkit.ws;

import cn.chenzw.toolkit.ws.builder.SoapBodyBuilders;
import okhttp3.Response;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.io.IOException;


@RunWith(JUnit4.class)
public class SoapClientTest {

    @Test
    public void testSoapOkHttpClient() throws IOException {
        SoapOkHttpClient soapOkHttpClient = SoapOkHttpClient.create().build();


        SoapRequest soapRequest = SoapRequest.create()
                .url("http://www.webxml.com.cn/WebServices/ValidateEmailWebService.asmx?wsdl")
                .body(SoapBodyBuilders.create()
                        .methodName("ValidateEmailAddress")
                        .namespaceURI("http://WebXml.com.cn/")
                        .addMethodParameter("theEmail", "656469722@qq.com")
                        .build())
                .build();

        Response response = soapOkHttpClient.newCall(soapRequest).execute();

        System.out.println(response.body().string());
    }
}
