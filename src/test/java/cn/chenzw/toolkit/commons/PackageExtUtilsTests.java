package cn.chenzw.toolkit.commons;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class PackageExtUtilsTests {

    @Test
    public void testToFilePath() {
        Assert.assertEquals("cn\\chenzw\\toolkit\\commons", PackageExtUtils.toFilePath("cn.chenzw.toolkit.commons"));
    }
}
