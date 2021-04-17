package com.cdn.springsecurity.handler;

/**
 * @author CDN
 * @desc
 * @date 2021/04/05 01:58
 */

import com.alibaba.fastjson.JSON;
import com.cdn.springsecurity.common.ResultCode;
import com.cdn.springsecurity.utils.CommUtil;
import org.springframework.security.web.session.SessionInformationExpiredEvent;
import org.springframework.security.web.session.SessionInformationExpiredStrategy;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @Description: 会话信息过期策略
 * @Date Create in 2019/9/4 9:34
 */
@Component
public class CustomizeSessionInformationExpiredStrategy implements SessionInformationExpiredStrategy {
    @Override
    public void onExpiredSessionDetected(SessionInformationExpiredEvent sessionInformationExpiredEvent) throws IOException, ServletException {
        HttpServletResponse httpServletResponse = sessionInformationExpiredEvent.getResponse();
        httpServletResponse.setContentType("text/json;charset=utf-8");
        Map<String,Object> map=new HashMap<>();
        map.put("code", HttpServletResponse.SC_FORBIDDEN);
        map.put("msg","被挤掉线");
        String s = JSON.toJSONString(map);
        CommUtil. writeMsgToFront(httpServletResponse,s);
    }
}
