package xyz.jianzha.library.config.shiro;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import xyz.jianzha.library.entity.User;
import xyz.jianzha.library.service.UserService;

import javax.annotation.Resource;

/**
 * shiro自定义realm
 *
 * @author Y_Kevin
 * @date 2020-01-11 13:49
 */
public class ShiroRealm extends AuthorizingRealm {

    @Resource
    UserService userService;

    /**
     * 执行认证逻辑
     * 若配置里没有权限拦截，这方法不执行
     *
     * @param token
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        System.out.println("执行认证逻辑");

        // 编写shiro判断逻辑，判断用户名和密码
        // 1. 获取在控制层封装用户数据的AuthenticationToken中的值
        UsernamePasswordToken usertoken = (UsernamePasswordToken) token;
        // 2. 数据库查询
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("stuID", usertoken.getUsername());
        User user = userService.getOne(queryWrapper);
        // 3. 判断用户名
        if (!usertoken.getUsername().equals(user.getStuid())) {
            // 用户名不存在
            // return null-->shiro底层会抛出UnknownAccountException
            return null;
        }
        // 4.判断密码
        // shiro会通过下面去判断密码是否正确
        return new SimpleAuthenticationInfo(user, user.getPassword(), "");
    }

    /**
     * 执行授权逻辑
     *
     * @param principals
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        System.out.println("执行授权逻辑");
        // 给资源进行授权
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();

        // 到数据库查询当前登录用户的授权字符串
        // 获取当前登录用户(这里是上面执行登录用户时所返回的用户信息)
        Subject subject = SecurityUtils.getSubject();
        User user = (User) subject.getPrincipal();

        // 添加资源的授权字符串(应与ShiroFilterFactor拦截那里的数据一致)
        info.addStringPermission("user:add");

        // 这里可以通过角色联表查询对应的权限列表
        // info.addStringPermission(user.getRole());
        // info.addStringPermissions(new ArrayList<String>());
        return info;
    }
}
