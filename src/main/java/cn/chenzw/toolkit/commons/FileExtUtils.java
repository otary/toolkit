package cn.chenzw.toolkit.commons;

import org.apache.commons.lang3.StringUtils;

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
        return Paths.get(fragmentPath).relativize(Paths.get(fullPath)).toString();
    }

}
