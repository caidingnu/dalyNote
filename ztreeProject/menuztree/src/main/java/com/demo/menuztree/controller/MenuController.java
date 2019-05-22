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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

}
