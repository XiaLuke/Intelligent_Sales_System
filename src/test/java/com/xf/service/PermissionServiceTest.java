package com.xf.service;

import com.xf.BaseSpringTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class PermissionServiceTest extends BaseSpringTest {
    @Autowired
    private IPermissionService permissionService;

    @Test
    public void testFindAll()throws Exception{
        permissionService.findAll().forEach(e-> System.out.println(e));
    }

}
