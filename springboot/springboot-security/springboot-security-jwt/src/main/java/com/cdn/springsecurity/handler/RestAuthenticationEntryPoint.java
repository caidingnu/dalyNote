package com.cdn.springsecurity.handler;

/**
 * @author CDN
 * @desc
 * @date 2021/03/30 22:23
 */

import cn.hutool.json.JSONUtil;
import com.cdn.springsecurity.entity.common.Result;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 当未登录或者token失效访问接口时，自定义的返回结果
 * ☆☆☆☆☆☆☆   不能使用全局异常捕获，不然不会进入该回调  ☆☆☆☆☆☆☆☆☆☆
 */
@Component
public class RestAuthenticationEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException e) throws IOException, ServletException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        response.getWriter().println(JSONUtil.parse(Result.unauthorized(e.getMessage())));
        response.getWriter().flush();
    }
}

