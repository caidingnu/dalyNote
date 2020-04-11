package com.oceam.utils;


import net.sf.jxls.exception.ParsePropertyException;
import net.sf.jxls.transformer.XLSTransformer;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * excel导出、下载实现方法
 */

public class ExcelExportUtil {

    /**
     * excel导出、下载实现方法
     *
     * @param templateFileName 模板xls或xlsx文件路径
     * @param list1            模板xls中对应要用到的集合;
     * @param list2            模板xls中对应要用到的集合;
     * @param serverFilePath   生成的xls或xlsx文件路径;
     * @param outExcelName     导出的的xls或xlsx文件名;
     * @date 2018年7月24日 下午6:29:14
     */
    public void createExcel(String templateFileName, List<?> list1, String outExcelName, HttpServletResponse response) {
        /* ********我们也可以使用相对路径来定位 读取模板文件,或放置生成的文件******** */

//        生成excel的临时目录
        String path = "./src/main/resources/excel";
        File temPath = new File(path);
        if (!temPath.exists()) {
            temPath.mkdirs();
        }
        String serverFilePath = path + "/临时表.xlsx";

        // 创建XLSTransformer对象
        XLSTransformer transformer = new XLSTransformer();
        Map<String, Object> beanParams = new HashMap<String, Object>();
        // 将要用到的list集合,按对应模板中的名字,放入map中
        beanParams.put("list1", list1);
        beanParams.put("cdn", "测试字符串");  //------------------------直接放字符串   excel中${cdn}即可取值
        try {
            // 生成Excel文件
            transformer.transformXLS(templateFileName, beanParams, serverFilePath);
//            ******************************************下载******************************************
            response.reset();
            response.setHeader("Content-disposition", "attachment;success=true;filename =" + URLEncoder.encode(outExcelName, "utf-8"));
            BufferedInputStream bis = null;
            BufferedOutputStream bos = null;
            OutputStream fos = null;
            InputStream fis = null;
            fis = new FileInputStream(serverFilePath);
            bis = new BufferedInputStream(fis);
            fos = response.getOutputStream();
            bos = new BufferedOutputStream(fos);
            // 弹出下载对话框
            int bytesRead = 0;
            byte[] buffer = new byte[819200];
            while ((bytesRead = bis.read(buffer, 0, 819200)) != -1) {
                bos.write(buffer, 0, bytesRead);
            }
            bos.flush();
            fis.close();
            bis.close();
            fos.close();
            bos.close();

            // 清空服务端生成的excel文件，节约空间
            File[] files = temPath.listFiles();
            for (File file : files) {
                file.delete();
            }

        } catch (ParsePropertyException | InvalidFormatException | IOException e) {
            e.printStackTrace();
        }
    }
}

