package cn.chenzw.toolkit.ws;

import cn.chenzw.toolkit.ws.builder.SoapBodyBuilders;
import cn.chenzw.toolkit.ws.builder.SoapHeaderBuilders;
import cn.chenzw.toolkit.ws.inteceptor.SoapRequestInteceptor;
import cn.chenzw.toolkit.ws.util.SoapUtils;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import javax.xml.XMLConstants;
import javax.xml.namespace.QName;
import java.io.IOException;


/**
 * @author chenzw
 */
@RunWith(JUnit4.class)
public class SoapOkHttpClientTest {

    /**
     * 同步请求示例
     *
     * @throws IOException
     */
    @Test
    public void testSoapOkHttpClient() throws IOException {
        SoapOkHttpClient soapOkHttpClient = SoapOkHttpClient.create()
                .addInterceptor(new SoapRequestInteceptor())  // 添加拦截器
                .build();

        // 多次请求
        for (int i = 0; i < 3; i++) {
            SoapRequest soapRequest = SoapRequest.create()
                    .url("http://www.webxml.com.cn/WebServices/ValidateEmailWebService.asmx?wsdl")
                   .header(SoapHeaderBuilders.create()
                           .qName(new QName(XMLConstants.XML_NS_URI, "", XMLConstants.XML_NS_PREFIX))
                           .build())
                    .body(SoapBodyBuilders.create()
                            .methodName("ValidateEmailAddress")
                            .namespaceURI("http://WebXml.com.cn/")
                            .addMethodParameter("theEmail", "<![CDATA[<root><text>11</text></root>]]>")
                            .build())
                    .build();

            Response response = soapOkHttpClient.newCall(soapRequest).execute();

            if (response.isSuccessful()) {
                String content = SoapUtils.getContent(response.body().string());

                Assert.assertEquals("<ValidateEmailAddressResult xmlns=\"http://WebXml.com.cn/\">1</ValidateEmailAddressResult>", content);
            } else {
                System.out.println("异常:" + response.message());
            }

        }
    }


    /**
     * 异步请求示例
     */
    @Test
    public void testSoapOkHttpClientAsync() {
        SoapOkHttpClient soapOkHttpClient = SoapOkHttpClient.create()
                .addInterceptor(new SoapRequestInteceptor())  // 添加拦截器
                .build();

        // 多次请求
        for (int i = 0; i < 3; i++) {
            SoapRequest soapRequest = SoapRequest.create()
                    .url("http://www.webxml.com.cn/WebServices/ValidateEmailWebService.asmx?wsdl")
                    .body(SoapBodyBuilders.create()
                            .methodName("ValidateEmailAddress")
                            .namespaceURI("http://WebXml.com.cn/")
                            .addMethodParameter("theEmail", "656469722@qq.com")
                            .build())
                    .build();

            soapOkHttpClient.newCall(soapRequest).enqueue(new Callback() {

                @Override
                public void onFailure(Call call, IOException e) {
                    System.out.println("异常:" + e.getMessage());
                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    String content = SoapUtils.getContent(response.body().string());

                    Assert.assertEquals("<ValidateEmailAddressResult xmlns=\"http://WebXml.com.cn/\">1</ValidateEmailAddressResult>", content);

                }
            });

        }

    }


}
