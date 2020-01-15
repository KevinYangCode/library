package xyz.jianzha.library.config.shiro;

import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * shiro的配置类
 *
 * @author Y_Kevin
 * @date 2020-01-11 13:46
 */
@Configuration
public class ShiroConfig {
    /**
     * 创建Realm
     */
    @Bean("ShiroRealm")
    public ShiroRealm shiroRealm(HashedCredentialsMatcher hashedCredentialsMatcher) {
        ShiroRealm shiroRealm = new ShiroRealm();
        shiroRealm.setCredentialsMatcher(hashedCredentialsMatcher);
        return shiroRealm;
    }

    /**
     * 创建DefaultWebSecurityManager
     */
    @Bean("securityManager")
    public SecurityManager getSecurityManager(@Qualifier("ShiroRealm") ShiroRealm shiroRealm) {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        // 关联realm
        securityManager.setRealm(shiroRealm);
        return securityManager;
    }

    /**
     * 创建ShiroFilterFactorBean
     */
    @Bean
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(@Qualifier("securityManager") SecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        // 设置安全管理器
        shiroFilterFactoryBean.setSecurityManager(securityManager);

        /**
         *Shiro内置过滤器，可以实现权限相关的拦截器
         *  常用的过滤器：
         *      anon： 无需认证（登录） 可以访问
         *      authc： 必须认证才可以访问
         *      user：  如果使用rememberMe的功能才可以直接访问
         *      perms:  该资源必须得到权限才可以访问
         *      role：  该资源必须得到角色权限才可以访问
         */
        Map<String, String> filterMap = new LinkedHashMap<String, String>();
        // 静态资源>放行
        filterMap.put("/api/**", "anon");
        filterMap.put("/css/**", "anon");
        filterMap.put("/images/**", "anon");
        filterMap.put("/js/**", "anon");
        filterMap.put("/lib/**", "anon");

        // 验证码接口>放行
        filterMap.put("/captcha", "anon");
        // 登录接口>放行
        filterMap.put("/login", "anon");
        // 登录页面>放行
        filterMap.put("/page/login.html", "anon");
        // 登出
        filterMap.put("/shiro/logout", "logout");
        // druid后端监控录路径
        filterMap.put("/druid/**", "anon");
        //swaggerAPI管理入口
        filterMap.put("/swagger-ui.html", "anon");

        // 授权过滤器
        // 注意：当前授权拦截后，shiro会自动跳转到未授权页面
        filterMap.put("/page/system/**", "roles[admin]");

        filterMap.put("/**", "authc");

        // 修改调整登录页面(被拦截时跳转的页面)
        shiroFilterFactoryBean.setLoginUrl("/page/login.html");
        // 设置未授权提示页面
        shiroFilterFactoryBean.setUnauthorizedUrl("/page/noAuth.html");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterMap);

        return shiroFilterFactoryBean;
    }

    /**
     * 使用MD5加密
     *
     * @return
     */
    @Bean("hashedCredentialsMatcher")
    public HashedCredentialsMatcher hashedCredentialsMatcher() {
        HashedCredentialsMatcher matcher = new HashedCredentialsMatcher();
        // 散列算法:这里使用MD5算法;
        matcher.setHashAlgorithmName("MD5");
        // 散列的次数，比如散列两次，相当于 md5(md5(""));
        matcher.setHashIterations(1024);
        return matcher;
    }
}
