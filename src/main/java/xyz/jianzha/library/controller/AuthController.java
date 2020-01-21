package xyz.jianzha.library.controller;

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.LineCaptcha;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import xyz.jianzha.library.entity.User;
import xyz.jianzha.library.utils.ResponseData;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Date;

/**
 * 使用shiro认证
 *
 * @author Y_Kevin
 * @date 2020-01-11 14:48
 */
@CrossOrigin(allowCredentials = "true", allowedHeaders = "*")
@RestController
public class AuthController {
    /**
     * 登录验证
     *
     * @param user    账号密码
     * @param code    验证码
     * @param session session
     * @return 成功失败信息
     */
    @PostMapping("/login")
    public ResponseData login(User user, String code, HttpSession session) {
        Date now = new Date();
        long codeTime = Long.parseLong(session.getAttribute("codeTime") + "");
        // 判断验证码是否超时
        if ((now.getTime() - codeTime) / 1000 / 60 <= 5) {
        // 获取验证码
        String codeSession = (String) session.getAttribute("code");
        if (code != null && code.equalsIgnoreCase(codeSession)) {
            /** 使用Shiro编写认证操作 */
            // 1. 获取Subject
            Subject subject = SecurityUtils.getSubject();
            // 测试当前的用户是否已经被认证，即是否已经登录
            if (!subject.isAuthenticated()) {
                // 2. 封装用户数据
                UsernamePasswordToken token = new UsernamePasswordToken(user.getStuid(), user.getPassword());
                // 3. 执行登录操作
                try {
                    subject.login(token);
                    // 登录成功！
                    return ResponseData.success("登陆成功！");
                } catch (UnknownAccountException e) {
                    // 登录失败：用户名不存在
                    return ResponseData.fail("用户不存在！");
                } catch (IncorrectCredentialsException e) {
                    // 登录失败：密码错误
                    return ResponseData.fail("密码不正确！");
                } catch (AuthenticationException e) {
                    // 登录失败：用户或密码不正确
                    return ResponseData.fail("用户或密码不正确！");
                }
            }
            return ResponseData.fail("登陆失败！");
        } else {
            return ResponseData.fail("验证码错误");
        }
        } else {
            return ResponseData.fail("验证码失效");
        }
    }

    /**
     * 获取验证码
     *
     * @param response 用于输出验证码图片
     * @param session  用于存储验证码的值
     * @throws IOException 流异常
     */
    @GetMapping(value =  "/captcha", produces = "image/png")
    public void getCode(HttpServletResponse response, HttpSession session) throws IOException {
        //定义图形验证码的长和宽
        LineCaptcha captcha = CaptchaUtil.createLineCaptcha(100, 38, 4, 20);
        // 得到code
        String code = captcha.getCode();
        // 放到session
        session.setAttribute("code", code);
        // 存储当前创建验证码的时间
        session.setAttribute("codeTime", System.currentTimeMillis());
        ServletOutputStream outputStream = response.getOutputStream();
        //图形验证码写出，可以写出到文件，也可以写出到流
        captcha.write(outputStream);
        outputStream.close();
    }

    /**
     * 退出登录
     *
     * @return JSON
     */
    @PostMapping("/logout")
    public ResponseData logout() {
        Subject subject = SecurityUtils.getSubject();
        if (subject.isAuthenticated()) {
            subject.logout();
        }
        return ResponseData.success("", "退出成功！");
    }

}
