package cn.chenzw.toolkit.core.io;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

@RunWith(JUnit4.class)
public class IOKitTests {

    @Test
    public void testCopy() throws IOException {
        InputStream is = new ByteArrayInputStream("hello world".getBytes());

        InputStream copyIs = IOKit.copy(is);

        Assert.assertEquals(11, is.available());
        Assert.assertEquals(11, copyIs.available());
    }
}
