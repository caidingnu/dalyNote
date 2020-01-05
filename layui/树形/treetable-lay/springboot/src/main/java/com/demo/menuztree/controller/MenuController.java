package com.demo.menuztree.controller;

import com.demo.menuztree.constant.Constant;
import com.demo.menuztree.entity.Menu;
import com.demo.menuztree.entity.base.BaseResponseEntity;
import com.demo.menuztree.mapper.MenuMapper;
import com.demo.menuztree.utils.EmptyUtil;
import com.demo.menuztree.utils.JsonUtil;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.*;

/**
 * 功能简述：
 *
 * @author caidingnu
 * @create 2019-04-16 21:30
 * @since 1.0.0
 */
@RestController
@CrossOrigin
public class MenuController {

    @Resource
    MenuMapper menuMapper;

    /**
     * desc:
     * param:
     * return:
     * author: CDN
     * date: 2019/11/15
     */
    @RequestMapping("all")
    public Object all(){
        Map<String,Object> map=new HashMap<>();
        List<Menu> menuList = menuMapper.all();
        if (menuList==null){
            menuList=new ArrayList<>();
        }
        map.put("StateCode",200);
        map.put("msg","ok");
        map.put("data",menuList);
        return map;
    }

    /**
     * @Description:
     * @Param:
     * @return:
     * @Author: caidingnu
     * @Date: 2019/4/16
     */
    @RequestMapping("list")
    public String list() {
        List<Menu> list = menuMapper.selectAll();
        return JsonUtil.toJSon(new BaseResponseEntity(list, list.size()));
    }

    /**
     * @Description:
     * @Param:
     * @return:
     * @Author: caidingnu
     * @Date: 2019/4/16
     */
    @RequestMapping("add")
    public Map add(Menu menu) {
        int result = menuMapper.insertSelective(menu);
        Integer id = menu.getId();//该对象的自增ID
        Map<String, Integer> map = new HashMap<>();
        map.put("id", id);
        map.put("result", result);
        return map;
    }


    /**
     * @Description:
     * @Param:
     * @return:
     * @Author: caidingnu
     * @Date: 2019/4/16
     */
    @RequestMapping("update")
    public String update(Menu menu) {
        int result = menuMapper.updateByPrimaryKeySelective(menu);
        if (result == 1) {
            return JsonUtil.toJSon(new BaseResponseEntity(Constant.CODE_OK));
        } else {
            return JsonUtil.toJSon(new BaseResponseEntity(Constant.CODE_DEFALT));
        }
    }

    /**
     * @Description:
     * @Param:
     * @return:
     * @Author: caidingnu
     * @Date: 2019/4/16
     */
    @RequestMapping("delete")
    public String delete(Integer id) {
        int result = menuMapper.deleteByPrimaryKey(id);
        if (result == 1) {
            return JsonUtil.toJSon(new BaseResponseEntity(Constant.CODE_OK));
        } else {
            return JsonUtil.toJSon(new BaseResponseEntity(Constant.CODE_DEFALT));
        }
    }


    /**
     * @Description: 查询一级目录
     * @Param:
     * @return:
     * @Author: caidingnu
     * @Date: 2019/4/17
     */
    @RequestMapping("oneLevel")
    public String selectOneLeve(Integer id) {

        List<Menu> list = menuMapper.selectOne(id);
        System.out.println(id);
        return JsonUtil.toJSon(new BaseResponseEntity(list, list.size()));
    }


    /**
     * desc:
     * param:
     * return:
     * author: CDN
     * date: 2019/11/15
     */
    @RequestMapping("shu")
    public Object shu(){
        return tree();
    }

     public List<Map<String, Object>> tree() {
             //查询出所有的一级菜单[]
             List<Menu> treeMenu = menuMapper.selectOne(0);
             List<Map<String, Object>> list = new ArrayList<>();
             if (treeMenu.size() > 0) {
                 for (Menu menu : treeMenu) {
                     Map<String, Object> map = new LinkedHashMap<>();
                     map.put("id", menu.getId());
                     map.put("title", menu.getName());
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
             List<Menu> treeMenu = menuMapper.selectOne(id);
             for (Menu menu : treeMenu) {
                 Map<String, Object> map = new LinkedHashMap<>();
                 map.put("id", menu.getId());
                 map.put("title", menu.getName());
                 map.put("children", getChildren(menu.getId()));
                 list.add(map);
             }
             return list;
         }

}
