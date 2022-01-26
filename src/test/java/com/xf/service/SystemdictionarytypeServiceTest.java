package com.xf.service;

import com.xf.BaseSpringTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class SystemdictionarytypeServiceTest extends BaseSpringTest {
    @Autowired
    private ISystemdictionarytypeService systemdictionarytypeService;

    @Test
    public void testFindAll()throws Exception{
        systemdictionarytypeService.findAll().forEach(e-> System.out.println(e));
    }

}
