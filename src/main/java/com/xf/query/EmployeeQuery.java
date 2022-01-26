package com.xf.query;

import com.xf.domain.Employee;
import com.github.wenhao.jpa.Specifications;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;

/**
 * 只用于Employee的查询条件
 */
public class EmployeeQuery extends BaseQuery {

    private String username;
    private String email;
    private Integer age;
    //部门的接收
    private Long departmentId;

    //返回咱们的查询条件 where username = ?
    @Override
    public Specification createSpec(){
        /**
         * like方法两个参数
         *  1.查询的字段
         *  2.这个字段条件对应的值
         * like方法三个参数
         *  1.boolean false，这个查询不执行
         *  2.查询的字段
         *  3.这个字段条件对应的值
         */
        Specification<Employee> spec = Specifications.<Employee>and()
                .like(StringUtils.isNotBlank(username),"username", "%"+username+"%")
                .like(StringUtils.isNotBlank(email),"email", "%"+email+"%")
                .gt(age!=null,"age", age)
                .eq(departmentId!=null,"department.id", departmentId)
                .build();
        return spec;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Long getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Long departmentId) {
        this.departmentId = departmentId;
    }
}
