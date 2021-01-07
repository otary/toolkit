package cn.chenzw.toolkit.commons;

import org.dom4j.DocumentException;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.Map;

@RunWith(JUnit4.class)
public class XmlUtilsTests {

    @Test
    public void testXmlToMap() throws DocumentException {
        Map<String, String> result = XmlUtils.xmlToMap("<note>\n" +
                "<to>George</to>\n" +
                "<from>John</from>\n" +
                "<heading>Reminder</heading>\n" +
                "<body>Don't forget the meeting! <b>xxx</b></body>\n" +
                "</note>");

        Assert.assertEquals("{note={heading=Reminder, from=John, to=George, body={b=xxx}}}", result.toString());
    }
}
