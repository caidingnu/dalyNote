package com.e.word.controller;

import java.io.*;

/**
 * desc：  读取html为字符串
 * author CDN
 * create 2019-07-20 15:33
 * version 1.0.0
 */
public class Read {
    public static String readfile(String filePath) {
        File file = new File(filePath);
        InputStream input = null;
        try {
            input = new FileInputStream(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        StringBuffer buffer = new StringBuffer();
        byte[] bytes = new byte[1024];
        try {
            for (int n; (n = input.read(bytes)) != -1;) {
                buffer.append(new String(bytes, 0, n, "utf8"));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return buffer.toString();
    }
}
