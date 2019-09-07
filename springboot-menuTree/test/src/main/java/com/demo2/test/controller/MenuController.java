package com.demo2.test.controller;

import com.demo2.test.entity.Menu;
import com.demo2.test.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

/**
 * desc：
 * author CDN
 * create 2019-09-07 20:21
 * version 1.0.0
 */
@RestController
public class MenuController {

    @Autowired
    MenuService menuServiceImpl;

    //    递归查询
    @RequestMapping("tree")
    public List<Map<String, Object>> tree() {
        //查询出所有的一级菜单[]
        List<Menu> treeMenu = menuServiceImpl.selectByPid(0);
        List<Map<String, Object>> list = new ArrayList<>();
        if (treeMenu.size() > 0) {
            for (Menu menu : treeMenu) {
                Map<String, Object> map = new LinkedHashMap<>();
                map.put("id", menu.getId());
                map.put("name", menu.getName());
                map.put("children", getChildren(menu.getId()));
                list.add(map);
            }
        }
        return list;
    }


    /**
     * 递归
     *
     * @param id
     * @return
     */
    public List<Object> getChildren(Integer id) {
        List<Object> list = new ArrayList<>();
        List<Menu> treeMenu = menuServiceImpl.selectByPid(id);
        for (Menu menu : treeMenu) {
            Map<String, Object> map = new LinkedHashMap<>();
            map.put("id", menu.getId());
            map.put("name", menu.getName());
            map.put("children", getChildren(menu.getId()));
            list.add(map);
        }
        return list;
    }

    //    递归找----------------------------------------------------------------------------------------------------------------
    @RequestMapping("findTree")
    public List<Menu> findTree() {
        //所有的菜单
        List<Menu> menuList = menuServiceImpl.selectByExample(null);
        List<Menu> respList = new ArrayList<>();  // respList 装最后返回的结果
        // 先找到所有的一级菜单
        for (Menu value : menuList) {
            //一级菜单parentId=0；
            if (value.getPid() == 0) {
                respList.add(value);
            }
        }

        // 遍历一级菜单，开始找子菜单
        for (Menu menu : respList) {
            Object childlist = getChild(menu.getId(), menuList);
            menu.setChildren(childlist);
        }
        return respList;
    }


    /**
     * 递归查找子菜单
     * id 当前菜单id
     * menuList  所有的菜单
     */
    private Object getChild(int id, List<Menu> menuList) {
        // 找二级子菜单
        List<Menu> childList = new ArrayList<>();
        for (Menu menu : menuList) {
            // 遍历所有菜单，将父菜单id与传过来的id比较，如果相等就是该父菜单下的子菜单
            if (menu.getPid() == id) {
                childList.add(menu);
            }
        }
        // 把子菜单的子菜单再循查找子菜单
        for (Menu menu : childList) {
            Object obj = getChild(menu.getId(), menuList);
            menu.setChildren(obj);
        }
        return childList;
    }

}
