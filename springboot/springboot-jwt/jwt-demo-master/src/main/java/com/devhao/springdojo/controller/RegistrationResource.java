package com.devhao.springdojo.controller;

import com.devhao.springdojo.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

@RestController
public class RegistrationResource {
    private final static String JWT_HEADER_NAME = "Authorization";

    private JwtUtil jwtUtil;

    @RequestMapping("/registration")
    public String register(@RequestParam String username, HttpServletResponse response) {
        String jwt = jwtUtil.generateToken(username);
        response.setHeader(JWT_HEADER_NAME, jwt);
        System.out.println(response.getHeader(JWT_HEADER_NAME));
        System.out.println(String.format("JWT for %s :\n%s", username, jwt));
//        return String.format("JWT for %s :\n%s", username, jwt);
        return jwt;
    }

    @Autowired
    public void setJwtUtil(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }
}
