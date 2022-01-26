package com.xf.service;

import com.xf.BaseSpringTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class SupplierServiceTest extends BaseSpringTest {
    @Autowired
    private ISupplierService supplierService;

    @Test
    public void testFindAll()throws Exception{
        supplierService.findAll().forEach(e-> System.out.println(e));
    }

}
