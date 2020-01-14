package xyz.jianzha.library.utils;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import xyz.jianzha.library.entity.User;

/**
 * 认证成功用户的信息
 *
 * @author Y_Kevin
 * @date 2020-01-14 02:03
 */
public class AuthUtils {
    public static User authInfo() {
        Subject subject = SecurityUtils.getSubject();
        if (subject.isAuthenticated()) {
            return (User) subject.getPrincipal();
        } else {
            return User.builder().stuid("unknown user").build();
        }
    }
}
