package com.xf.service;

import com.xf.BaseSpringTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class DeptServiceTest extends BaseSpringTest {
    @Autowired
    private IDeptService deptService;

    @Test
    public void testFindAll()throws Exception{
        deptService.findAll().forEach(e-> System.out.println(e));
    }

}
