package com.e.word.controller;

import org.apache.ibatis.logging.Log;
import org.apache.poi.xwpf.converter.core.BasicURIResolver;
import org.apache.poi.xwpf.converter.core.FileImageExtractor;
import org.apache.poi.xwpf.converter.xhtml.XHTMLConverter;
import org.apache.poi.xwpf.converter.xhtml.XHTMLOptions;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.springframework.boot.system.ApplicationHome;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import java.io.*;
import java.net.ConnectException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Logger;

/**
 * desc：
 * author CDN
 * create 2019-07-20 15:15
 * version 1.0.0
 */
@Service
public class DocxToHtmlUtil {

    private static String rootDirectoryTemp = "upload" + File.separator + "htmlTemp";


    public static String readHtml(String docPath) {
        String html = docxToHtml(docPath);
        File file = new File(html);
        InputStream input = null;
        try {
            input = new FileInputStream(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        StringBuffer buffer = new StringBuffer();
        byte[] bytes = new byte[1024];
        try {
            for (int n; (n = input.read(bytes)) != -1; ) {
                buffer.append(new String(bytes, 0, n, "utf8"));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        del();
        return buffer.toString();
    }


    private static String docxToHtml(String docPath) {
//拿到当前项目运行路径
        ApplicationHome h = new ApplicationHome(DocxToHtmlUtil.class);
        File jarF = h.getSource();
        String runPath = jarF.getParentFile().toString() + File.separator + rootDirectoryTemp + File.separator + getNowDate() + File.separator + getNowDateTime();
        if (!new File(runPath).exists()) {
            new File(runPath).mkdirs();
        }
        String targetFileName = null;
        try {
            String imagePath = runPath + "/image";
            targetFileName = runPath + "/" + getNowDateTime() + ".html";
            OutputStreamWriter outputStreamWriter = null;
            try {
                XWPFDocument document = new XWPFDocument(new FileInputStream(docPath));
                XHTMLOptions options = XHTMLOptions.create();
                // 存放图片的文件夹
                options.setExtractor(new FileImageExtractor(new File(imagePath)));
                // html中图片的路径
                options.URIResolver(new BasicURIResolver("image"));
                outputStreamWriter = new OutputStreamWriter(new FileOutputStream(targetFileName), "utf-8");
                XHTMLConverter xhtmlConverter = (XHTMLConverter) XHTMLConverter.getInstance();
                xhtmlConverter.convert(document, outputStreamWriter, options);
            } finally {
                if (outputStreamWriter != null) {
                    outputStreamWriter.close();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("ok");
        return targetFileName;
    }


    /**
     * desc:
     * param: 零晨3点触发
     * author: CDN
     * date: 2019/10/13
     */
    @Scheduled(cron = "0 0 3 * * ?")
    private static void del() {
        ApplicationHome h = new ApplicationHome(DocxToHtmlUtil.class);
        File jarF = h.getSource();
        String runPath = jarF.getParentFile().toString() + File.separator + rootDirectoryTemp + File.separator + getYesterdayDate();
        deleteDir(runPath);
    }


    /**
     * 迭代删除文件夹
     *
     * @param dirPath 文件夹路径
     */
    public static void deleteDir(String dirPath) {
        File file = new File(dirPath);

        System.out.println(file.getName());
        if (file.isFile()) {
            System.out.println(file.delete());
        } else {
            File[] files = file.listFiles();
            if (files == null) {
                file.delete();
            } else {
                for (int i = 0; i < files.length; i++) {
                    deleteDir(files[i].getAbsolutePath());
                }
                file.delete();
            }
        }
    }


    /**
     * 获取当前时间yyyyMMddHHmmss
     *
     * @return
     */
    private static String getNowDateTime() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
        return formatter.format(new Date());
    }

    /**
     * 获取当前日期yyyyMMdd
     *
     * @return
     */
    private static String getNowDate() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
        return formatter.format(new Date());
    }

    /**
     * 获取昨天日期yyyyMMdd
     *
     * @return
     */
    private static String getYesterdayDate() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DATE, -1);
        String yesterdayDate = dateFormat.format(calendar.getTime());
        return yesterdayDate;
    }



}