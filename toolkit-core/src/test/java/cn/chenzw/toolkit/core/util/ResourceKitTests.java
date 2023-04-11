package cn.chenzw.toolkit.core.util;

import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.io.File;

/**
 * @author chenzw
 */
@Slf4j
@RunWith(JUnit4.class)
public class ResourceKitTests {

    @Test
    public void testLoadFromProjectPath() {
        File file = ResourceKit.loadFromProjectPath("pom.xml");
        Assert.assertTrue(file.exists());
        log.info("pom.xml => {}", file);
    }

    @Test
    public void testLoadFromClassPath() {
        File file = ResourceKit.loadFromClassPath("cn/chenzw/toolkit/core/util/ResourceKitTests.class");
        Assert.assertTrue(file.exists());
        log.info("ResourceKitTests => {}", file);
    }
}
