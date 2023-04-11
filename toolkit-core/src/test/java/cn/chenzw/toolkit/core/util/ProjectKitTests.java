package cn.chenzw.toolkit.core.util;

import lombok.extern.slf4j.Slf4j;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.io.File;
import java.util.List;

@Slf4j
@RunWith(JUnit4.class)
public class ProjectKitTests {

    @Test
    public void testGetRootPath() {
        String rootPath = ProjectKit.getRootPath();

        Assert.assertThat(rootPath, Matchers.endsWith("toolkit-core"));

        log.info("rootPath => {}", rootPath);
    }

    @Test
    public void testGetClassPath() {
        String classPath = ProjectKit.getClassPath();

        Assert.assertThat(classPath, Matchers.endsWith("toolkit-core/target/test-classes/"));

        log.info("classPath => {}", classPath);
    }

    @Test
    public void testGetDependentJarFiles() {
        List<File> dependentJarFiles = ProjectKit.getDependentJarFiles();

        Assert.assertTrue(dependentJarFiles.size() > 0);

        log.info("dependentJarFiles => {}", dependentJarFiles);
    }

    @Test
    public void testGetProcessId(){
        int processId = ProjectKit.getProcessId();
        Assert.assertTrue(processId > 0);

        log.info("processId => {}", processId);
    }

}
