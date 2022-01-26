package com.xf.web.controller;

import com.xf.domain.*;
import com.xf.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * 就一个工具:返回所有的下拉数据
 */
@Controller
@RequestMapping("/util")
public class UtilController {

    @Autowired
    private IEmployeeService employeeService;
    @Autowired
    private IDepartmentService departmentService;
    @Autowired
    private IMenuService menuService;
    @Autowired
    private ISystemdictionarydetailService systemdictionarydetailService;
    @Autowired
    private IProducttypeService producttypeService;
    @Autowired
    private ISupplierService supplierService;
    @Autowired
    private IProductService productService;

    @RequestMapping("/depts")
    @ResponseBody
    public List<Department> findAllDepts(){
        return departmentService.findAll();
    }


    @RequestMapping("/findMenusByUser")
    @ResponseBody
    public List<Menu> findMenusByUser(){
        return menuService.findByUser();
    }


    //拿到所有品牌
    @RequestMapping("/findAllBrand")
    @ResponseBody
    public List<Systemdictionarydetail> findAllBrand(){
        return systemdictionarydetailService.findAllBrand();
    }

    //拿到所有单位
    @RequestMapping("/findAllUnit")
    @ResponseBody
    public List<Systemdictionarydetail> findAllUnit(){
        return systemdictionarydetailService.findAllUnit();
    }

    @RequestMapping("/findChildren")
    @ResponseBody
    public List<Producttype> findChildren(){
       return producttypeService.findChildren();
    }

    @RequestMapping("/findAllSupplier")
    @ResponseBody
    public List<Supplier> findAllSupplier(){
        return supplierService.findAll();
    }
    @RequestMapping("/findBuyer")
    @ResponseBody
    public List<Employee> findBuyer(){
        return employeeService.findBuyer();
    }
    @RequestMapping("/findProducts")
    @ResponseBody
    public List<Product> findProducts(){
        return productService.findAll();
    }
}
