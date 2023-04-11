package cn.chenzw.toolkit.core.util;

import org.apache.commons.lang3.StringUtils;

import java.awt.*;
import java.util.Random;

/**
 * 颜色工具类
 *
 * @author chenzw
 */
public final class ColorKit {
    private ColorKit() {
    }

    /**
     * 将十六进制颜色转RGB格式
     *
     * @param hexColor
     * @return
     */
    public static Color hexToRgb(String hexColor) {

        if (StringUtils.isBlank(hexColor)) {
            throw new IllegalArgumentException("hex color is null!");
        }

        if (StringUtils.startsWith(hexColor, "#")) {
            hexColor = StringUtils.removeStart(hexColor, "#");
        }
        return new Color(Integer.parseInt(hexColor, 16));
    }

    /**
     * 生成随机的颜色
     *
     * @param fc 起始颜色值
     * @param bc 结束颜色值
     * @return
     */
    public static Color randomColor(int fc, int bc) {
        Random random = new Random();
        if (fc > 255) {
            fc = 255;
        }
        if (bc > 255) {
            bc = 255;
        }
        int r = fc + random.nextInt(bc - fc);
        int g = fc + random.nextInt(bc - fc);
        int b = fc + random.nextInt(bc - fc);
        return new Color(r, g, b);
    }

}
