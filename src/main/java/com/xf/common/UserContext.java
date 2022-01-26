package com.xf.common;

import com.xf.domain.Employee;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;

/**
 * 操作用户的工具类
 */
public class UserContext {

    public static final String LOGINUSER ="loginUser";

    public static void putUser(){
        Subject subject = SecurityUtils.getSubject();
        //获取到session
        Session session = subject.getSession();
        //获取到相应的主体
        Employee employee = (Employee) subject.getPrincipal();
        //将当前登录用户放到session中
        session.setAttribute(LOGINUSER,employee);
    }

    public static Employee getUser(){
        Subject subject = SecurityUtils.getSubject();
        //获取到session
        Session session = subject.getSession();
        return (Employee) session.getAttribute(LOGINUSER);
    }

}
