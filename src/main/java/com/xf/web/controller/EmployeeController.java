package com.xf.web.controller;
import cn.afterturn.easypoi.entity.vo.NormalExcelConstants;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.afterturn.easypoi.excel.entity.enmus.ExcelType;
import com.xf.common.JsonResult;

import com.xf.common.UIPage;
import com.xf.domain.Employee;
import com.xf.query.EmployeeQuery;
import com.xf.service.IEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/employee")
public class EmployeeController extends BaseController {

    @Autowired
    private IEmployeeService employeeService;


    @RequestMapping("/index")
    public String index(){
        return "employee/employee";
    }


    @RequestMapping("/page")
    @ResponseBody
    public UIPage<Employee> page(EmployeeQuery query){
        Page page = employeeService.findPageByQuery(query);
        return new UIPage(page);
    }


    /**
     *ModelAttribute:路径访问Controller的每个方法，都会先执行它里面的代码
     */
    @ModelAttribute("editEmployee")
    public Employee beforeEdit(Long id,String cmd){
        if(id!=null && "_update".equals(cmd)){
            //修改才执行这个代码
            Employee dbEmployee = employeeService.findOne(id);
            //解决n-to-n的问题,把关联对象设置为null
            dbEmployee.setDepartment(null);
            return  dbEmployee;
        }
        return null;
    }

    //添加
    @RequestMapping("/save")
    @ResponseBody
    public JsonResult save(Employee employee){
        try {
            employeeService.save(employee);
            return new JsonResult();
        } catch (Exception e) {
            e.printStackTrace();
            return new JsonResult(false,e.getMessage());
        }
    }
    //修改
    @RequestMapping("/update")
    @ResponseBody
    public JsonResult update(@ModelAttribute("editEmployee")Employee employee){
        try {
            employeeService.save(employee);
            return new JsonResult();
        } catch (Exception e) {
            e.printStackTrace();
            return new JsonResult(false,e.getMessage());
        }
    }
    /**
     * 删除功能,前台要求返回{success:true/false,msg:xxx}
     * @return
     */
    @RequestMapping("/delete")
    @ResponseBody
    public JsonResult delete(Long id){
        try {
            employeeService.delete(id);
            return new JsonResult();
        } catch (Exception e) {
            e.printStackTrace();
            return new JsonResult(false,e.getMessage());
        }
    }

    @RequestMapping("/checkUsername")
    @ResponseBody
    public Boolean checkUsername(Long id,String username){
        //1.判断id是否存在
        if(id!=null){
            //2.根据id查询对象
            Employee dbEmp = employeeService.findOne(id);
            //3.判断名称是否相等
            if(username.equals(dbEmp.getUsername())){
                //4.如果相等，直接返回true
                return true;
            }
        }
        return employeeService.checkUsername(username);
    }

    @RequestMapping("/download")
    public String download(EmployeeQuery query,ModelMap map, HttpServletRequest request) {

        List<Employee> list = employeeService.findByQuery(query);

        //修改当前员工的头像路径 -> 真实路径
        String realPath = request.getServletContext().getRealPath("");
        list.forEach(e->{
            e.setHeadImage(realPath + e.getHeadImage());
        });
        ExportParams params = new ExportParams("员工数据", "测试", ExcelType.XSSF);
        //params.setFreezeCol(2); 冻结相应的列
        map.put(NormalExcelConstants.DATA_LIST, list); // 数据集合
        map.put(NormalExcelConstants.CLASS, Employee.class);//导出实体
        map.put(NormalExcelConstants.PARAMS, params);//参数
        map.put(NormalExcelConstants.FILE_NAME, "employee");//文件名称
        //return "easypoiExcelView";
        return NormalExcelConstants.EASYPOI_EXCEL_VIEW;//View名称
    }

}
