package cn.chenzw.toolkit.commons;

/**
 * 项目相关
 *
 * @author chenzw
 */
public class ProjectUtils {

    /**
     * 获取项目根路径
     *
     * @return
     */
    public static String getRootPath() {
        return System.getProperty("user.dir");
    }


    /**
     * 获取classpath路径
     *
     * @return
     */
    public static String getClassPath() {
        return ProjectUtils.class.getResource("/").getPath();
    }

}
