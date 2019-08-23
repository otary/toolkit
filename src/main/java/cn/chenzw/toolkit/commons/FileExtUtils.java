package cn.chenzw.toolkit.commons;

import org.apache.commons.lang3.StringUtils;

import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * 文件工具类
 *
 * @author chenzw
 */
public class FileExtUtils {

    private FileExtUtils() {
    }


    /**
     * 获取两个路径的相对路径
     *
     * @param fullPath
     * @param fragmentPath
     * @return
     */
    public static String relativePath(String fullPath, String fragmentPath) {
        if (StringUtils.isEmpty(fragmentPath)) {
            return fullPath;
        }
        if (StringUtils.isEmpty(fullPath)) {
            return "";
        }
        Path fullPath2 = Paths.get(fullPath);
        Path fragmentPath2 = Paths.get(fragmentPath);
        Path relativizePath = fragmentPath2.relativize(fullPath2);
        return relativizePath.toString();
    }

}
