package cn.chenzw.toolkit.commons;

import java.io.File;
import java.util.regex.Matcher;

/**
 * 包操作工具
 *
 * @author chenzw
 */
public class PackageExtUtils {

    private PackageExtUtils() {
    }

    /**
     * 包路径转文件路径
     *
     * @param packageName
     * @return
     */
    public static final String toFilePath(String packageName) {
        return packageName.replaceAll("\\.", Matcher.quoteReplacement(File.separator));
    }
}
