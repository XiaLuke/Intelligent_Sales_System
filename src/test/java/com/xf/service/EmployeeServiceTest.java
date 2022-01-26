package com.xf.service;


import com.xf.BaseSpringTest;
import com.xf.common.Md5Utils;
import com.xf.domain.Employee;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class EmployeeServiceTest extends BaseSpringTest {
    @Autowired
    private IEmployeeService employeeService;

    @Test
    public void testFindAll() throws Exception {
        employeeService.findAll().forEach(e -> System.out.println(e));
    }

    @Test
    public void testSave() throws Exception {
        Employee employee = new Employee();
        employee.setUsername("haha");

        employeeService.save(employee);
    }

    //加密方案是MD5 盐值:itsource  迭代次数:10次
    @Test
    public void testUpdatePwd()throws Exception{
        employeeService.findAll().forEach(e->{
            String pwd = Md5Utils.createMd5Pwd(e.getUsername());
            e.setPassword(pwd);
            employeeService.save(e);
        });
    }

}
