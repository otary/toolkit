package cn.chenzw.toolkit.authentication.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;

import javax.servlet.http.HttpServletRequest;
import java.util.Collection;

/**
 * @author chenzw
 */
public class SecurityUtils {

    /**
     * 获取当前用户对象
     *
     * @param <T>
     * @return
     */
    public static <T> T getUser() {
        Authentication authentication = getAuthentication();
        if (authentication == null) {
            return null;
        }
        return (T) authentication.getPrincipal();
    }

    /**
     * 获取用户名
     *
     * @return
     */
    public static String getUserName() {
        Authentication authentication = getAuthentication();
        if (authentication == null) {
            return null;
        }
        return authentication.getName();
    }


    /**
     * 获取当前用户IP
     *
     * @return
     */
    public static String getUserIp() {
        Authentication authentication = getAuthentication();
        if (authentication == null) {
            return "";
        }
        Object details = authentication.getDetails();
        if (!(details instanceof WebAuthenticationDetails)) {
            return "";
        }
        WebAuthenticationDetails webDetails = (WebAuthenticationDetails) details;
        return webDetails.getRemoteAddress();
    }

    /**
     * 获取客户端ID
     *
     * @return
     */
    public static String getClientId() {
        Authentication authentication = getAuthentication();
        if (authentication instanceof org.springframework.security.oauth2.provider.OAuth2Authentication) {
            org.springframework.security.oauth2.provider.OAuth2Authentication auth2Authentication = (org.springframework.security.oauth2.provider.OAuth2Authentication) authentication;
            return auth2Authentication.getOAuth2Request().getClientId();
        }
        return null;
    }

    /**
     * 判断用户是否拥有某角色
     *
     * @param roles
     * @return
     */
    public static boolean hasAnyRole(String... roles) {
        Authentication authentication = getAuthentication();
        if (authentication == null) {
            return false;
        }
        Collection<? extends GrantedAuthority> grantedAuthorities = authentication.getAuthorities();
        for (String role : roles) {
            for (GrantedAuthority authority : grantedAuthorities) {
                if (role.equals(authority.getAuthority())) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 保存用户信息到Context
     *
     * @param userDetails
     * @param request
     */
    public static void saveUserDetailsToContext(UserDetails userDetails,
                                                HttpServletRequest request) {
        PreAuthenticatedAuthenticationToken authentication = new PreAuthenticatedAuthenticationToken(
                userDetails, userDetails.getPassword(), userDetails.getAuthorities());
        if (request != null) {
            authentication.setDetails(new WebAuthenticationDetails(request));
        }
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    private static Authentication getAuthentication() {
        SecurityContext context = SecurityContextHolder.getContext();
        if (context == null) {
            return null;
        }
        return context.getAuthentication();
    }

}
