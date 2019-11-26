package com.model.demo.controller;

import com.alibaba.fastjson.JSONObject;
import com.model.demo.entity.JsonResult;
import com.model.demo.util.JwtUtil;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * desc：
 * author: CDN
 * date: 2019-11-26 15:44
 */
@RestController
@RequestMapping("/login")
public class LoginController {
    /**
     * 登录并获取token
     *
     * @param userName
     * @param passWord
     * @return
     */
    @PostMapping("/login")
    public Object login(String userName, String passWord, HttpServletResponse response) {
        // 检验用户是否存在(为了简单，这里假设用户存在，并制造一个uuid假设为用户id)
        String userId = UUID.randomUUID().toString();
        // 生成签名
        String token = JwtUtil.sign(userId);
        response.setHeader("token", token);
        Map<String, String> userInfo = new HashMap<>();
        userInfo.put("userId", userId);
        userInfo.put("userName", userName);
        userInfo.put("passWord", passWord);
        return JsonResult.buildSuccess(userInfo);
    }
}
