package org.sidecar;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * desc：zuul网关过滤器
 *
 * @author CDN
 * date 2020/03/08 21:42
 */
@Component
public class ZuulFiltrator extends ZuulFilter {

    @Value("${server.port}")
    String port;

    /**
     * 类型包括  pre post route error
     * pre 代表路由代理之前执行
     * route 代表代理的时候执行
     * error 代表出现错误的时候执行
     * post  代表在route 或者 error执行完成之后执行
     * <p>
     * pre：可以在请求被路由之前调用
     * route：在路由请求时候被调用
     * post：在route和error过滤器之后被调用
     * error：处理请求时发生错误时被调用
     *
     * @return
     */
    @Override
    public String filterType() {
        return FilterConstants.PRE_TYPE;
    }

    /**
     * 执行顺序
     * 越小的约先执行
     *
     * @return
     */
    @Override
    public int filterOrder() {
        return 1;
    }

    /**
     * 是否使用该过滤器
     *
     * @return
     */
    @Override
    public boolean shouldFilter() {
        return true;
    }

    /**
     * 过滤器要执行的逻辑代码
     *
     * @return
     */
    @Override
    @ResponseBody
    public Object run() {
        System.out.println(port + ":Zuul过滤器执行了！");
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();
        Object accessToken = request.getParameter("token");
        if (accessToken == null) {
            ctx.setSendZuulResponse(true);
            ctx.setResponseStatusCode(401);
            ctx.setResponseBody("token is empty");
        }
        return null;
    }
}
