package com.xf.service;

import com.xf.BaseSpringTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class DepartmentServiceTest extends BaseSpringTest{

    @Autowired
    private IDepartmentService departmentService;

    @Test
    public void testFindAll()throws Exception{
        departmentService.findAll().forEach(d-> System.out.println(d));
    }
}
