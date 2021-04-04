package com.cdn.springsecurity.handler;

import com.alibaba.fastjson.JSON;
import com.cdn.springsecurity.utils.CommUtil;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/** 无权限处理类
 * ☆☆☆☆☆☆☆   不能使用全局异常捕获，不然不会进入该回调  ☆☆☆☆☆☆☆☆☆☆
 * CDN
 * 2020/05/19 00:50
 */
@Component
public class MyAccessDeniedHandler implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AccessDeniedException e) throws IOException, ServletException {
        Map<String,Object> map=new HashMap<>();
        map.put("code", HttpServletResponse.SC_UNAUTHORIZED);
        map.put("msg","无权限");
        String s = JSON.toJSONString(map);
        CommUtil. writeMsgToFront(httpServletResponse,s);
    }
}
