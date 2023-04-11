package cn.chenzw.toolkit.core.util;

import org.apache.commons.lang3.StringUtils;

import java.io.File;

/**
 * 资源工具类
 *
 * @author chenzw
 */
public final class ResourceKit {

    private ResourceKit() {

    }

    /**
     * 从项目同级目录下加载文件（项目外部）
     *
     * @param name
     * @return
     */
    public static final File loadFromProjectPath(String name) {
        return new File(ProjectKit.getRootPath() + File.separator + name);
    }

    /**
     * 从项目classpath下加载文件
     *
     * @param name
     * @return
     */
    public static final File loadFromClassPath(String name) {
        return new File(ProjectKit.getClassPath() + File.separator + name);
    }


    /**
     * 加载文件
     *
     * <b>加载顺序:</b>
     * <ul>
     * <li>先从项目同级目录中查找文件</li>
     * <li>找不到，再从classpath中查找文件</li>
     * </ul>
     *
     * @param folder 项目外额外的目录
     * @param name
     * @return
     */
    public static final File load(String folder, String name) {
        File projectPathFile = loadFromProjectPath(StringUtils.defaultIfEmpty(folder, "") + File.separator + name);
        if (projectPathFile.exists()) {
            return projectPathFile;
        }
        return loadFromClassPath(name);
    }

}
