package com.xf.web.shiro;

import com.xf.common.UserContext;
import com.xf.domain.Employee;
import com.xf.service.IEmployeeService;
import com.xf.service.IPermissionService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.Set;

/**
 * 项目的自定义realm
 */
public class AiSellRealm extends AuthorizingRealm {

    @Autowired
    private IEmployeeService employeeService;
    @Autowired
    private IPermissionService permissionService;

    @Override
    public String getName() {
        return "aiSellRealm";
    }

    //权限认证
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        //1.拿到登录主体
        Employee employee = UserContext.getUser();
        //2.根据登录主体拿到对应的角色与权限
        //Set<String> roles = getRoles(employee.getUsername());
        Set<String> permissions = permissionService.findPersByUser(employee.getId());
        //3.返回权限认证对象（要把角色和权限放进去）
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        //authorizationInfo.setRoles(roles);
        authorizationInfo.setStringPermissions(permissions);
        return authorizationInfo;
    }

    //根据用户名拿到角色
    public Set<String> getRoles(String sername){
        Set<String> roles = new HashSet<String>();
        roles.add("admin");
        roles.add("it");
        return roles;
    }
    //根据用户名拿到权限
    public Set<String> getPermissions(String sername){
        Set<String> perms = new HashSet<String>();
        //当前登录用户所有的权限
//        perms.add("employee:*");
//        perms.add("dept:index");
        return perms;
    }
    //登录认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        //1.拿到令牌
        UsernamePasswordToken token = (UsernamePasswordToken)authenticationToken;
        //2.根据令牌获到username -> 拿到用户对象(没有对象返回null)
        String username = token.getUsername();
        Employee loginUser = employeeService.findByUsername(username);
        if(loginUser==null){
            return null;
        }
        //3.如果有密码就登录
        ByteSource salt = ByteSource.Util.bytes("itsource");
        // 把当前登录的对象变成主体对象
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(loginUser,loginUser.getPassword(),salt,getName());
        return authenticationInfo;
    }


    /**
     * 123 : 202cb962ac59075b964b07152d234b70
     * 123 加密10次: 5371007260db2b98e3f7402395c45f28
     * 123 加密10次 加盐(itsource): d5a3fedf6c59c2ecbe7f7a6c1a22da37
     */
    public String getByName(String username){
        if("admin".equals(username)){
            return "d5a3fedf6c59c2ecbe7f7a6c1a22da37";
        }else if("zhao".equals(username)){
            return  "123";
        }
        return null;
    }
}
