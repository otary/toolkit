package cn.chenzw.toolkit.commons;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class JsonUtilsTests {

    @Test
    public void testJson2Xml() throws JsonProcessingException {
        String xml = JsonUtils.json2Xml("{\"root\":{\"a\":\"aaa\",\"b\":\"bbb\",\"c\":{\"d\":\"ddd\",\"e\":\"eee\"}}}");

        Assert.assertEquals("<root><a>aaa</a><b>bbb</b><c><d>ddd</d><e>eee</e></c></root>", xml);

        String xml2 = JsonUtils.json2Xml("{\"root\":{\"a\":\"aaa\",\"b\":\"bbb\",\"c\":{\"d\":\"ddd\",\"e\":\"eee\"},\"f\":[{\"g\":\"ggg1\",\"h\":\"hhh1\"},{\"g\":\"ggg2\",\"h\":\"hhh2\"}]}}");
        System.out.println(xml2);
    }
}
