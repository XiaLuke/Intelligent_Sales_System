package com.xf.service.impl;

import com.xf.common.UserContext;
import com.xf.domain.Employee;
import com.xf.domain.Menu;
import com.xf.repository.MenuRepository;
import com.xf.service.IMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 */
@Service
public class MenuServiceImpl extends BaseServiceImpl<Menu,Long> implements IMenuService {

    @Autowired
    private MenuRepository menuRepository;

    @Override
    public List<Menu> findByUser() {
        //1.拿到当前登录用户
        Employee loginUser = UserContext.getUser();
        //2.根据当前登录用户拿到他的所有菜单
        List<Menu> menuList = menuRepository.findByUser(loginUser.getId());
        //①.准备父菜单的集合
        List<Menu> parentMenus = new ArrayList<>();
        //②.遍历对应的子菜单
        for (int i = 0; i < menuList.size(); i++) {
            //③.拿到相应的子菜单,并且获取到它的父亲
            Menu menu = menuList.get(i);
            Menu parent = menu.getParent();
            //④.判断父菜单集合中是否已经有我们查的这个父菜单了
            if(!parentMenus.contains(parent)){
                //不包含的话把父菜单放到集合中
                parentMenus.add(parent);
            }
            //⑤.把子菜单放到父菜单中
            parent.getChildren().add(menu);
        }

        return parentMenus;
    }
}
