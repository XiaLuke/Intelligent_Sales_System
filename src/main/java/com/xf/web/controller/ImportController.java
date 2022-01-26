package com.xf.web.controller;

import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import cn.afterturn.easypoi.excel.entity.result.ExcelImportResult;
import com.xf.common.EmployeeExcelVerifyHandler;
import com.xf.domain.Employee;
import com.xf.service.IDepartmentService;
import com.xf.service.IEmployeeService;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

@Controller
@RequestMapping("/import")
public class ImportController {
    @Autowired
    private IEmployeeService employeeService;
    @Autowired
    private IDepartmentService departmentService;
    @Autowired
    private EmployeeExcelVerifyHandler excelVerifyHandler;

    @RequestMapping("/index")
    public String index(){
        return "import";
    }

    @RequestMapping("/empxlsx")
    public String empxlsx(MultipartFile empFile, HttpServletResponse response) throws Exception {
        //System.out.println(empFile);
        InputStream inputStream = empFile.getInputStream();
        //设置基本参数配置
        ImportParams params = new ImportParams();
        //要求导入的时候做验证
        params.setNeedVerfiy(true);
        //设置自定义的验证处理器
        params.setVerifyHandler(excelVerifyHandler);
        // params.setTitleRows(1);
        //params.setHeadRows(1);
        //导入excel(还可以做得更多)
        ExcelImportResult<Employee> result = ExcelImportUtil.importExcelMore(
                inputStream,
                Employee.class, params);
        //获取到引入成功的数据
        List<Employee> list = result.getList();
        list.forEach(e->{
            System.out.println(e);
            //成功的保存到数据库中
        });
        System.out.println("===============================");
        //获到到失败的数据 -> 导出到前台
        /*
        List<Employee> failList = result.getFailList();
        failList.forEach(e->{
            System.out.println(e);
        });
        */
        if(result.isVerfiyFail()) {
            //错误的文件
            Workbook wb = result.getFailWorkbook();
            response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"); //mime类型
            response.setHeader("Content-disposition", "attachment;filename=error.xlsx");
            response.setHeader("Pragma", "No-cache");//设置不要缓存
            OutputStream ouputStream = response.getOutputStream();
            wb.write(ouputStream);
            ouputStream.flush();
            ouputStream.close();
        }
        return "import";
    }


    /*
    不做验证的版本
    @RequestMapping("/empxlsx")
    public String empxlsx(MultipartFile empFile) throws Exception {
        //System.out.println(empFile);
        InputStream inputStream = empFile.getInputStream();
        //设置基本参数配置
        ImportParams params = new ImportParams();
        // params.setTitleRows(1);
        //params.setHeadRows(1);
        List<Employee> list = ExcelImportUtil.importExcel(
                inputStream,
                Employee.class, params);

        list.forEach(e-> {
            //设置初始密码
            e.setPassword("123456");
            //1.拿到部门名称
            String deptName = e.getDepartment().getName();
            //2.拿到部门名称拿到部门
            Department dept = departmentService.findByName(deptName);
            //3.把部门放进去
            e.setDepartment(dept);
            employeeService.save(e);
        });
        return "import";
    }
    */
}
