package cn.chenzw.toolkit.core.util;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

@RunWith(JUnit4.class)
public class ZIPKitTests {

    @Test
    public void testToZip() throws IOException {
        try (OutputStream os = new FileOutputStream(new File("src.zip"))) {
            ZIPKit.toZip(new File("src"), os);
        }
    }
}
