package cn.chenzw.toolkit.authentication.support;

import cn.chenzw.toolkit.commons.ColorUtils;
import org.apache.commons.lang3.RandomUtils;
import org.apache.commons.lang3.StringUtils;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * 验证码构建器
 *
 * @author chenzw
 */
public class CaptchaBuilder {

    /**
     * 图片宽度
     */
    private int width = 64;

    /**
     * 图片高度
     */
    private int height = 32;

    /**
     * 字体样式集合
     */
    private Font[] fonts = new Font[]{
            new Font("Times New Roman", Font.ITALIC, height - 5)
    };

    /**
     * 验证码文本
     */
    private String text;

    /**
     * 是否允许绘制干扰线
     */
    private boolean enableDrawRandomLine = true;

    public CaptchaBuilder width(int width) {
        this.width = width;

        return this;
    }

    public CaptchaBuilder height(int height) {
        this.height = height;

        return this;
    }

    public CaptchaBuilder fonts(Font[] fonts) {
        this.fonts = fonts;

        return this;
    }


    public CaptchaBuilder text(String text) {
        this.text = text;

        return this;
    }


    public CaptchaBuilder enableDrawRandomLine(boolean enableDrawRandomLine) {
        this.enableDrawRandomLine = enableDrawRandomLine;

        return this;
    }


    /**
     * 绘制干扰线条
     */
    private void drawRandomLine(Graphics graphics) {

        for (int i = 0; i < 155; i++) {
            int x = RandomUtils.nextInt(0, width);
            int y = RandomUtils.nextInt(0, height);
            int xl = RandomUtils.nextInt(0, width);
            int yl = RandomUtils.nextInt(0, height);
            graphics.drawLine(x, y, x + xl, y + yl);
        }

    }

    /**
     * 写入文本
     *
     * @param graphics
     */
    private void drawText(Graphics graphics) {
        if (!StringUtils.isEmpty(text)) {
            for (int i = 0; i < text.length(); i++) {
                graphics.setColor(ColorUtils.randomColor(20, 130));
                graphics.drawString(String.valueOf(text.charAt(i)), 1, 16);
            }
        }
    }


    public BufferedImage build() throws IOException {
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_3BYTE_BGR);
        Graphics2D graphics = image.createGraphics();

        // 抗锯齿
        graphics.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_LCD_HRGB);

        // 背景颜色（背景颜色不可与字体颜色冲突）
        graphics.setColor(ColorUtils.randomColor(200, 255));
        // 范围
        graphics.fillRect(0, 0, width, height);
        // 字体
        graphics.setFont(fonts[RandomUtils.nextInt(0, fonts.length - 1)]);

        // 绘制干扰线
        if (enableDrawRandomLine) {
            drawRandomLine(graphics);
        }


        graphics.setColor(ColorUtils.randomColor(20, 130));
        graphics.drawString("4", 0, height);

        graphics.dispose();

        return image;
    }


}
