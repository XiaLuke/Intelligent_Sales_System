package com.xf.web.shiro;

import com.xf.domain.Permission;
import com.xf.service.IPermissionService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * /s/login.jsp = anon
     /login = anon
     /employee/index = perms[employee:index]
     /dept/index = perms[dept:index]
     /** = authc
 */
public class FilterChainDefinitionMapFactory {

    @Autowired
    private IPermissionService permissionService;


    public Map<String,String> builderFilterChainDefinitionMap(){
        //使用LinkedHashMap -> 保证是有顺序的
        Map<String,String> maps = new LinkedHashMap<>();
        //在maps中加上数据
        //设置放行
        maps.put("/s/login.jsp","anon");
        maps.put("/login","anon");
        maps.put("/easyui/**","anon");
        maps.put("/images/**","anon");
        maps.put("/js/**","anon");
        maps.put("/json/**","anon");
        maps.put("/login","anon");
        maps.put("*.js","anon");
        maps.put("*.css","anon");
        maps.put("*.jpg","anon");
        //设置权限拦截 (所有需要做判断判断的数据)
//        maps.put("/employee/index","perms[employee:index]");
//        maps.put("/dept/index","perms[dept:index]");
//        maps.put("/resource/index","perms[resource:index]");
        //1.获取到所有权限
        List<Permission> perms = permissionService.findAll();
        perms.forEach(p->{
            maps.put(p.getUrl(),"aiSellPerms["+p.getSn()+"]");
        });
        //设置所有拦截
        maps.put("/**","authc");
        return maps;
    }
}
