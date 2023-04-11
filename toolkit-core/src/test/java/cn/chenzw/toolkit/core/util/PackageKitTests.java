package cn.chenzw.toolkit.core.util;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class PackageKitTests {

    @Test
    public void testToFilePath() {
        Assert.assertEquals("cn\\chenzw\\toolkit\\core", PackageKit.toFilePath("cn.chenzw.toolkit.core"));
    }
}
