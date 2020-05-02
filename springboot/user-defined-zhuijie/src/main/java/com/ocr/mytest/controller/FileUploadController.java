package com.ocr.mytest.controller;

import com.ocr.mytest.aop.CdnLog;
import com.ocr.mytest.mapper.MenuMapper;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

@RestController
public class FileUploadController {

    public static void main(String[] args) throws Exception {
        // new一个URL对象
        URL url = new URL("http://127.0.0.1:8080/app/aaa.png");
        // 保存目录
        File file = new File("D:\\upload\\tem1111.png");
        // 得到图片并保存
        readInputStream(url, file);
    }


    /**
     * desc:
     * param:
     * author: CDN
     * date: 2019/10/19
     */
    @RequestMapping("a")
    public void test() throws Exception {
        // new一个URL对象
        URL url = new URL("http://127.0.0.1:8080/app/aaa.png");
        // 保存目录
        File file = new File("D:\\upload\\tem1111.png");
        // 得到图片并保存
        readInputStream(url, file);
    }

    public static void readInputStream(URL url, File file) throws Exception {
        // 打开链接
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        // 设置请求方式为"GET"
        conn.setRequestMethod("GET");
        // 超时响应时间为5秒
        conn.setConnectTimeout(5 * 1000);
        // 通过输入流获取图片数据
        InputStream inStream = conn.getInputStream();
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        // 创建一个Buffer字符串
        byte[] buffer = new byte[1024];
        // 每次读取的字符串长度，如果为-1，代表全部读取完毕
        int len = 0;
        // 使用一个输入流从buffer里把数据读取出来
        while ((len = inStream.read(buffer)) != -1) {
            // 用输出流往buffer里写入数据，中间参数代表从哪个位置开始读，len代表读取的长度
            outStream.write(buffer, 0, len);
        }
        // 关闭输入流
        inStream.close();
        // 创建输出流
        FileOutputStream outStream2 = new FileOutputStream(file);
        // 写入数据
        outStream2.write(outStream.toByteArray());
        // 关闭输出流
        outStream2.close();
    }

    /**
     * desc:
     * param:
     * author: CDN
     * date: 2019/10/20
     */
    @RequestMapping("tt")
    @CdnLog(description = "测试", type = "9999")
    public void aa(String aa) {
        System.out.println("-----");
    }

    /**
     * desc:
     * param:
     * author: CDN
     * date: 2019/10/21
     */
    @Resource
    MenuMapper menuMapper;

    @RequestMapping("all")
    @CdnLog(description = "所有", type = "666")
    public Object all() {
        return menuMapper.selectAll();
    }

}