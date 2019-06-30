package com.study.privoder.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Emp {

    private long empId;
    private String empName;
    private String empAge;
    private String sex;


}
