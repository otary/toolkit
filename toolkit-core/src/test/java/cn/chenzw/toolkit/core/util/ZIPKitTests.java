package cn.chenzw.toolkit.core.util;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;

@RunWith(JUnit4.class)
public class ZIPKitTests {

    @Test
    public void testToZip() throws IOException {
        try (OutputStream os = Files.newOutputStream(new File("src.zip").toPath())) {
            ZIPKit.toZip(new File("src"), os);
        }
    }
}
