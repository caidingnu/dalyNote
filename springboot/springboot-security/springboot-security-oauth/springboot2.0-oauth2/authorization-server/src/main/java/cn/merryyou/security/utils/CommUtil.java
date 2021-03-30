package cn.merryyou.security.utils;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class CommUtil {
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
