package com.xf.service.impl;

import com.xf.common.Md5Utils;
import com.xf.domain.Employee;
import com.xf.repository.EmployeeRepository;
import com.xf.service.IEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class EmployeeServiceImpl extends BaseServiceImpl<Employee,Long> implements IEmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    /**
     * 添加{employee:{id:1,pwd:123}}或者修改{employee:{id:1,pwd:d563ad18fb8e2067eaee76ca27f3e8a3}}
     * @param employee
     */
    @Override
    @Transactional
    public void save(Employee employee) {
        if(employee.getId()==null) {
            //只有添加做密码加载密(注:修改是不改密码的)
            employee.setPassword(Md5Utils.createMd5Pwd(employee.getPassword()));
        }
        employeeRepository.save(employee);
    }

    /**
     * 如果得到的结果大于0，返回false -> 用户名已经被占用
     * @param username
     * @return
     */
    @Override
    public Boolean checkUsername(String username) {
        return  !(employeeRepository.getCountByUsername(username)>0);
    }

    @Override
    public Employee findByUsername(String username) {
        return employeeRepository.findByUsername(username);
    }

    @Override
    public List<Employee> findBuyer() {
        return employeeRepository.findByDepartment_Name("采购部");
    }
}
