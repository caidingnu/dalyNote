package com.cdn.configclient.entity;

public class Emp {
    private Integer empId;

    private String empName;

    private String emAge;

    private String sex;

    public Integer getEmpId() {
        return empId;
    }

    public void setEmpId(Integer empId) {
        this.empId = empId;
    }

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName == null ? null : empName.trim();
    }

    public String getEmAge() {
        return emAge;
    }

    public void setEmAge(String emAge) {
        this.emAge = emAge == null ? null : emAge.trim();
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex == null ? null : sex.trim();
    }
}