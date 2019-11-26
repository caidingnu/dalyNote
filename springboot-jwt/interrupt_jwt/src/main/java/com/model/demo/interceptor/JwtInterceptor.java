package com.model.demo.interceptor;

import com.model.demo.exception.MyException;
import com.model.demo.util.JwtUtil;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class JwtInterceptor implements HandlerInterceptor {

    private static final String JWT_HEADER_NAME = "token";

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object object) {
        httpServletResponse.setHeader("Access-Control-Allow-Origin", "*"); //支持全域名访问，不安全，部署后需要固定限制为客户端网址
        httpServletResponse.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");//支持的http 动作
        httpServletResponse.setHeader("Access-Control-Max-Age", "3600");//用来指定本次预检(OPTIONS)请求的有效期，单位为秒,在此期间，不用发出另一条预检请求。
        httpServletResponse.addHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept," + JWT_HEADER_NAME);//Access-Control-Allow-Headers配置允许前端提交的请求头信息
        httpServletResponse.addHeader("Access-Control-Expose-Headers", "GID,SID," + JWT_HEADER_NAME);//Access-Control-Expose-Headers配置允许前端获取到的请求头信息
//       在跨域的情况下，发现每次客户端请求接口的时候，都会有一个 OPTIONS 预检请求，然后再发真正请求。
        if (httpServletRequest.getMethod().equals(RequestMethod.OPTIONS.name())) {
            return true;
        }

        // 从 http 请求头中取出 token
        String token = httpServletRequest.getHeader(JWT_HEADER_NAME);
        // 执行认证
        if (token == null) {
            throw new MyException("无token，请重新登录");
        }
        // 获取 token 中的 userId
        String userId = JwtUtil.getUserId(token);
        System.out.println("用户id:" + userId);

        // 验证 token
        JwtUtil.checkSign(token);
        updateToken(httpServletResponse, token);
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }

    private void updateToken(HttpServletResponse httpServletResponse, String jwt) {
        String userId = JwtUtil.getUserId(jwt);
        String newToken = JwtUtil.sign(userId);
        httpServletResponse.setHeader(JWT_HEADER_NAME, newToken);
    }
}
