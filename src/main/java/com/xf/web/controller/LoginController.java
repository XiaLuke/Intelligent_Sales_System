package com.xf.web.controller;

import com.xf.common.JsonResult;
import com.xf.common.UserContext;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class LoginController {


    @RequestMapping(value="/login",method = RequestMethod.GET)
    public String index(){
        return "login";
    }

    @RequestMapping(value="/login",method = RequestMethod.POST)
    @ResponseBody
    public JsonResult login(String username, String password){
        //1.拿到subject(当前用户)
        Subject subject = SecurityUtils.getSubject();
        //2.如果没有登录，放它登录
        if(!subject.isAuthenticated()){
            UsernamePasswordToken token = new UsernamePasswordToken(username,password);
            try {
                subject.login(token);
            } catch (UnknownAccountException e) {
                e.printStackTrace();
                return  new JsonResult(false,"用户名出错");
            } catch (IncorrectCredentialsException e) {
                e.printStackTrace();
                return  new JsonResult(false,"用户名或者密码错误");
            }catch (AuthenticationException e) {
                System.out.println("神秘错误");
                return  new JsonResult(false,"神秘错误");
            }
        }
        //把当前登录用户放到session中
        UserContext.putUser();
        //成功到主页面
        return new JsonResult();
    }

    //登出:注销功能
    @RequestMapping("/logout")
    public String logout(){
        //1.拿到主体完成登出
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        //2.跳回到登录页面
        return "redirect:/login";
    }
}
