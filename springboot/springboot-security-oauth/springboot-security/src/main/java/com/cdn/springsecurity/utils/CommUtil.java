package com.cdn.springsecurity.utils;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;

/**
 * CDN
 * 2020/05/19 01:15
 */
public class CommUtil {


    /**
     * desc:
     * param:
     * author: CDN
     * date: 2020/5/19
     */
    public static void writeMsgToFront(HttpServletResponse response, String msg) {
        try {
            //在响应中主动告诉浏览器使用UTF-8编码格式来接收数据
            response.setHeader("Content-Type", "text/html;charset=UTF-8");
            response.setContentType("text/html;charset=UTF-8");
            PrintWriter writer = response.getWriter();
            writer.write(msg);
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
