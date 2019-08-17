package cn.chenzw.toolkit.freemarker;

import cn.chenzw.toolkit.domain.entity.User;
import cn.chenzw.toolkit.freemarker.builder.FreeMarkerBuilder;
import freemarker.template.TemplateException;
import org.apache.commons.lang3.StringUtils;
import org.dozer.DozerBeanMapper;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.*;

@RunWith(JUnit4.class)
public class FreeMarkerUtilsTests {

    static List<User> users = new ArrayList<>();

    static {
        for (int i = 0; i < 10; i++) {
            users.add(new User(i, "张三_" + i, "男", i, new Date()));
        }
    }

    @Test
    public void testProcessToString() throws IOException, TemplateException, URISyntaxException {
        Map<String, Object> userMap = new HashMap<>();
        userMap.put("users", users);

        URL templateFile = Thread.currentThread().getContextClassLoader().getResource("freemarker/template.ftl");
        String result = FreeMarkerUtils.processToString(new File(templateFile.toURI()), userMap);

        Assert.assertEquals("0: 张三_0,1: 张三_1,2: 张三_2,3: 张三_3,4: 张三_4,5: 张三_5,6: 张三_6,7: 张三_7,8: 张三_8,9: 张三_9,", result.replaceAll("\r\n", ","));
    }

    @Test
    public void testProcessToFile() throws URISyntaxException, IOException, TemplateException {
        Map<String, Object> userMap = new HashMap<>();
        userMap.put("users", users);

        URL templateFile = Thread.currentThread().getContextClassLoader().getResource("freemarker/template.ftl");
        File outFile = new File("target/result.txt");
        FreeMarkerUtils.processToFile(new File(templateFile.toURI()), userMap, outFile);
    }
}
