package com.example.demo.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 *　动态数据源类继承自AbstractRoutingDataSource
 *  查看数据源信息
 */
public class DynamicDataSource extends AbstractRoutingDataSource{
    private static final Logger logger = LoggerFactory.getLogger(DynamicDataSource.class);

    @Override
    public Object determineCurrentLookupKey() {
        logger.info("数据源☆☆☆☆☆☆☆☆☆☆{}", DataSourceContextHolder.getDatdasourceName());
        return DataSourceContextHolder.getDatdasourceName();
    }
}