package com.xf.service;

import com.xf.BaseSpringTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class RoleServiceTest extends BaseSpringTest {
    @Autowired
    private IRoleService roleService;

    @Test
    public void testFindAll()throws Exception{
        roleService.findAll().forEach(e-> System.out.println(e));
    }

}
