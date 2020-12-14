package com.cdn.one.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.cdn.one.constant.Level;
import com.cdn.one.customTransform.LevelConverter;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * desc：
 *
 * @author CDN
 * date 2020/09/10 19:25
 */
@Data
public class User {

    private Integer age;

    private String name;

    private int grade;

    private Double money;

    private double money2;

    private BigDecimal money3;

    private Boolean isAdult;

    private Date create;

    private Student student;

    /**
     * 通长情况下，前端传的也是code，数据库存的也是code，
     * * 一般不需要反序列化操作，根据实际情况而定
     *
     * @serializeUsing 序列化类
     * @deserializeUsing 反序列化类
     */
//    @JSONField(serializeUsing = LevelConverter.class,deserializeUsing = LevelConverter.class)
    @JSONField(serializeUsing = LevelConverter.class)
    private Level level;


}
