package cn.chenzw.toolkit.core.io;

import cn.chenzw.toolkit.core.enums.FileType;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.io.IOException;
import java.io.InputStream;

@Slf4j
@RunWith(JUnit4.class)
public class FileExtKitTests {

    @Test
    public void testRelativePath() {
        Assert.assertEquals("domain\\entity\\entity.ftl", FileKit.relativePath("C:\\Users\\chenzw\\IdeaProjects\\code-generator\\code-generator-server\\target\\classes\\template\\basic\\domain\\entity\\entity.ftl", "C:\\Users\\chenzw\\IdeaProjects\\code-generator\\code-generator-server\\target\\classes\\template\\basic"));
        Assert.assertEquals("", FileKit.relativePath(null, "C:\\Users\\chenzw\\IdeaProjects\\code-generator\\code-generator-server\\target\\classes\\template\\basic"));
        Assert.assertEquals("C:\\Users\\chenzw\\IdeaProjects\\code-generator\\code-generator-server\\target\\classes\\template\\basic\\domain\\entity\\entity.ftl", FileKit.relativePath("C:\\Users\\chenzw\\IdeaProjects\\code-generator\\code-generator-server\\target\\classes\\template\\basic\\domain\\entity\\entity.ftl", null));
    }

    @Test
    public void testGetFileType() throws IOException {
        InputStream is2 = Thread.currentThread().getContextClassLoader().getResourceAsStream("commons/index.js");
        FileType fileType2 = FileKit.getFileType(is2);

        log.info("文件类型 => {}", fileType2);
    }

}
