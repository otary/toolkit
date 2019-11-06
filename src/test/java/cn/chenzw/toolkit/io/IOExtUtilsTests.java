package cn.chenzw.toolkit.io;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.io.IOException;
import java.io.InputStream;

@RunWith(JUnit4.class)
public class IOExtUtilsTests {

    @Test
    public void testCopy() throws IOException {
        InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream("io/test.txt");

        InputStream copyIs = IOExtUtils.copy(is);

        Assert.assertEquals(19, is.available());
        Assert.assertEquals(19, copyIs.available());
    }
}
