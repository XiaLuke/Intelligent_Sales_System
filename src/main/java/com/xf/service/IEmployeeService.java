package com.xf.service;

import com.xf.domain.Employee;

import java.util.List;

/**
 * 现在虽然没有方法，但是以后肯定会有一个Employee特有的业务
 */
public interface IEmployeeService extends IBaseService<Employee,Long> {

    Boolean checkUsername(String username);
    //根据用户名拿到对应的员工
    Employee findByUsername(String username);

    List<Employee> findBuyer();
}
