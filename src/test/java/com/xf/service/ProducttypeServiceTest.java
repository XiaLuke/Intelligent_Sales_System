package com.xf.service;

import com.xf.BaseSpringTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class ProducttypeServiceTest extends BaseSpringTest {
    @Autowired
    private IProducttypeService producttypeService;

    @Test
    public void testFindAll()throws Exception{
        producttypeService.findAll().forEach(e-> System.out.println(e));
    }

}
