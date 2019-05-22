package com.tese.boot.entity.pojo;

import lombok.Data;

/**
 * 功能简述：
 *
 * @author CDN
 * @create 2019-05-20 17:32
 * @VERSION 1.0.0
 */
@Data
public class LoginReturn {
    private String userId;
    private String loginName;
    private String relName;
    private String menuId;
    private String menuName;
    private String menuPid;
    private String roleId;
    private String url;
    private String status;

}
