package cn.chenzw.toolkit.authentication.support;

import cn.chenzw.toolkit.commons.ProjectUtils;
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

        String random = CaptchaBuilders.randomAlphanumeric(4);

        BufferedImage image = CaptchaBuilders.create()
                //.width(65).height(32)
                .text("ABCDEFG").build();

        FileOutputStream fos = new FileOutputStream(new File(ProjectUtils.getClassPath() + File.separator + "captcha.jpg"));
        ImageIO.write(image, "JPEG", fos);
    }
}
