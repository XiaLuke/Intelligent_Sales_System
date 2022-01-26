package com.xf.common;

import cn.afterturn.easypoi.excel.entity.result.ExcelVerifyHandlerResult;
import cn.afterturn.easypoi.handler.inter.IExcelVerifyHandler;
import com.xf.domain.Employee;
import com.xf.service.IEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EmployeeExcelVerifyHandler implements IExcelVerifyHandler<Employee> {

    @Autowired
    private IEmployeeService employeeService;

    @Override
    public ExcelVerifyHandlerResult verifyHandler(Employee employee) {
        ExcelVerifyHandlerResult result = new ExcelVerifyHandlerResult(true);
        //拿到相应的用户名
        String username = employee.getUsername();
        if(!employeeService.checkUsername(username)){
            result.setSuccess(false);
            result.setMsg("用户名重复了");
        }
        return result;
    }
}
