package com.oceam.controller;

import com.alibaba.fastjson.JSONArray;
import com.oceam.entity.MyJson;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * 功能简述：
 *
 * @author caidingnu
 * @version 1.0.0
 * @create 2019-04-01 20:53
 */
@RestController
@CrossOrigin("*")
public class GetJsonArr {


    /**
     * @Description:
     * @Param:
     * @return:
     * @Author: caidingnu
     * @Date: 2019/4/1
     */
    @RequestMapping("getjson")
    public void as(JSONArray jsonArray) {

        List<MyJson> list = new ArrayList<>();
        for (int i = 0; i < jsonArray.size(); i++) {
            MyJson myJson = new MyJson();
            myJson.setName((String) jsonArray.getJSONObject(i).get("name"));
            myJson.setValue((String) jsonArray.getJSONObject(i).get("name"));
            list.add(myJson);
        }

        System.out.println(list);

    }
}
