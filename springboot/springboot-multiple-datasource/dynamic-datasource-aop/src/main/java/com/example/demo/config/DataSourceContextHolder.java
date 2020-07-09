package com.example.demo.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * 多数据源动态切换的工具类
 */
public class DataSourceContextHolder {

    /**
     * 用于日志记录的对象
     */
    public static final Logger logger = LoggerFactory.getLogger(DataSourceContextHolder.class);

    /**
     * 进程内数据存储
     */
    private static final ThreadLocal<String> contextHolder = new ThreadLocal<>();

    /**
     * 默认的数据源的名称
     */
    public static final String DEFAULT_DATDASOURCE_NAME = "master";

    /**
     * 设置数据源名
     * @param datasourceName 数据源的名字
     */
    public static void setDatasourceName(String datasourceName){
        logger.info("切换到{}数据源",datasourceName);
        contextHolder.set(datasourceName);
    }

    /**
     * 获取数据源名
     * @return
     */
    public static String getDatdasourceName(){
        return contextHolder.get();
    }

    /**
     * 清除数据源名称
     */
    public static void clearDatasourceName(){
        logger.info("=======恢复默认数据库=====");
        contextHolder.remove();
    }
}