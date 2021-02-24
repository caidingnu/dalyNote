package com.example.demo.change;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 *　动态数据源类继承自AbstractRoutingDataSource
 * @author lishouyu
 * @version 1.0
 * @since 1.0
 */
public class DynamicDataSource extends AbstractRoutingDataSource {
    /**
     * 记录日志
     */
    private static final Logger logger = LoggerFactory.getLogger(DynamicDataSource.class);

    @Override
    public Object determineCurrentLookupKey() {
        logger.debug("数据源----{}",DataSourceContextHolder.getDatdasourceName());
        return DataSourceContextHolder.getDatdasourceName();
    }
}