package cn.chenzw.toolkit.authentication.support;

import java.awt.*;

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
     * 字体样式
     */
    private Font font;

    public CaptchaBuilder width(int width) {
        this.width = width;

        return this;
    }

    public CaptchaBuilder height(int height) {
        this.height = height;

        return this;
    }

    public CaptchaBuilder font(Font font) {
        this.font = font;

        return this;
    }


}
