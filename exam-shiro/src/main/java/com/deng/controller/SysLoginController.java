package com.deng.controller;

import com.deng.utils.R;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by hp on 2017/4/10.
 */
@Controller
public class SysLoginController {

    @ResponseBody
    @RequestMapping(value = "sys/login", method = RequestMethod.POST)
    public R login(String username, String password, HttpServletRequest request) {
        try {
            Subject subject = SecurityUtils.getSubject();
            if (password == null) {
                password = "admin";
            }
            password = new Sha256Hash(password).toHex();
            System.out.println(password);
            UsernamePasswordToken token = new UsernamePasswordToken(username, password);
            subject.login(token);
        }catch (UnknownAccountException e) {
            return R.error(e.getMessage());
        }catch (IncorrectCredentialsException e) {
            return R.error(e.getMessage());
        }catch (LockedAccountException e) {
            return R.error(e.getMessage());
        }catch (AuthenticationException e) {
            return R.error("账户验证失败");
        }
        return R.ok();
    }

    @ResponseBody
    @RequestMapping(value = "sys/login1", method = RequestMethod.POST)
    public R login1() {

        return R.ok();
    }
}
