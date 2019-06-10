package cn.chenzw.toolkit.commons;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

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


}
