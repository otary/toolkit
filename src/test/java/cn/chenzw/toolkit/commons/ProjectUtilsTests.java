package cn.chenzw.toolkit.commons;

import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class ProjectUtilsTests {

    @Test
    public void testGetRootPath() {
        String rootPath = ProjectUtils.getRootPath();

        Assert.assertThat(rootPath, Matchers.endsWith("toolkit"));
    }

    @Test
    public void testGetClassPath(){
        String classPath = ProjectUtils.getClassPath();

        Assert.assertThat(classPath, Matchers.endsWith("toolkit/target/test-classes/"));
    }
}
