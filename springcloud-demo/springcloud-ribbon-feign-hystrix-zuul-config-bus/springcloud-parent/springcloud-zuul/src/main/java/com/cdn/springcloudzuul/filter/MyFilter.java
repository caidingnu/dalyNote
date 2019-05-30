package com.cdn.springcloudzuul.filter;

import com.alibaba.fastjson.JSONObject;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * 功能简述：
 *
 * @author caidingnu
 * @create 2019-05-01 23:38
 * @since 1.0.0
 */
public class MyFilter extends ZuulFilter {

    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() {
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();
        Object accessToken = request.getParameter("token");
        System.out.println(accessToken);
        if (accessToken == null) {
            System.out.println("token is empty");
            ctx.setSendZuulResponse(false);
            try {
                JSONObject object = new JSONObject();
                object.put("list", "token is empty");
                ctx.getResponse().getWriter().write(object.toJSONString());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
