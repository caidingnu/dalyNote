package com.e.word.controller;

import org.apache.poi.xwpf.converter.core.BasicURIResolver;
import org.apache.poi.xwpf.converter.core.FileImageExtractor;
import org.apache.poi.xwpf.converter.xhtml.XHTMLConverter;
import org.apache.poi.xwpf.converter.xhtml.XHTMLOptions;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import java.io.*;
import java.util.Arrays;

/**
 * desc：
 * author CDN
 * create 2019-07-20 15:15
 * version 1.0.0
 */
@Service
public class docxToHtml {
    public static String docxToHtml(String docxName) {

        File path = null;
        String targetFileName = null;
        try {
            path = new File(ResourceUtils.getURL("classpath:").getPath());
            String imagePath = path.getAbsolutePath() + "/static/image";
            String sourceFileName = path.getAbsolutePath() + "/static/" + docxName;
            targetFileName = path.getAbsolutePath() + "/static/" + docxName.substring(0,docxName.indexOf("doc")-1) + ".html";
            OutputStreamWriter outputStreamWriter = null;
            try {
                XWPFDocument document = new XWPFDocument(new FileInputStream(sourceFileName));
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


}
