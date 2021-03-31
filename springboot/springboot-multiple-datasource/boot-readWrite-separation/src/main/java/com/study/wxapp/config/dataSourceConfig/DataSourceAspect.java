package com.study.wxapp.config.dataSourceConfig;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * 拦截数据库读写
 * <p>
 * 注意 ----------   因为该切面是配置在业务实现层，所以事务要配置在serviceImpl才会生效
 */

@Aspect
@Component
@Order(1)
public class DataSourceAspect {

    Logger logger = LoggerFactory.getLogger(DataSourceAspect.class);

    @Before("execution(* com.study..*.*ServiceImpl.find*(..)) " +
            "|| execution(* com.study..*.*ServiceImpl.count*(..))" +
            "|| execution(* com.study..*.*ServiceImpl.sel*(..))" +
            "|| execution(* com.study..*.*ServiceImpl.query*(..))" +
            "|| execution(* com.study..*.*ServiceImpl.get*(..))"
    )
    public void setReadDataSourceType() {
        logger.info("拦截[read]方法");
        DataSourceContextHolder.read();
    }


    @Before("execution(* com.study..*.*ServiceImpl.insert*(..)) " +
            "|| execution(* com.study..*.*ServiceImpl.save*(..))" +
            "|| execution(* com.study..*.*ServiceImpl.update*(..))" +
            "|| execution(* com.study..*.*ServiceImpl.set*(..))" +
            "|| execution(* com.study..*.*ServiceImpl.del*(..))")
    public void setWriteDataSourceType() {
        logger.info("拦截[write]操作");
        DataSourceContextHolder.write();
    }

}