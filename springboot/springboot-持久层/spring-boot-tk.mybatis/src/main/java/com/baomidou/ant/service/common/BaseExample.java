package com.baomidou.ant.service.common;

import tk.mybatis.mapper.entity.Example;

/**
 * desc：
 *
 * @author CDN
 * date 2020/08/25 22:07
 */
public class BaseExample extends Example {
    private Integer pageNum;
    private Integer pageSize;
    private boolean needPage;


    public Integer getPageNum() {
        return pageNum;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public boolean getNeedPage() {
        return needPage;
    }

    public BaseExample(Class<?> entityClass) {
        super(entityClass);
        this.needPage = false;
    }

    public BaseExample(Class<?> entityClass, Integer pageNum, Integer pageSize) {
        super(entityClass);
        this.needPage = true;
        this.pageNum = pageNum;
        this.pageSize = pageSize;
    }


}
