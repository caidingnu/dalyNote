package com.study.mybatisplus.controller;


import com.baomidou.mybatisplus.enums.SqlLike;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.study.mybatisplus.entity.Menu;
import com.study.mybatisplus.service.IMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author author
 * @since 2019-10-31
 */
@RestController
@RequestMapping("/menu")
public class MenuController {

    @Autowired
    IMenuService iMenuService;


    @RequestMapping("all")
    public Object test() {
        EntityWrapper<Menu> entityWrapper = new EntityWrapper<>();
        return iMenuService.selectList(null);
    }


    /**
     * desc: 分页查询
     * param:
     * return:
     * author: CDN
     * date: 2019/10/31
     */
    @RequestMapping("page")
    public Object page(Integer startPage, Integer pageSize) {
        EntityWrapper<Menu> entityWrapper = new EntityWrapper<>();
//        分页条件
        entityWrapper.lt("id", 2);
        Page<Menu> page = new Page<>(startPage == null ? 0 : pageSize, 10);

        return iMenuService.selectPage(page, entityWrapper);
    }

    /**
     * desc: 自定义sql
     * param:
     * return:
     * author: CDN
     * date: 2019/10/31
     */
    @RequestMapping("sql")
    public Object tiaojian() {
        EntityWrapper<Menu> entityWrapper = new EntityWrapper<>();
        entityWrapper.setSqlSelect(" menu.* ").eq("id", 1).and("pid", 33);
        return iMenuService.selectMaps(entityWrapper);
    }


    /**
     * desc:  根据id查询,批量查询
     * param:
     * return:
     * author: CDN
     * date: 2019/10/31
     */
    @RequestMapping("id")
    public Object all() {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        return iMenuService.selectBatchIds(list);
    }


    /**
     * desc: 多表关联查询自己写
     * param:
     * return:
     * author: CDN
     * date: 2019/11/1
     */
    @RequestMapping("join")
    public Object join() {
        return iMenuService.inner();
    }


    /**
     * desc:  模糊查询
     * param:
     * return:
     * author: CDN
     * date: 2019/11/2
     */
    @RequestMapping("like")
    public Object like() {

        EntityWrapper<Menu> wrapper = new EntityWrapper<>();
//        wrapper.like("name", "部",);
//                LEFT("left", "左边%"),
//                RIGHT("right", "右边%"),
//                CUSTOM("custom", "定制"),
//                DEFAULT("default", "两边%");
        wrapper.like("name", "部", SqlLike.RIGHT);
        wrapper.like("name", "部", SqlLike.LEFT);
        wrapper.like("name", "部", SqlLike.DEFAULT);
        wrapper.like("name", "_务%", SqlLike.CUSTOM);
        return iMenuService.selectList(wrapper);
    }


    /**
     * des:
     * param:  新增，批量新增
     * return:
     * author: CDN
     * date: 2019/11/2
     */
    @RequestMapping("add")
    public boolean insert() {
        Menu menu = new Menu();
        menu.setId(33);
        menu.setName("测试");

//        第一种
//        boolean b = menu.insertOrUpdate();

//        第二种
        EntityWrapper<Menu> wrapper = new EntityWrapper<>();
        wrapper.setEntity(menu);
        return iMenuService.insertOrUpdate(menu);
    }

    /**
     * desc:  删除批量删除
     * param:
     * return:
     * author: CDN
     * date: 2019/11/2
     */
    @RequestMapping("delete")
    public Object delete() {
        Menu menu = new Menu();
//        第一种
        menu.setId(1);
        menu.deleteById();
//     二
        menu.deleteById(1);
//  三
        menu.delete("id", 1);
//        四
        EntityWrapper<Menu> wrapper = new EntityWrapper<>();
        wrapper.where("id", 1);
        menu.delete(wrapper);

//        五
        iMenuService.delete(wrapper);
//        6 批量删除
        List<Integer> idList = new ArrayList<Integer>() {{
            add(1);
        }};
        iMenuService.deleteBatchIds(idList);

//        7
        iMenuService.deleteById(1);
//        8
        Map<String, Object> map = new HashMap<String, Object>() {{
            put("id", 1);
        }};
        iMenuService.deleteByMap(map);
        return null;
    }


    /**
     * des:
     * param:  更新，批量更新
     * return:
     * author: CDN
     * date: 2019/11/2
     */
    @RequestMapping("update")
    public boolean update() {
        Menu menu = new Menu();
        menu.setId(33);
        menu.setName("测试");
//        第一种
//        boolean b = menu.insertOrUpdate();

//        第二种
        EntityWrapper<Menu> wrapper1 = new EntityWrapper<>();
        wrapper1.setEntity(menu);
        wrapper1.where("id", 1);
        menu.update(wrapper1);


//        第三种
        EntityWrapper<Menu> wrapper = new EntityWrapper<>();
        wrapper.setEntity(menu);

//        更新方法很多
        return iMenuService.insertOrUpdate(menu);
    }



}
