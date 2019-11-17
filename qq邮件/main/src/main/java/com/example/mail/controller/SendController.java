package com.example.mail.controller;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.util.*;

/**
 * desc：
 * author CDN
 * create 2019-11-17 00:06
 * version 1.0.0
 */
@RestController
public class SendController {

    @Value("${emailaddr}")
    private String userName;

    @Value("${authCode}")
    private String authCode;

    /**
     * @param receiveAddr 接受的qq邮箱号码
     * @return
     * @throws Exception
     */
    @RequestMapping("send")
    public Object send( String[] receiveAddr) throws Exception {
        List<File> list = new ArrayList<>(Collections.singletonList(new File("D:\\image\\a.jpg")));
        String text = "<h1>来自cdn网站验证码邮件,请接收你的验证码：</h1><h3>你的验证码是：" + UUID.randomUUID().toString() + "，请妥善保管好你的验证码！</h3>";
        return MailUtils.sendMail(userName, receiveAddr, "标题", text, authCode, list);
    }

}
