package com.admin.boot.controller;

import java.io.IOException;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;

@RestController
public class IoController {
    @RequestMapping("io")
    public void responseIo(HttpServletResponse response) {
        FileInputStream fileInputStream = null;
        ServletOutputStream outputStream = null;
        try {
            Resource resource = new ClassPathResource("file/a.mp3");
            File file = resource.getFile();
            response.setContentType("octets/stream");
//        设置编码，避免文件名中文乱码
            response.setHeader("Content-Disposition", "attachment;filename=" + new String("新的文件名称".getBytes("gb2312"), "ISO8859-1") + ".mp3");
            fileInputStream = new FileInputStream(file);
            outputStream = response.getOutputStream();
            int a = -1;
            while ((a = fileInputStream.read()) != -1) {
                outputStream.write(a);
            }
            outputStream.flush();
        } catch (IOException e) {
            System.out.println("取消下载");
            e.printStackTrace();
        } finally {
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (fileInputStream != null) {
                try {
                    fileInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
