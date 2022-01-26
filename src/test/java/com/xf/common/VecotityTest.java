package com.xf.common;

import com.xf.domain.Employee;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.junit.Test;

import java.io.FileWriter;
import java.io.StringWriter;

public class VecotityTest {

    //测试默认就是项目根目录
    //数据 + 模板 = 输入文本
    @Test
    public void testHello()throws Exception{
        //创建一个Velocity引擎
        VelocityEngine ve = new VelocityEngine();
        //获到到相应的模板
        Template template = ve.getTemplate("template/hello.vm","UTF-8");
        //创建模板上下文(装数据的东西)
        VelocityContext velocityContext = new VelocityContext();
        velocityContext.put("username","二狗");
        //准备输出的位置
        StringWriter stringWriter = new StringWriter();
        //数据 + 模板 = 输入文本
        template.merge(velocityContext, stringWriter);

        System.out.println(stringWriter.toString());
    }

    //测试默认就是项目根目录
    //数据 + 模板 = 输入文本
    @Test
    public void testHelloFile()throws Exception{
        //创建一个Velocity引擎
        VelocityEngine ve = new VelocityEngine();
        //获到到相应的模板
        Template template = ve.getTemplate("template/hello.vm","UTF-8");
        //创建模板上下文(装数据的东西)
        VelocityContext velocityContext = new VelocityContext();
        velocityContext.put("username","二狗");
        //准备对象，放到上下文中
        Employee employee = new Employee();
        employee.setUsername("三狗");
//        employee.setAge(45);
        velocityContext.put("employee",employee);

        //准备输出的位置
        FileWriter fileWriter = new FileWriter("template/hello.html");
        //数据 + 模板 = 输入文本
        template.merge(velocityContext, fileWriter);

        fileWriter.close();
    }
}
