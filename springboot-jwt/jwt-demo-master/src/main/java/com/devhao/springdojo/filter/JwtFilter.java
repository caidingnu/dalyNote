package com.devhao.springdojo.filter;

import com.devhao.springdojo.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebFilter(urlPatterns = "/*")
public class JwtFilter implements Filter {
    private static final String JWT_HEADER_NAME = "Authorization";
    private JwtUtil jwtUtil;


    //    存放不直接放行的请求
    private static final List<String> WHITE_LIST = new ArrayList<String>() {{
        add("/registration");
        add("/");
    }};

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        httpServletResponse.setHeader("Access-Control-Allow-Origin", "*"); //支持全域名访问，不安全，部署后需要固定限制为客户端网址
        httpServletResponse.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");//支持的http 动作
        httpServletResponse.setHeader("Access-Control-Max-Age", "3600");//用来指定本次预检(OPTIONS)请求的有效期，单位为秒,在此期间，不用发出另一条预检请求。
        httpServletResponse.addHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept," + JWT_HEADER_NAME);//Access-Control-Allow-Headers配置允许前端提交的请求头信息
        httpServletResponse.addHeader("Access-Control-Expose-Headers", "GID,SID," + JWT_HEADER_NAME);//Access-Control-Expose-Headers配置允许前端获取到的请求头信息

//       在跨域的情况下，发现每次客户端请求接口的时候，都会有一个 OPTIONS 预检请求，然后再发真正请求。
        if (httpServletRequest.getMethod().equals(RequestMethod.OPTIONS.name())) {
            chain.doFilter(httpServletRequest, httpServletResponse);
            return;
        }
        System.out.println(WHITE_LIST.contains(httpServletRequest.getRequestURI()));
        System.out.println(httpServletRequest.getRequestURI());


        String myPath = httpServletRequest.getRequestURI();
        String jwt = httpServletRequest.getHeader(JWT_HEADER_NAME);
        if (WHITE_LIST.contains(myPath) || checkPath(myPath)) {
            chain.doFilter(request, response);
        } else if (isTokenValid(jwt)) {
            System.out.println("token验证成功");
            updateToken(httpServletResponse, jwt);
            chain.doFilter(request, response);
        } else {
            httpServletResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED);
        }
    }

    private void updateToken(HttpServletResponse httpServletResponse, String jwt) {
        String payload = jwtUtil.parseToken(jwt);
        String newToken = jwtUtil.generateToken(payload);
        httpServletResponse.setHeader(JWT_HEADER_NAME, newToken);
    }

    private boolean isTokenValid(String token) {
        return jwtUtil.isTokenValid(token);
    }


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("我是innit");
    }

    @Override
    public void destroy() {
        System.out.println("销毁方法");
    }

    @Autowired
    public void setJwtUtil(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    /**
     * @Description: 静态资源放行
     * @Param:
     * @return:
     * @Author: cdn
     * @Date: 2019/5/11
     */
    private boolean checkPath(String myPath) {
        return myPath.startsWith("/js") || myPath.startsWith("/css") || myPath.startsWith("/img") || myPath.startsWith("/favicon.ico") || myPath.startsWith("/fonts") || myPath.startsWith("/static");
    }

}
