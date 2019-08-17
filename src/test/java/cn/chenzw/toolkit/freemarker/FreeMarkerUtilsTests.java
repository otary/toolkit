package cn.chenzw.toolkit.freemarker;

import cn.chenzw.toolkit.domain.entity.User;
import cn.chenzw.toolkit.freemarker.builder.FreeMarkerBuilder;
import freemarker.template.TemplateException;
import org.dozer.DozerBeanMapper;
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

       /* FreeMarkerBuilder freeMarkerBuilder = FreeMarkerBuilder.create().setTemplatePath(Thread.currentThread().getContextClassLoader().getResource("freemarker").getPath());
        freeMarkerBuilder.setTemplateName("template.ftl");
        String s = FreeMarkerUtils.processToString(userMap, freeMarkerBuilder);*/


        URL resource = Thread.currentThread().getContextClassLoader().getResource("freemarker/template.ftl");

        File file = new File(resource.toURI());

        String s = FreeMarkerUtils.processToString(file, userMap, null);

        System.out.println(s);
    }
}
