package com.cdn.common.entity;

import lombok.Data;

import java.util.List;

/**
 * @author CDN
 * @desc
 * @date 2020/12/06 14:14
 */
@Data
public class Base {

    private String name;

    List<Employee> employeesList;
}
