package com.cdn.one.constant;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.springframework.boot.jackson.JsonComponent;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * desc：
 *
 * @author CDN
 * date 2020/09/10 21:08
 */
public enum Level {

    ONE("a", "优"),
    TWO("b", "良"),
    THREE("c", "中"),
    FOUR("d", "差");

    private String code;
    private String value;

    Level(String code, String value) {
        this.code = code;
        this.value = value;
    }

    public String getCode() {
        return code;
    }

    public String getValue() {
        return value;
    }

    /**
     * desc:
     * param:
     * return:
     * author: CDN
     * date: 2020/9/10
     */
    public static String getValueByCode(String code) {
        for (Level level : values()) {
            if (level.code.equals(code)){
                return level.value;
            }
        }
        return "未知";
    }

}


