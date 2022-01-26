package com.xf.poi;

import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.afterturn.easypoi.excel.annotation.ExcelEntity;
import cn.afterturn.easypoi.excel.annotation.ExcelTarget;

import java.util.Date;

@ExcelTarget("emp")
public class POIEmployee {

    private Long id;
    @Excel(name="用户名")
    private String name;
    @Excel(name="邮箱",width = 20)
    private String email;
    @Excel(name = "性别",replace = { "男_true", "女_false" })
    private Boolean sex = true;
    @Excel(name = "出生日期",format = "yyyy-MM-dd HH:mm:ss")
    private Date bornDate = new Date();

    @Excel(name = "头像", type = 2 ,width = 10 , height = 20,savePath = "image/upload")
    private String headImage;

    @ExcelEntity
    private POIDepartment department;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Boolean getSex() {
        return sex;
    }

    public void setSex(Boolean sex) {
        this.sex = sex;
    }

    public Date getBornDate() {
        return bornDate;
    }

    public void setBornDate(Date bornDate) {
        this.bornDate = bornDate;
    }

    public String getHeadImage() {
        return headImage;
    }

    public void setHeadImage(String headImage) {
        this.headImage = headImage;
    }

    public POIDepartment getDepartment() {
        return department;
    }

    public void setDepartment(POIDepartment department) {
        this.department = department;
    }

    @Override
    public String toString() {
        return "POIEmployee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", sex=" + sex +
                ", bornDate=" + bornDate +
                ", headImage='" + headImage + '\'' +
                ", department=" + department +
                '}';
    }
}
