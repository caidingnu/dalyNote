package com.cdn.springsecurity.utils;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

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
    public static void writeMsgToFront(HttpServletResponse response, String msg){
        try {
            PrintWriter writer = response.getWriter();
            writer.write(msg);
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
