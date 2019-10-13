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
        return DocxToHtmlUtil.readHtml("C:\\Users\\cdn\\Desktop\\test.doc");
    }

}
