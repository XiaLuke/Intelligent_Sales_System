package com.xf.poi;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import org.apache.poi.ss.usermodel.Workbook;
import org.junit.Test;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

public class EasyPoiTest {

    @Test
    public void testExport()throws Exception{
        POIDepartment d1 = new POIDepartment();
        d1.setId(1L);
        d1.setName("涂山");
        d1.setAddress("涂山路32号");
        POIDepartment d2 = new POIDepartment();
        d2.setId(2L);
        d2.setName("傲来国");
        d2.setAddress("花果山100号");
        //模拟相应的数据
        List<POIEmployee> list = new ArrayList<>();
        POIEmployee emp01 = new POIEmployee();
        emp01.setId(1L);
        emp01.setName("王权富贵");
        emp01.setEmail("gue@qq.com");
        emp01.setSex(true);
        emp01.setHeadImage("1.jpg");
        emp01.setDepartment(d1);
        POIEmployee emp02 = new POIEmployee();
        emp02.setId(2L);
        emp02.setName("小月初");
        emp02.setEmail("chu@qq.com");
        emp02.setSex(false);
        emp02.setHeadImage("2.jpg");
        emp02.setDepartment(d2);
        list.add(emp01);
        list.add(emp02);

        /**
         * ExportParams:当前导出的excel的属性
         *  title:一级标题
         *  secondTitle:二级标题
         *  sheetName:表名称
         * POIEmployee.class：导出的数据类型
         * list:导出的值
         */
        Workbook wb = ExcelExportUtil.exportExcel(new ExportParams(),
                POIEmployee.class, list);

        //将文件创建出来
        FileOutputStream out = new FileOutputStream("emp.xlsx");
        wb.write(out);
        out.close();
    }

    @Test
    public void test02()throws Exception{
        List<POIDepartment> list = new ArrayList<>();
        POIDepartment d1 = new POIDepartment();
        d1.setId(1L);
        d1.setName("涂山");
        d1.setAddress("涂山路32号");
        list.add(d1);

        /**
         */
        Workbook wb = ExcelExportUtil.exportExcel(new ExportParams(),
                POIDepartment.class, list);

        //将文件创建出来
        FileOutputStream out = new FileOutputStream("dept.xlsx");
        wb.write(out);
        out.close();
    }


    @Test
    public void testImport()throws Exception{
        ImportParams params = new ImportParams();
       // params.setTitleRows(1);
        //params.setHeadRows(1);

        List<POIEmployee> list = ExcelImportUtil.importExcel(
                new File("emp.xlsx"),
                POIEmployee.class, params);

        list.forEach(e-> System.out.println(e));
    }
}
