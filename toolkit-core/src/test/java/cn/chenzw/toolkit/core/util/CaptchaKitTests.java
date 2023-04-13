package cn.chenzw.toolkit.core.util;

import cn.chenzw.toolkit.core.captcha.CaptchaBuilders;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @author chenzw
 */
@RunWith(JUnit4.class)
public class CaptchaKitTests {

    @Test
    public void testBuilder() throws IOException {
        BufferedImage image = CaptchaBuilders.createDefault()
                //.width(65)
                //.height(32)
                .text("ABCD")
                .build();
        FileOutputStream fos = new FileOutputStream("captcha.jpg");
        ImageIO.write(image, "JPEG", fos);
    }

    @Test
    public void testCreateCode() throws IOException {
        FileOutputStream fos = new FileOutputStream("captcha2.jpg");
        CaptchaKit.createCode("XYZ", fos);
    }

}
