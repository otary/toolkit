package cn.chenzw.toolkit.commons;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.TestTemplate;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class JsonUtilsTests {

    @Test
    public void testJson2Xml() {
        String xml = JsonUtils.json2Xml("{\"root\":{\"a\":\"aaa\",\"b\":\"bbb\",\"c\":{\"d\":\"ddd\",\"e\":\"eee\"}}}");

        Assert.assertEquals("<root><a>aaa</a><b>bbb</b><c><d>ddd</d><e>eee</e></c></root>", xml);

        String xml2 = JsonUtils.json2Xml("{\"root\":{\"a\":\"aaa\",\"b\":\"bbb\",\"c\":{\"d\":\"ddd\",\"e\":\"eee\"},\"f\":[{\"g\":\"ggg1\",\"h\":\"hhh1\"},{\"g\":\"ggg2\",\"h\":\"hhh2\"}]}}");

        Assert.assertEquals("<root><a>aaa</a><b>bbb</b><c><d>ddd</d><e>eee</e></c><f><g>ggg1</g><h>hhh1</h></f><f><g>ggg2</g><h>hhh2</h></f></root>", xml2);
    }

    @Test
    public void testJson2Yaml() {
        String yaml = JsonUtils.json2Yaml("{\"root\":{\"a\":\"aaa\",\"b\":\"bbb\",\"c\":{\"d\":\"ddd\",\"e\":\"eee\"}}}");
        Assert.assertEquals("---\n" +
                "root:\n" +
                "  a: \"aaa\"\n" +
                "  b: \"bbb\"\n" +
                "  c:\n" +
                "    d: \"ddd\"\n" +
                "    e: \"eee\"\n", yaml);
    }
}
