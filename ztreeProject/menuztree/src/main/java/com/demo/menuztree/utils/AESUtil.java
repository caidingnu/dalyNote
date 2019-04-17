package com.demo.menuztree.utils;


import org.apache.tomcat.util.codec.binary.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.security.Key;


public class AESUtil {
    //密钥算法
    public static final String KEY_ALGORITHM = "AES";
    public static final String key = "GNR2a5x5qxWIi3/E9EvJeQ==";

    //加解密算法/工作模式/填充方式,Java6.0支持PKCS5Padding填充方式,BouncyCastle支持PKCS7Padding填充方式
    public static final String CIPHER_ALGORITHM = "AES/ECB/PKCS5Padding";


    /**
     * 转换密钥
     */
    public static Key toKey(byte[] key) throws Exception {
        return new SecretKeySpec(key, KEY_ALGORITHM);
    }


    /**
     * 加密数据
     *
     * @param data 待加密数据
     * @return 加密后的数据
     */
    public static String encrypt(String data) throws Exception {
        Key k = toKey(Base64.decodeBase64(key));                           //还原密钥
        //使用PKCS7Padding填充方式,这里就得这么写了(即调用BouncyCastle组件实现)
        //Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM, "BC");
        Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);              //实例化Cipher对象，它用于完成实际的加密操作
        cipher.init(Cipher.ENCRYPT_MODE, k);                               //初始化Cipher对象，设置为加密模式
        return Base64.encodeBase64String(cipher.doFinal(data.getBytes())); //执行加密操作。加密后的结果通常都会用Base64编码进行传输
    }


    /**
     * 解密数据
     *
     * @param data 待解密数据
     * @return 解密后的数据
     */
    public static String decrypt(String data) throws Exception {
        Key k = toKey(Base64.decodeBase64(key));
        Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE, k);                          //初始化Cipher对象，设置为解密模式
        return new String(cipher.doFinal(Base64.decodeBase64(data))); //执行解密操作
    }


    public static void main(String[] args) throws Exception {
        String source = "123456789";
        System.out.println("原文：" + source);

        String encryptData = encrypt(source);
        System.out.println("加密：" + encryptData);

        String decryptData = decrypt("xCyF15jwOQ5mdZ6SJh6mHg==");
        System.out.println("解密: " + decryptData);
    }
}