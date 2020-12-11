package com.cdn.common.entity;

import lombok.Data;
import org.jxls.common.Context;
import org.jxls.util.JxlsHelper;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author CDN
 * @desc
 * @date 2020/12/10 15:09
 */
@Data
public class Employee {
    private String name;
    private Date birthDate;
    private int payment;
    private int bonus;


    public static void main(String[] args) throws IOException {
        List<Employee> employees = new ArrayList<>();
        for(int i=0;i<20;i++){
            Employee employee = new Employee();
            employee.setName("项目");
            employee.setBirthDate(new Date());
            employee.setPayment(i);
            employee.setBonus(i);
            employees.add(employee);
        }
        Base base = new Base();
        base.setName("项目--");
        base.setEmployeesList(employees);

        try(InputStream is = Employee.class.getClassLoader().getResourceAsStream("a.xlsx")) {
            try (OutputStream os = new FileOutputStream("target/object_collection_output.xls")) {
                Context context = new Context();
                context.putVar("employees", base);
                JxlsHelper.getInstance().processTemplate(is, os, context);
            }
        }
    }
}
