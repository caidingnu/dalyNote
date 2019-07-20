package com.e.word.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * desc：
 * author CDN
 * create 2019-07-20 16:20
 * version 1.0.0
 */
@RestController
public class ConverController {

    @RequestMapping("html")
    public String a()  {
//        String htmlPath= docxToHtml.docxToHtml("IDEA拉取码云上的项目配置.docx");
        String htmlPath= docxToHtml.docxToHtml("test.doc");
        return  Read.readfile(htmlPath);
    }
}
