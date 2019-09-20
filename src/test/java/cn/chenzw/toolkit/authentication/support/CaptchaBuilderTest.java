package cn.chenzw.toolkit.authentication.support;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

@RunWith(JUnit4.class)
public class CaptchaBuilderTest {

    @Test
    public void testBasic() throws IOException {
        BufferedImage image = new CaptchaBuilder().build();

        FileOutputStream fos = new FileOutputStream(new File("a.jpg"));
        ImageIO.write(image, "JPEG", fos);

    }
}
