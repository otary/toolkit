package cn.chenzw.toolkit.authentication.util;

import cn.chenzw.toolkit.authentication.support.CaptchaBuilders;
import org.apache.commons.lang3.StringUtils;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;

/**
 * 验证码工具类
 *
 * @author chenzw
 */
public class CaptchaUtils {

    private CaptchaUtils() {
    }

    /**
     * 生成验证码
     * <pre>
     *    BufferedImage image = CaptchaBuilders.createDefault()
     *         //.width(65).height(32)
     *         .text("ABCD").build();
     * </pre>
     *
     * @param image
     * @param fos
     * @throws IOException
     */
    public static void createCode(BufferedImage image, OutputStream fos) throws IOException {
        ImageIO.write(image, "JPEG", fos);
    }

    /**
     * 生成验证码
     *
     * @param text 验证码文本
     * @param fos
     * @throws IOException
     */
    public static void createCode(String text, OutputStream fos) throws IOException {
        if (StringUtils.isEmpty(text)) {
            throw new IllegalArgumentException("text is null.");
        }
        BufferedImage image = CaptchaBuilders.createDefault().text(text).build();

        createCode(image, fos);
    }

}
