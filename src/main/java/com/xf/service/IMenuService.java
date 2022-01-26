package com.xf.service;

import com.xf.domain.Menu;

import java.util.List;

/**
 */
public interface IMenuService extends IBaseService<Menu,Long>{

    List<Menu> findByUser();
}
