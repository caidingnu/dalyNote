package com.study.shiro.shiroconfig;


import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;

/**
 * Shiro工具类
 */
public class ShiroUtil {

    /**
     * desc:
     * param:
     * return:
     * author: CDN
     * date: 2019/11/30
     */
    public static String encrypt(String pwd,String salt) {
        String hashAlgorithmName = "MD5";
        ByteSource salt2 = ByteSource.Util.bytes(salt);//盐值
        int hashIterations = 1024;  //加密次数
        Object result = new SimpleHash(hashAlgorithmName, pwd, salt2, hashIterations);
        System.out.println(result);
        return result.toString();
    }

}
