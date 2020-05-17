package com.study.shiro.entity;


public class Permission {

    private Integer id; // 编号

    private String permissionName; // 菜单名称

    private String remarks; // 备注

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPermissionName() {
        return permissionName;
    }

    public void setPermissionName(String permissionName) {
        this.permissionName = permissionName;
    }

    public Permission() {
        super();
    }

}
