package com.xf.service;

import com.xf.BaseSpringTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class SystemdictionarydetailServiceTest extends BaseSpringTest {
    @Autowired
    private ISystemdictionarydetailService systemdictionarydetailService;

    @Test
    public void testFindAll()throws Exception{
        systemdictionarydetailService.findAll().forEach(e-> System.out.println(e));
    }

}
