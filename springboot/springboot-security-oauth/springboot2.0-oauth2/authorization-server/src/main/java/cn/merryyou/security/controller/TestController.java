package cn.merryyou.security.controller;

import cn.merryyou.security.properties.OAuth2Properties;
import cn.merryyou.security.utils.JsonUtil;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.security.Principal;

/**
 * springbootTest 中有相关配置测试说明
 * 生成
 */
@RestController
@Slf4j
public class TestController {
    @Autowired
    private OAuth2Properties oAuth2Properties;

    @GetMapping("/userJwt")
    public Object getCurrentUserJwt(Authentication authentication, HttpServletRequest request) throws UnsupportedEncodingException {
        log.info("【SecurityOauth2Application】 getCurrentUserJwt authentication={}", JsonUtil.toJson(authentication));
        String header = request.getHeader("Authorization");
        String token = StringUtils.substringAfter(header, "bearer ");
        Claims claims = Jwts.parser().setSigningKey(oAuth2Properties.getJwtSigningKey().getBytes(StandardCharsets.UTF_8)).parseClaimsJws(token).getBody();
        String blog = (String) claims.get("blog");
        log.info("【SecurityOauth2Application】 getCurrentUser1 blog={}", blog);
        return authentication;
    }

    @GetMapping("/userRedis")
    public Object getCurrentUserRedis(Authentication authentication) {
        log.info("【SecurityOauth2Application】 getCurrentUserRedis authentication={}", JsonUtil.toJson(authentication));
        return authentication;
    }

    @GetMapping("/user/me")
    public Principal user(Principal user) {
        return user;
    }


    @GetMapping("authTest")
    @PreAuthorize("hasAuthority('user:view')")
    public String authTest() {

        return "权限注解测试测试--user:view";
    }

    @GetMapping("viewSelect")
//    @PreAuthorize("hasAuthority('view:select') || hasAuthority('user:view')")
    @PreAuthorize("hasAnyAuthority('view:select','user:view')")
    public String viewSelect() {

        return "权限注解测试测试--viewSelect";
    }
}
