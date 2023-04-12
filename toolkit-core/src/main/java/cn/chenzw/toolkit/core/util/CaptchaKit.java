package cn.chenzw.toolkit.core.util;

import cn.chenzw.toolkit.core.captcha.CaptchaBuilders;
import org.apache.commons.lang3.StringUtils;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;

/**
 * @author chenzw
 */
public final class CaptchaKit {


    /**
     * 生成验证码
     * <pre>
     *   FileOutputStream fos = new FileOutputStream("captcha.jpg");
     *   CaptchaKit.createCode("ABCD", fos);
     * </pre>
     * @param text 验证码文本
     * @param fos
     * @throws IOException
     */
    public static void createCode(String text, OutputStream fos) throws IOException {
        if (StringUtils.isEmpty(text)) {
            throw new IllegalArgumentException("text is null");
        }
        BufferedImage image = CaptchaBuilders.createDefault().text(text).build();
        writeImage(image, fos);
    }

    /**
     * 将BufferedImage写入到输出流种
     * <pre>
     *   BufferedImage image = CaptchaBuilders.createDefault()
     *      //.width(65)
     *      //.height(32)
     *      .text("ABCD")
     *      .build();
     *   FileOutputStream fos = new FileOutputStream("captcha.jpg");
     *   CaptchaKit.writeImage(image, fos);
     * </pre>
     *
     * @param image
     * @param fos
     * @throws IOException
     */
    public static void writeImage(BufferedImage image, OutputStream fos) throws IOException {
        ImageIO.write(image, "JPEG", fos);
    }


}
