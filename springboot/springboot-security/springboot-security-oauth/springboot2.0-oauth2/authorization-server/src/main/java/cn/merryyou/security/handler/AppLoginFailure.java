package cn.merryyou.security.handler;

import cn.merryyou.security.utils.CommUtil;
import com.alibaba.fastjson.JSON;
import org.springframework.security.authentication.*;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * desc：
 *
 * @author CDN
 * date 2020/05/21 16:28
 */
@Component
public class AppLoginFailure implements AuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException exception) throws IOException, ServletException {
        Map<String, Object> map = new HashMap<>();
        map.put("code", HttpServletResponse.SC_BAD_REQUEST);
        if (exception instanceof UsernameNotFoundException || exception instanceof BadCredentialsException) {
            map.put("msg", "用户名或密码错误");
        } else if (exception instanceof DisabledException) {
            map.put("msg", "用户已被禁用");
        } else if (exception instanceof LockedException) {
            map.put("msg", "账户被锁定");
        } else if (exception instanceof AccountExpiredException) {
            map.put("msg", "账户过期");
        } else if (exception instanceof CredentialsExpiredException) {
            map.put("msg", "证书过期");
        } else {
            map.put("msg","登录失败");
        }
        String  param= JSON.toJSONString(map);
        System.out.println("login Failure -------------");
        System.out.println(map);
        CommUtil. writeMsgToFront(httpServletResponse,param);
    }
}
