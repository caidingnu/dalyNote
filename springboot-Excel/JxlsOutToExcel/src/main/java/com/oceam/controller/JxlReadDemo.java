package com.oceam.controller;

import java.io.File;
import java.io.IOException;
import java.util.*;

import com.oceam.entity.Excel;
import com.oceam.mapper.ExcelMapper;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * Poi写Excel
 *
 * @author jianggujin
 */
@RestController
public class JxlReadDemo {
    @Resource
    private ExcelMapper excelMapper;

    @RequestMapping("/")
    public void main() throws IOException,
            InvalidFormatException {
        File xlsFile = new File("C:\\Users\\cdn\\Desktop\\qq2.xls");
        // 获得工作簿
        Workbook workbook = WorkbookFactory.create(xlsFile);
        // 获得工作表个数
        int sheetCount = workbook.getNumberOfSheets();
        // 遍历工作表
        for (int i = 0; i < sheetCount; i++) {
            Sheet sheet = workbook.getSheetAt(i);
            // 获得行数
            int rows = sheet.getLastRowNum() + 1;
            // 获得列数，先获得一行，在得到改行列数
            Row tmp = sheet.getRow(0);
            if (tmp == null) {
                continue;
            }
            int cols = tmp.getPhysicalNumberOfCells();
            // 读取数据
            List<String> list = new ArrayList<>();
            for (int row = 0; row < rows; row++) {
                String a = "";
                Row r = sheet.getRow(row);
                for (int col = 0; col < cols; col++) {
//                    把单元格内的数值类型设置为String
                    r.getCell(col).setCellType(Cell.CELL_TYPE_STRING);
//                    System.out.printf("%10s", r.getCell(col).getStringCellValue());   //此处的"%10s"是保持原格式输出
                    a = a + r.getCell(col).getStringCellValue() + "-";
                }
                list.add(a);

            }
            System.out.println(list);
            for (int j = 1; j < list.size(); j++) {
                String[] b = list.get(j).split("-");
                Map<String, String> ma = new HashMap<>();
                Excel excel = new Excel();
                for (int h = 0; h < b.length; h++) {
                    switch (h) {
                        case 0:
                            excel.setXuhao(b[h]);
                            break;
                        case 1:
                            excel.setName(b[h]);
                            break;
                        case 2:
                            excel.setAuth(b[h]);
                            break;
                        case 3:
                            excel.setDes(b[h]);
                            break;
                        case 4:
                            excel.setSchool(b[h]);
                            break;
                        default:
                            break;
                    }

                }
                if (excelMapper.insertSelective(excel) == 1) {
                    System.out.println("插入成功");
                }
            }
        }
    }
}