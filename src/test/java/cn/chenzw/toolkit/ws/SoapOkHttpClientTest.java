package cn.chenzw.toolkit.ws;

import cn.chenzw.toolkit.ws.builder.SoapBodyBuilders;
import cn.chenzw.toolkit.ws.inteceptor.SoapRequestInteceptor;
import cn.chenzw.toolkit.ws.parts.SoapHeader;
import cn.chenzw.toolkit.ws.util.SoapUtils;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;
import org.dom4j.Node;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import javax.xml.XMLConstants;
import javax.xml.namespace.QName;
import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPFactory;
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
    @Ignore
    public void testSoapOkHttpClient() throws IOException, SOAPException {
        SoapOkHttpClient soapOkHttpClient = SoapOkHttpClient.create()
                .addInterceptor(new SoapRequestInteceptor())  // 添加拦截器
                .build();

        // 多次请求
        for (int i = 0; i < 3; i++) {

            SOAPFactory soapFactory = SOAPFactory.newInstance();
            SOAPElement esbElement = soapFactory.createElement(new QName(XMLConstants.XML_NS_URI, "Esb", XMLConstants.XML_NS_PREFIX));
            SOAPElement routeElement = esbElement.addChildElement("Route");
            routeElement.addChildElement("Sender").setValue("10.1001");
            routeElement.addChildElement("Time").setValue("");
            routeElement.addChildElement("ServCode").setValue("");
            routeElement.addChildElement("MsgId").setValue("");
            routeElement.addChildElement("EsbId").setValue("33");
            routeElement.addChildElement("MsgType");
            routeElement.addChildElement("TransId");
            routeElement.addChildElement("Version");
            routeElement.addChildElement("AuthCode");
            routeElement.addChildElement("AuthType");
            routeElement.addChildElement("CarryType").setValue("0");
            routeElement.addChildElement("ServTestFlag").setValue("1");

            SoapRequest soapRequest = SoapRequest.create()
                    .url("http://www.webxml.com.cn/WebServices/ValidateEmailWebService.asmx?wsdl")
                    .header(new SoapHeader(esbElement))
                    .body(SoapBodyBuilders.create()
                            .methodName("ValidateEmailAddress")
                            .namespaceURI("http://WebXml.com.cn/")
                            .addMethodParameter("theEmail", "656469722@qq.com")
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
    @Ignore
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


    public static void main(String[] args) {
        String xml = "<soap:Envelope xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"><soap:Header><Esb><Route><Time>2019-11-21 15:14:55</Time><EsbId>33</EsbId></Route></Esb></soap:Header><soap:Body><ns1:gcDataQryResponse xmlns:ns1=\"http://oipDataShare.predeal.scap.ccssoft.com\"><ns1:gcDataQry><root><responseCode>000</responseCode><responseXML><GetMOAttributes><ResultCode>0</ResultCode><ResultDescr>Success</ResultDescr><ISDN>8617322902059</ISDN><GIMSI>204046246198023</GIMSI><LIMSI></LIMSI><IMSI>460037760205991</IMSI><ESN>8083AD72</ESN><OuterRoamAuth>0</OuterRoamAuth><MSActStatus>1</MSActStatus><GPRSActStatus>1</GPRSActStatus><SGSNAddress></SGSNAddress><SGSNAddressType></SGSNAddressType><SGSNNumber></SGSNNumber><MSCNumber></MSCNumber><VLRNumber></VLRNumber><SMSDPF>0</SMSDPF><InterSmsOriginating>1</InterSmsOriginating><InterSmsTerminating>1</InterSmsTerminating><InterVoiceOriginating>1</InterVoiceOriginating><InterVoiceTerminating>1</InterVoiceTerminating><InterDataService>1</InterDataService><NAM>0</NAM><Charge>8</Charge><OCSITPL></OCSITPL></GetMOAttributes></responseXML></root></ns1:gcDataQry></ns1:gcDataQryResponse></soap:Body></soap:Envelope>";

        Node contentNode = SoapUtils.getContentNode(xml);

        Node node = contentNode.selectSingleNode("*/*");
        System.out.println(contentNode.asXML());
        System.out.println(node.asXML());

    }


}
