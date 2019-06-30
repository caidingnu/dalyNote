package com.study.privoder.entity.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class ComAndEmp {
    private long companyId;
    private String companyName;
    private long empId;
    private String empName;
    private String empAge;
    private String sex;
}
