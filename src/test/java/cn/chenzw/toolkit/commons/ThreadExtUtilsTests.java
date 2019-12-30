package cn.chenzw.toolkit.commons;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class ThreadExtUtilsTests {

    @Test
    public void testGetCurrentStackElement() {
        StackTraceElement stackElement = ThreadExtUtils.getCurrentStackElement();
        Assert.assertEquals("cn.chenzw.toolkit.commons.ThreadExtUtils", stackElement.getClassName());
        Assert.assertEquals("ThreadExtUtils.java", stackElement.getFileName());
        Assert.assertEquals("getCurrentStackElement", stackElement.getMethodName());
        Assert.assertEquals(38, stackElement.getLineNumber());
    }
}
