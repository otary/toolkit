package cn.chenzw.toolkit.authentication.shiro;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;


/**
 * Shiro工具类
 *
 * @author chenzw
 */
public class ShiroUtils {

    private ShiroUtils() {
    }


    /**
     * 是否已登录
     *
     * @return
     */
    public static boolean isLogin() {
        return SecurityUtils.getSubject().getPrincipal() != null;
    }

    /**
     * 退出登录
     */
    public static void logout() {
        SecurityUtils.getSubject().logout();
    }


    /**
     * 获取当前登录的用户
     *
     * @param <T>
     * @return
     */
    public static <T> T getUserInfo() {
        return (T) SecurityUtils.getSubject().getPrincipal();
    }

    /**
     * @return
     */
    public static Subject getSubject() {
        return SecurityUtils.getSubject();
    }


    /**
     * @return
     */
    public static Session getSession() {
        return SecurityUtils.getSubject().getSession();
    }


}
