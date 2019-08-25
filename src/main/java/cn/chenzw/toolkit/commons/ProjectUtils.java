package cn.chenzw.toolkit.commons;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * 项目相关
 *
 * @author chenzw
 */
public class ProjectUtils {

    private ProjectUtils() {
    }

    /**
     * 获取项目根路径
     *
     * @return
     */
    public static String getRootPath() {
        return System.getProperty("user.dir");
    }

    /**
     * 获取项目根路径2
     *
     * @return
     */
    public static String getRootPath2() {
        return Thread.currentThread().getContextClassLoader().getResource("").getPath();
    }


    /**
     * 获取classpath路径
     *
     * @return
     */
    public static String getClassPath() {
        return ProjectUtils.class.getResource("/").getPath();
    }


    /**
     * 获取依赖的Jar文件
     *
     * @return
     */
    public static List<File> getDependentJarFiles() {
        String path = System.getProperty("java.class.path");
        String[] paths = path.split(";");

        List<File> jarFiles = new ArrayList<>();
        for (String jarPath : paths) {
            jarFiles.add(new File(jarPath));
        }
        return jarFiles;
    }
}