package com.cdn.exce.controller;

import org.apache.poi.hssf.usermodel.*;
import org.junit.jupiter.api.Test;
import org.springframework.boot.system.ApplicationHome;
import org.springframework.util.StringUtils;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static java.lang.System.out;
import static javax.imageio.ImageIO.read;
import static org.apache.poi.ss.usermodel.Workbook.PICTURE_TYPE_JPEG;

/**
 * excel根据
 */
public class ExcelOut {

    public static HSSFWorkbook generateListContent(List<Map<String, Object>> mapList, String sheetName) {
        return generateListContent(mapList, sheetName, null);
    }

    public static HSSFWorkbook generateListContent(List<Map<String, Object>> mapList, String sheetName, HSSFWorkbook hssfWorkbook) {
        if (StringUtils.isEmpty(sheetName)) throw new RuntimeException("sheet名称不能为空");
        if (hssfWorkbook == null) hssfWorkbook = new HSSFWorkbook();
        if (mapList == null) return hssfWorkbook;

        /**
         * 创建一个sheet
         */
        HSSFSheet sheet = hssfWorkbook.createSheet(sheetName);
        sheet.setColumnWidth(0, 20 * 256);
        sheet.setDefaultColumnWidth(20);
        sheet.setDefaultRowHeightInPoints(20);
        /**
         * 创建单元格，并设置值表头 设置表头居中
         */
        HSSFCellStyle style = hssfWorkbook.createCellStyle();
        /**
         * 创建一个居中格式
         */
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        HSSFFont font = hssfWorkbook.createFont();
        //font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        style.setFont(font);

        /**
         * row样式1
         */
        HSSFCellStyle style1 = hssfWorkbook.createCellStyle();
        HSSFFont font1 = hssfWorkbook.createFont();
        font1.setBoldweight(HSSFFont.BOLDWEIGHT_NORMAL);
        /**
         * row样式2
         */
        HSSFCellStyle style2 = hssfWorkbook.createCellStyle();
        style2.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        HSSFFont font2 = hssfWorkbook.createFont();
        font2.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        style2.setFont(font2);

        HSSFRow row;
        HSSFCell cell;
        int cellIndex;
        int rowIndex = 0;
        for (Map<String, Object> mapRow : mapList) {
            row = sheet.createRow(rowIndex);
            cellIndex = 0;
            for (Map.Entry<String, Object> entry : mapRow.entrySet()) {
                System.out.println(entry.getKey() + ":" + entry.getValue());
                cell = row.createCell(cellIndex);
                cell.setCellValue(entry.getValue() + "");
                if (rowIndex == 0) {
                    cell.setCellStyle(style2);
                } else {
                    cell.setCellStyle(style1);
                }
                cellIndex++;
            }
            rowIndex++;
        }

        return hssfWorkbook;
    }

    public static HSSFWorkbook generateListContent(List<Map<String, Object>> mapList, String sheetName, HSSFWorkbook hssfWorkbook, List<String> picPathList) {
        if (StringUtils.isEmpty(sheetName)) throw new RuntimeException("sheet名称不能为空");
        if (hssfWorkbook == null) hssfWorkbook = new HSSFWorkbook();
        if (mapList == null) return hssfWorkbook;

        /**
         * 创建一个sheet
         */
        HSSFSheet sheet = hssfWorkbook.createSheet(sheetName);
        sheet.setColumnWidth(0, 20 * 256);
        sheet.setDefaultColumnWidth(20);
        sheet.setDefaultRowHeightInPoints(20);
        /**
         * 创建单元格，并设置值表头 设置表头居中
         */
        HSSFCellStyle style = hssfWorkbook.createCellStyle();
        /**
         * 创建一个居中格式
         */
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        HSSFFont font = hssfWorkbook.createFont();
        //font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        style.setFont(font);

        /**
         * row样式1
         */
        HSSFCellStyle style1 = hssfWorkbook.createCellStyle();
        HSSFFont font1 = hssfWorkbook.createFont();
        font1.setBoldweight(HSSFFont.BOLDWEIGHT_NORMAL);
        /**
         * row样式2
         */
        HSSFCellStyle style2 = hssfWorkbook.createCellStyle();
        style2.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        HSSFFont font2 = hssfWorkbook.createFont();
        font2.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        style2.setFont(font2);

        HSSFRow row;
        HSSFCell cell;
        int cellIndex;
        int rowIndex = 0;
        for (Map<String, Object> mapRow : mapList) {
            row = sheet.createRow(rowIndex);
            cellIndex = 0;
            for (Map.Entry<String, Object> entry : mapRow.entrySet()) {
                System.out.println(entry.getKey() + ":" + entry.getValue());
                cell = row.createCell(cellIndex);
                cell.setCellValue(entry.getValue() + "");
                if (rowIndex == 0) {
                    cell.setCellStyle(style2);
                } else {
                    cell.setCellStyle(style1);
                }
                cellIndex++;
            }
            rowIndex++;
        }
        generatePic(hssfWorkbook, picPathList);
        return hssfWorkbook;
    }

    public static void write(HSSFWorkbook hssfWorkbook, String name, HttpServletResponse response) throws IOException {
        write(hssfWorkbook, name, null, response);
    }

    /**
     * 输出流
     *
     * @param hssfWorkbook
     * @param name
     * @param suffix       后缀
     * @param response
     * @throws IOException
     */
    public static void write(HSSFWorkbook hssfWorkbook, String name, String suffix, HttpServletResponse response) throws IOException {

        if (suffix == null) suffix = "xls";

        OutputStream os = null;
        //将excel的数据写入文件
        response.setContentType("octets/stream");
        String excelName = name;//文件名字
        //转码防止乱码
        try {
            response.addHeader("Content-Disposition", "attachment;filename=" + new String(excelName.getBytes("gb2312"), "ISO8859-1") + "." + suffix);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        try {
            os = response.getOutputStream();
            hssfWorkbook.write(os);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("生成表格出错");
        } finally {
            if (os != null) {
                os.flush();
                os.close();
            }
        }

    }


    /**
     * 写图片到excel
     *
     * @param hssfWorkbook
     */
    private static void generatePic(HSSFWorkbook hssfWorkbook, List<String> picPathList) {
        // 拿到当前项目运行路径
        ApplicationHome h = new ApplicationHome(ExcelOut.class);
        String runPath = h.getSource().getParentFile().toString();
        int index = 0;
        if (picPathList != null && picPathList.size() > 0) {
            for (String path : picPathList) {
                //图片
                BufferedImage bufferImg = null;
                try {
                    // 先把读进来的图片放到一个ByteArrayOutputStream中，以便产生ByteArray
                    ByteArrayOutputStream byteArrayOut = new ByteArrayOutputStream();
                    //将图片读到BufferedImage
                    bufferImg = read(new File(runPath + "/" + path));
//                    bufferImg = read(new File( path));
                    // 将图片写入流中
                    ImageIO.write(bufferImg, "png", byteArrayOut);
                    // 创建一个工作薄
                    // HSSFWorkbook wb = new HSSFWorkbook();
                    //创建一个sheet
                    HSSFSheet sheet = hssfWorkbook.createSheet("附件图" + index++);
                    // 利用HSSFPatriarch将图片写入EXCEL
                    HSSFPatriarch patriarch = sheet.createDrawingPatriarch();
                    /**
                     * 该构造函数有8个参数
                     * 前四个参数是控制图片在单元格的位置，分别是图片距离单元格left，top，right，bottom的像素距离
                     * 后四个参数，前两个表示图片左上角所在的cellNum和 rowNum，后天个参数对应的表示图片右下角所在的cellNum和 rowNum，
                     * excel中的cellNum和rowNum的index都是从0开始的
                     */
                    HSSFClientAnchor anchor = new HSSFClientAnchor(0, 0, 0, 0,
                            (short) 0, 0, (short) 6, 20);
                    // 插入图片
                    patriarch.createPicture(anchor, hssfWorkbook.addPicture(byteArrayOut.toByteArray(), PICTURE_TYPE_JPEG));
                } catch (IOException io) {
                    io.printStackTrace();
                    out.println("IO异常 : " + io.getMessage());
                }
            }
        }
    }


    /**
     * desc:
     * param:
     * author: CDN
     * date: 2019/9/12
     */
    @Test
    public void generateExcel(HttpServletResponse response) {
        List<Map<String, Object>> mapList = new ArrayList<>();
        Map<String, Object> respMap;
//        表头
        respMap = new LinkedHashMap<>();
        respMap.put("no", "发票号码");
        respMap.put("code", "发票代码");
        respMap.put("type", "发票类型");
        respMap.put("state", "发票状态");
        respMap.put("customerCompany", "客户公司");
        respMap.put("selfCompanyName", "我方公司");
        mapList.add(respMap);
//内容
        respMap = new LinkedHashMap<>();
        respMap.put("no", "内容");
        respMap.put("no1", "内容");
        respMap.put("no2", "内容");
        respMap.put("no3", "内容");
        respMap.put("no4", "内容");
        respMap.put("no5", "内容");
        mapList.add(respMap);
        try {
            ExcelOut.write(ExcelOut.generateListContent(mapList, "名称"), "名称", response);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}

