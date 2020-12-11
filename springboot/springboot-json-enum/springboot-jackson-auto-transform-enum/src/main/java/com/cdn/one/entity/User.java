package com.cdn.one.entity;

import com.cdn.one.customTransform.CustomeJackSon;
import com.cdn.one.constant.Level;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
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
    @JsonSerialize(using = CustomeJackSon.DateSerialize.class)
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
//    @JSONField(serializeUsing = LevelConverter.class)
    @JsonSerialize(using = CustomeJackSon.LevelSerialize.class)
//    @JsonDeserialize(using =CustomeJackSon.LevelDesSerialize.class )
    private Level level;


}
