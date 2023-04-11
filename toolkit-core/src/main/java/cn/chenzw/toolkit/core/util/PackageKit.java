package cn.chenzw.toolkit.core.util;

import java.io.File;
import java.util.regex.Matcher;

/**
 * 包操作工具
 *
 * @author chenzw
 */
public class PackageKit {

    private PackageKit() {
    }

    /**
     * 包路径转文件路径
     *
     * <pre>
     * PackageKit.toFilePath("cn.chenzw.toolkit.core") = "cn\chenzw\toolkit\core"
     * </pre>
     * @param packageName
     * @return
     */
    public static final String toFilePath(String packageName) {
        return packageName.replaceAll("\\.", Matcher.quoteReplacement(File.separator));
    }

}
