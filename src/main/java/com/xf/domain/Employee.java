package com.xf.domain;

import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.afterturn.easypoi.excel.annotation.ExcelEntity;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "employee")
public class Employee extends BaseDomain {

    @Excel(name = "用户名")
    @NotNull(message = "用户名不能为空")
    private String username;
    private String password;
    @Excel(name = "邮件",width = 25)
    @NotNull
    private String email;
    @Excel(name = "年龄")
    @Max(value = 80,message = "年龄最大值不能超过80")
    private Integer age;
    //头像
    @Excel(name = "头像",type = 2,width = 10 , height = 20)
    private String headImage;

    //设置抓取策略为懒加载
    //  Department的一个代理->子类，JPA为这个子类加了很多东西，这些东西对它自己有用，但是对SpringMVC返回json是无用，甚至是有害的
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "department_id")
    //@JsonIgnoreProperties(value={"hibernateLazyInitializer","handler","fieldHandler"})
    @ExcelEntity
    private Department department;

    //员工与角色的关系配置
    @ManyToMany
    @JoinTable(name = "employee_role",
            joinColumns = @JoinColumn(name = "employee_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private List<Role> roles = new ArrayList<>();

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public String getHeadImage() {
        return headImage;
    }

    public void setHeadImage(String headImage) {
        this.headImage = headImage;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", age=" + age +
                ", id=" + id +
                '}';
    }
}
