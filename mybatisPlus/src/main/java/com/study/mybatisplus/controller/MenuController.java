package com.study.mybatisplus.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
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
        Menu menu = new Menu();
        menu.selectList(null);
        menu.selectAll();
        return iMenuService.list();
    }


    /**
     * desc: lambad 的and   or
     * param:
     * return:
     * author: CDN
     * date: 2019/11/4
     */
    @RequestMapping("lambad")
    public Object lambad() {
        QueryWrapper<Menu> queryWrapper = new QueryWrapper<>();
        queryWrapper.and(p -> p.like(true, "name", "金浔"));
        queryWrapper.or(p -> p.eq("id", 2));
        return iMenuService.list(queryWrapper);
    }

    /**
     * desc: 子查询
     * param:
     * return:
     * author: CDN
     * date: 2019/11/4
     */
    @RequestMapping("selectChild")
    public Object selectChild() {
        QueryWrapper<Menu> queryWrapper = new QueryWrapper<>();
//        queryWrapper.in(true, "id", "select id from menu");
        queryWrapper.inSql("id", "select id from menu where id in (1,2,3)");
        return iMenuService.list(queryWrapper);
    }


    /**
     * desc: 查询制定字段
     * param:
     * return:
     * author: CDN
     * date: 2019/11/5
     */
    @RequestMapping("find")
    public Object find(){
        QueryWrapper<Menu> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("id,name").eq("id", 1);

        return  iMenuService.list(queryWrapper);
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
        QueryWrapper<Menu> queryWrapper = new QueryWrapper<>();
//        分页条件
        queryWrapper.lt("id", 2);
        Page<Menu> page = new Page<>(startPage == null ? 0 : pageSize, 10);
        return iMenuService.page(page, queryWrapper);
    }

    /**
     * desc: 自定义sql  【and】
     * param:
     * return:
     * author: CDN
     * date: 2019/10/31
     */
    @RequestMapping("sql")
    public Object tiaojian() {
        QueryWrapper<Menu> queryWrapper = new QueryWrapper<>();
//
        queryWrapper.select("*").eq("id", 1).and(p -> p.like("name", "金浔"));

//        或者 and
        queryWrapper.eq("pid", 33);

//        或者 or
        queryWrapper.or().like("name", "金浔");
        return iMenuService.list(queryWrapper);
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
        return iMenuService.listByIds(list);
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
        QueryWrapper<Menu> queryWrapper = new QueryWrapper<>();
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
        QueryWrapper<Menu> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("name", "部");
        queryWrapper.likeRight("name", "部");
        queryWrapper.likeLeft("name", "部");
        queryWrapper.like("name", "_务%");
        queryWrapper.like("name", "_务");
        return iMenuService.list(queryWrapper);
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
        boolean b = menu.insertOrUpdate();

//        第二种
        QueryWrapper<Menu> queryWrapper = new QueryWrapper<>();
        queryWrapper.setEntity(menu);
        return iMenuService.saveOrUpdate(menu);
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

//        四
        QueryWrapper<Menu> wrapper = new QueryWrapper<>();
        wrapper.eq("id", 1);
        menu.delete(wrapper);

//        五
        iMenuService.remove(wrapper);

//        6 批量删除
        List<Integer> idList = new ArrayList<Integer>() {{
            add(1);
        }};
        iMenuService.removeByIds(idList);

//        7
        iMenuService.removeById(1);
//        8
        Map<String, Object> map = new HashMap<String, Object>() {{
            put("id", 1);
        }};
        iMenuService.removeByMap(map);

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
        QueryWrapper<Menu> wrapper1 = new QueryWrapper<>();
        wrapper1.setEntity(menu);
        wrapper1.eq("id", 1);
        menu.update(wrapper1);

//        第三种
        QueryWrapper<Menu> wrapper = new QueryWrapper<>();
        wrapper.setEntity(menu);

//        更新方法很多
        return iMenuService.saveOrUpdate(menu);
    }


}
