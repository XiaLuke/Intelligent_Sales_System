package com.xf.service;

import com.xf.BaseSpringTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class MenuServiceTest extends BaseSpringTest {
    @Autowired
    private IMenuService menuService;

    @Test
    public void testFindAll()throws Exception{
        menuService.findAll().forEach(e-> System.out.println(e));
    }

}
