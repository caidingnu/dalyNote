package com.demo.menuztree.utils;

import java.util.UUID;

/**
 * 生成UUID
 *
 * @author Hades
 */
public class UuidUtil {
    public static String getUUID() {
        String s = UUID.randomUUID().toString().replaceAll("-", "").substring(0, 32);
        return s;
    }

    public static void main(String[] args) {
        System.out.println(getUUID());
    }
}
