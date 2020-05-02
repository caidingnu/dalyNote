package com.study.wxapp.config.dataSourceConfig;


import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 数据源路由
 *
 * @author gengqiang
 */
public class RoutingDataSource extends AbstractRoutingDataSource {

    private AtomicInteger count = new AtomicInteger(0);

    private int readsize;

    public RoutingDataSource(int readsize) {
        this.readsize = readsize;
    }

    @Override
    protected Object determineCurrentLookupKey() {
        String typeKey = DataSourceContextHolder.getJdbcType();
        if (typeKey == null) {
            logger.error("无法确定数据源");
        } else if (typeKey.equals(DataSourceType.WRITE.getType())) {
            return DataSourceType.WRITE.getType();
        }

        //读库进行负载均衡
        int a = count.getAndAdd(1);
        return a % readsize;
    }

}