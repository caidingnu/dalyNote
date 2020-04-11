package com.model.demo.controller;

import com.alibaba.fastjson.JSONObject;
import com.model.demo.entity.JsonResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/jwt")
public class JwtController {

    @RequestMapping("/getMessage")
    public Object getMessage() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("msg", "你已通过验证");
        Mapping
        return JsonResult.buildSuccess(jsonObject);
    }
}