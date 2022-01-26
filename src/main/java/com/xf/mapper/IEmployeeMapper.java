package com.xf.mapper;

import com.xf.domain.Employee;
import com.xf.query.BaseQuery;
import org.springframework.data.domain.Page;

import java.util.List;

public interface IEmployeeMapper {
    Boolean checkUsername(String username);
    //根据用户名拿到对应的员工
    Employee findByUsername(String username);

    List<Employee> findBuyer();
    void save(Employee employee);
    void delete(Long id);
    Employee findOne(Long id);
    List<Employee> findAll();
    //根据Query拿到分页对象(分页)
    Page findPageByQuery(BaseQuery baseQuery);
    //根据Query拿到对应的所有数据(不分页)
    List<Employee> findByQuery(BaseQuery baseQuery);
    //根据jpql与对应的参数拿到数据
    List findByJpql(String jpql,Object... values);

}
