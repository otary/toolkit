package cn.chenzw.toolkit.authentication.support;

import cn.chenzw.toolkit.commons.ColorUtils;
import org.apache.commons.lang3.ArrayUtils;
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
    private int width = 120;

    /**
     * 图片高度
     */
    private int height = 62;


    /**
     * 字体样式集合
     */
    private Font[] fonts;

    private String[] fontNames = new String[]{"Times New Roman"};

    /**
     * 验证码文本
     */
    private String text;

    /**
     * 是否允许绘制干扰线
     */
    private boolean enableDrawRandomLine = true;

    /**
     * 字体大小
     */
    private int fontSize = 32;

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

    public CaptchaBuilder fonts(String[] fontNames) {
        this.fontNames = fontNames;
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
        graphics.setColor(ColorUtils.randomColor(160, 200));
        for (int i = 0; i < 150; i++) {
            int x = RandomUtils.nextInt(0, width);
            int y = RandomUtils.nextInt(0, height);
            int xl = RandomUtils.nextInt(0, width);
            int yl = RandomUtils.nextInt(0, height);
            graphics.drawLine(x, y, x + xl, y + yl);
        }

    }

    /**
     * 绘制文本
     *
     * @param graphics
     */
    private void drawText(Graphics graphics) {
        if (!StringUtils.isEmpty(text)) {
            for (int i = 0; i < text.length(); i++) {
                graphics.setColor(ColorUtils.randomColor(20, 130));
                graphics.drawString(String.valueOf(text.charAt(i)), (width / text.length()) * i + RandomUtils.nextInt(0, 10), (height / 2 + fontSize / 2) - RandomUtils.nextInt(0, 10));
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
        if (!ArrayUtils.isEmpty(fonts)) {
            Font font = fonts[RandomUtils.nextInt(0, fonts.length - 1)];
            graphics.setFont(font);

            this.fontSize = font.getSize();
        } else if (!ArrayUtils.isEmpty(fontNames)) {
            String fontName = fontNames[RandomUtils.nextInt(0, fontNames.length - 1)];
            graphics.setFont(new Font(fontName, Font.ITALIC, fontSize));
        }

        // 绘制干扰线
        if (enableDrawRandomLine) {
            drawRandomLine(graphics);
        }

        // 绘制验证码
        drawText(graphics);

        graphics.dispose();

        return image;
    }


}
