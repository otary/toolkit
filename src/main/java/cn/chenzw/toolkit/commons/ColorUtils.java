package cn.chenzw.toolkit.commons;

import org.apache.commons.lang3.StringUtils;

import java.awt.*;

/**
 * 颜色工具类
 *
 * @author
 */
public final class ColorUtils {
    private ColorUtils() {
    }

    /**
     * 将十六进制颜色转RGB格式
     *
     * @param hexColor
     * @return
     */
    public static final Color hexToRgb(String hexColor) {

        if (StringUtils.isBlank(hexColor)) {
            throw new IllegalArgumentException("hex color is null!");
        }

        if (StringUtils.startsWith(hexColor, "#")) {
            hexColor = StringUtils.removeStart(hexColor, "#");
        }
        return new Color(Integer.parseInt(hexColor, 16));
    }


}
