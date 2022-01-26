package com.xf.common;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.junit.Test;

import java.io.FileInputStream;
import java.io.FileOutputStream;

public class ExcelTest {

    /**
     * 创建excel(导出 = 创建+ 下载)
     * @throws Exception
     */
    @Test
    public void testCreateExcel()throws Exception{
        //1.在内存中创建了一个Excel文件
        SXSSFWorkbook wb = new SXSSFWorkbook();

        //2.在excel文件中创建一张表
        Sheet sheet = wb.createSheet("99乘法表");

        //3.开始创建行
        for (int i = 1; i <= 9; i++) {
            Row row = sheet.createRow(i);
            //4.开始创建列
            for (int j = 1; j <= 9; j++) {
                Cell cell = row.createCell(j);
                //5.cell格子中装相应的数据
                cell.setCellValue(i+"*"+j+"="+(i*j));
            }
        }
        FileOutputStream out = new FileOutputStream("99.xlsx");
        wb.write(out);
        out.close();

    }

    /**
     * 读取excel(导入 = 上传+解析)
     * @throws Exception
     */
    @Test
    public void testReadExcel()throws Exception{
        // 1.获取到相应的Excel文件(封装成对象，放到内存中)
        Workbook wb = WorkbookFactory.create(new FileInputStream("emp-poi.xlsx"));
        // 2.获取到第一张表
        Sheet sheet = wb.getSheetAt(0);
        // 3.获取到对应的行
        //  3.1 确定当前这张表有多少行数据
        int lastRowNum = sheet.getLastRowNum();
        // 3.2 遍历拿到对应的行
        for (int i = 1; i <= lastRowNum; i++) {
            Row row = sheet.getRow(i);
            //4.获到到这一行的所有列数据
            // 4.1 确定当前这一行有多少个单元格数据
            short lastCellNum = row.getLastCellNum();
            // 4.2 遍历拿到相应的单元格
            for (int j = 0; j < lastCellNum; j++) {
                //4.3 拿到单元格的值
                Cell cell = row.getCell(j);
                String stringCellValue = cell.getStringCellValue();
                System.out.print(stringCellValue+"  ");
            }
            System.out.println();
        }

    }
}
