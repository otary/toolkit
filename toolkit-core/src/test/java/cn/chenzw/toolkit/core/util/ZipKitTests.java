package cn.chenzw.toolkit.core.util;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@RunWith(JUnit4.class)
public class ZipKitTests {

    @Test
    public void testToZip() throws IOException {
        try (OutputStream os = new FileOutputStream(new File("src.zip"))) {
            ZipKit.toZip(new File("src"), os);
        }
    }
}
