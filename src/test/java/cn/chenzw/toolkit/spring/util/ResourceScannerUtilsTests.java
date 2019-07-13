package cn.chenzw.toolkit.spring.util;

import cn.chenzw.toolkit.domain.Writeable;
import cn.chenzw.toolkit.domain.dto.BookDto;
import cn.chenzw.toolkit.domain.entity.Book;
import cn.chenzw.toolkit.spring.aop.JoinPointWrapper;
import cn.chenzw.toolkit.support.SSO;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.springframework.core.io.Resource;

import java.io.IOException;
import java.util.Arrays;
import java.util.Set;

@RunWith(JUnit4.class)
public class ResourceScannerUtilsTests {

    @Test
    public void testScanClass() throws IOException, ClassNotFoundException {
        // 扫描包下的所有类
        Set<Class<?>> classes = ResourceScannerUtils.scanClass("cn.chenzw.toolkit");
        Assert.assertTrue(classes.contains(ResourceScannerUtils.class));
        Assert.assertTrue(classes.contains(JoinPointWrapper.class));


        // 扫描@SSO注解的类
        Set<Class<?>> annoClasses = ResourceScannerUtils.scanClassFromAnnotation("cn.chenzw.toolkit", new Class[]{SSO.class});
        Assert.assertTrue(annoClasses.contains(BookDto.class));

        // 扫描Writeable接口的实现类/继承类
        Set<Class<?>> superClasses = ResourceScannerUtils.scanClassFromSuper("cn.chenzw.toolkit", new Class[]{Writeable.class});
        Assert.assertTrue(superClasses.contains(Writeable.class));
        Assert.assertTrue(superClasses.contains(BookDto.class));


    }

    @Test
    public void testScan() throws IOException {
        // 扫描xml文件
        Set<Resource> xmlResources = ResourceScannerUtils.scan("cn.chenzw.toolkit", ResourceScannerUtils.SUFFIX.XML);
        System.out.println(xmlResources);

        // 扫描所有文件
        Set<Resource> allResources = ResourceScannerUtils.scan("cn.chenzw.toolkit", ResourceScannerUtils.SUFFIX.ALL);
        System.out.println(allResources);

    }

}
