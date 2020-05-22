package com.cdn.springsecurity.utils;

import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * CDN
 * 2020/05/18 00:15
 * <p>
 * 自定义密码加密器    对应 com.cdn.springsecurity.config.WebSecurityConfig  中的passwordEncoder方法
 */
public class Md5En implements PasswordEncoder {

    @Override
    public String encode(CharSequence charSequence) {
        return MD5Util.getMD5((String) charSequence);
    }

    @Override
    public boolean matches(CharSequence charSequence, String s) {


        return s.equals(MD5Util.getMD5((String) charSequence));
    }

    public static void main(String[] args) {
        System.out.println(new Md5En().encode("123"));
    }
}
