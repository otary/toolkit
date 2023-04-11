package cn.chenzw.toolkit.core.lang;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class ThreadKitTests {

    @Test
    public void testGetCurrentStackElement() {
        StackTraceElement stackElement = ThreadKit.getCurrentStackElement();
        Assert.assertEquals("cn.chenzw.toolkit.core.lang.ThreadKit", stackElement.getClassName());
        Assert.assertEquals("ThreadKit.java", stackElement.getFileName());
        Assert.assertEquals("getCurrentStackElement", stackElement.getMethodName());
        Assert.assertEquals(38, stackElement.getLineNumber());
    }
}
