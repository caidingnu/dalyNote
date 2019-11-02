package com.database.backup.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * desc：
 * author CDN
 * create 2019-10-29 14:17
 * version 1.0.0
 */
@RestController
@EnableScheduling
public class DataBaseBackUp {
    private final static Logger LOGGER = LoggerFactory.getLogger(DataBaseBackUp.class);
    @Value("${database.name}")
    private String databaseName;
    @Value("${database.ip}")
    private String databaseIp;
    @Value("${database.account}")
    private String databaseAccount;
    @Value("${database.pwd}")
    private String pwd;
//    @Value("${database.savepath}")
//    private String savePath;


    /**
     * 执行生成备份
     */
    private void back() throws IOException {
        Runtime runtime = Runtime.getRuntime();  //获取Runtime实例
        Date currentDate = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        String sdfDate = sdf.format(currentDate);
        System.out.println("现在时间是" + new Date());
//        String filepath = "d:\\time_" + sdfDate + ".sql"; // 备份的路径地址
//        String filepath = file.toString()+File.separator+ databaseName + "_" + sdfDate + ".sql"; // 备份的路径地址
        String filepath = "./back" + File.separator + databaseName + "_" + sdfDate + ".sql"; // 备份的路径地址
        File file = new File("./back");
        if (!file.exists()) {
            file.mkdirs();
        }
        //执行命令
//        指定表
//C:\Program Files\MySQL\MySQL Server 5.7\bin>mysqldump -h localhost -u root -proot --databases shop --tables sc_cart sys_admin > d:\time_2018-11-14_09-54-55.sql";
        String mysqldumpPath = new ClassPathResource("static/mysqldump.exe").getFile().getPath();
        String cmd = mysqldumpPath + " -h " + databaseIp + " -u " + databaseAccount + " -p" + pwd + " --databases " + databaseName + "> " + filepath;
        try {
            String[] command = {"cmd", "/c", cmd};
            Process process = runtime.exec(command);
            InputStream input = process.getInputStream();
//            System.out.println(IOUtils.toString(input, "UTF-8"));
            //若有错误信息则输出
            InputStream errorStream = process.getErrorStream();
//            System.out.println(IOUtils.toString(errorStream, "gbk"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * desc: 每天凌晨2点执行
     * param:
     * return:
     * author: CDN
     * date: 2019-10-29
     */
    @RequestMapping("start")
    @Scheduled(cron = "0 0 2 * * ?")
    public String start() {
        try {
            Connection conn = getConnection(databaseName);
            if (conn != null) {
                back();
            } else {
                LOGGER.error("数据库连接失败或数据库不存在！");
                return "数据库连接失败或数据库不存在！";
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "成功";
    }

    /**
     * 获取数据库连接
     *
     * @return
     */
    private Connection getConnection(String databaseName) {
        String driver = "com.mysql.jdbc.Driver";
        String url = "jdbc:mysql://" + databaseIp + ":3306/" + databaseName + "?serverTimezone=UTC&useUnicode=true&characterEncoding=utf8";
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url, databaseAccount, pwd);
        } catch (SQLException e) {
            LOGGER.error("数据库连接失败" + e.getMessage());
        }
        return conn;
    }

}
