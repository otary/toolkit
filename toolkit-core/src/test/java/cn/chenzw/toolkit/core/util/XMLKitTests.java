package cn.chenzw.toolkit.core.util;

import cn.chenzw.toolkit.core.entity.attr.XmlToMapAttr;
import org.dom4j.DocumentException;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.Map;

@RunWith(JUnit4.class)
public class XMLKitTests {

    @Test
    public void testXmlToMap() throws DocumentException {
        XmlToMapAttr xmlToMapAttr = new XmlToMapAttr();
        xmlToMapAttr.setUseTextAttr(true);

        Map<String, String> result = XMLKit.xmlToMap("<note a=\"ttt\" b=\"kk\">\n" +
                "<to cc=\"xxx\" dd=\"yyy\">George</to>\n" +
                "<from><c><d>xxx</d></c></from>\n" +
                "<heading>Reminder</heading>\n" +
                "<body>Don't forget the meeting! <b>xxx</b></body>\n" +
                "</note>", xmlToMapAttr);

        Assert.assertEquals("{note={heading={#text=Reminder}, #attrs={a=ttt, b=kk}, from={c={d={#text=xxx}}}, to={#attrs={cc=xxx, dd=yyy}, #text=George}, body={b={#text=xxx}, #text=Don't forget the meeting! }}}", result.toString());
    }

    @Test
    public void testXml2Json() throws DocumentException {
        String json = XMLKit.xml2Json("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?>\n" +
                "<root> \n" +
                "    <a>aaa</a>\n" +
                "    <b>bbb</b>\n" +
                "    <c>\n" +
                "        <d>ddd</d>\n" +
                "        <e>eee</e>\n" +
                "    </c>\n" +
                "</root>");

        Assert.assertEquals("{\"root\":{\"a\":\"aaa\",\"b\":\"bbb\",\"c\":{\"d\":\"ddd\",\"e\":\"eee\"}}}", json);
    }
}
