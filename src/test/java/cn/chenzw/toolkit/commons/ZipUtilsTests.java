package cn.chenzw.toolkit.commons;

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
public class ZipUtilsTests {

    @Test
    public void testToZip() throws IOException {
        try (OutputStream os = new FileOutputStream(new File("src.zip"))) {
            ZipUtils.toZip(new File("src"), os);
        }
    }

    @Test
    public void test() {
       List<String> aa = new ArrayList<>();
       aa.add("1");
       aa.add("2");


        Iterator<String> iterator = aa.iterator();
        while (iterator.hasNext()) {
            iterator.next();
            iterator.remove();
        }

      /*  for (int i = 0; i < aa.size(); i++) {
            aa.remove(i);
        }*/
    }
}
