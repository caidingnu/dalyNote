package com.study.mybatisplus.service;


import com.baomidou.mybatisplus.service.IService;
import com.study.mybatisplus.entity.Menu;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author author
 * @since 2019-10-31
 */
public interface IMenuService extends IService<Menu> {
    List<Map<String,Object>> inner();
}
