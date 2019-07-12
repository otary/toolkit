package cn.chenzw.toolkit.commons;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.net.URL;

@RunWith(JUnit4.class)
public class ClassExtUtilsTests {

    @Test
    public void testIsPresent() {
        boolean present = ClassExtUtils.isPresent("cn.chenzw.toolkit.commons.DateExtUtils");
        Assert.assertTrue(present);
    }

    @Test
    public void testForName() throws ClassNotFoundException {
        Class<?> aClass = ClassExtUtils.forName("cn.chenzw.toolkit.commons.DateExtUtils");
        Assert.assertNotNull(aClass);
    }

    @Test
    public void testFindSourceJar() {
        URL sourceJar = ClassExtUtils.findSourceJar(DateUtils.class);
        Assert.assertTrue(StringUtils
                .endsWith(sourceJar.getPath(), "org/apache/commons/commons-lang3/3.9/commons-lang3-3.9.jar"));

        URL sourceJar2 = ClassExtUtils.findSourceJar(DateExtUtils.class);
        Assert.assertTrue(StringUtils.endsWith(sourceJar2.getPath(), "toolkit/target/classes/"));
    }


}
