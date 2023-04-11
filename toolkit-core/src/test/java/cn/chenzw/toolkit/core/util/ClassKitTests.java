package cn.chenzw.toolkit.core.util;

import cn.chenzw.toolkit.core.util.ClassKit;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.List;

@Slf4j
@RunWith(JUnit4.class)
public class ClassKitTests {

    @Test
    public void testIsPresent() {
        boolean present = ClassKit.isPresent("cn.chenzw.toolkit.core.lang.ClassKit");
        Assert.assertTrue(present);
    }

    @Test
    public void testForName() throws ClassNotFoundException {
        Class<?> aClass = ClassKit.forName("cn.chenzw.toolkit.core.lang.ClassKit");
        Assert.assertNotNull(aClass);
    }

    @Test
    public void testFindSourceJar() {
        URL sourceJar = ClassKit.findSourceJar(DateUtils.class);
        Assert.assertTrue(StringUtils
                .endsWith(sourceJar.getPath(), "org/apache/commons/commons-lang3/3.9/commons-lang3-3.9.jar"));

        URL sourceJar2 = ClassKit.findSourceJar(ClassKit.class);
        Assert.assertTrue(StringUtils.endsWith(sourceJar2.getPath(), "/toolkit-core/target/classes/"));
    }


    @Test
    public void testGenerateUniqueClassName() {
        Assert.assertEquals(
                "cn.chenzw.toolkit.core.lang.ClassKit$1",
                ClassKit.generateUniqueClassName("cn.chenzw.toolkit.core.lang.ClassKit")
        );
    }

    @Test
    public void testGetBootstrapClassPath() {
        URL[] bootstrapClassPaths = ClassKit.getBootstrapClassPath();
        log.info("bootstrapClassPaths => {}", Arrays.toString(bootstrapClassPaths));
    }

    @Test
    public void testGetExtClassPath() throws MalformedURLException {
        List<URL> extClassPath = ClassKit.getExtClassPath();
        log.info("extClassPath => {}", extClassPath);
    }

    @Test
    public void testGetAppClassPath() throws MalformedURLException {
        List<URL> appClassPath = ClassKit.getAppClassPath();
        log.info("appClassPath => {}", appClassPath);
    }

    @Test
    public void testIsStatic() throws NoSuchMethodException {
        Method method = ClassKit.class.getDeclaredMethod("isPresent", String.class);
        Assert.assertTrue(ClassKit.isStatic(method));
    }
}
