package cn.chenzw.toolkit.commons;

import cn.chenzw.toolkit.commons.enums.FileType;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.io.IOException;
import java.io.InputStream;

@RunWith(JUnit4.class)
public class FileExtUtilsTests {

    @Test
    public void testRelativePath() {
        Assert.assertEquals("domain\\entity\\entity.ftl", FileExtUtils.relativePath("C:\\Users\\chenzw\\IdeaProjects\\code-generator\\code-generator-server\\target\\classes\\template\\basic\\domain\\entity\\entity.ftl", "C:\\Users\\chenzw\\IdeaProjects\\code-generator\\code-generator-server\\target\\classes\\template\\basic"));
        Assert.assertEquals("", FileExtUtils.relativePath(null, "C:\\Users\\chenzw\\IdeaProjects\\code-generator\\code-generator-server\\target\\classes\\template\\basic"));
        Assert.assertEquals("C:\\Users\\chenzw\\IdeaProjects\\code-generator\\code-generator-server\\target\\classes\\template\\basic\\domain\\entity\\entity.ftl", FileExtUtils.relativePath("C:\\Users\\chenzw\\IdeaProjects\\code-generator\\code-generator-server\\target\\classes\\template\\basic\\domain\\entity\\entity.ftl", null));
    }

    @Test
    public void testGetFileType() throws IOException {
        InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream("cache/ehcache.xml");
        FileType fileType = FileExtUtils.getFileType(is);
        Assert.assertSame(FileType.XML, fileType);

        InputStream is2 = Thread.currentThread().getContextClassLoader().getResourceAsStream("commons/index.js");
        FileType fileType2 = FileExtUtils.getFileType(is2);

        System.out.println(fileType2);

    }

}
