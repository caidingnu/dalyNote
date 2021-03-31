package com.cdn.springaoplog.Log;

import java.util.Date;

/**
 * desc：
 * author CDN
 * create 2019-11-10 00:15
 * version 1.0.0
 */
public class EntityLog {
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    /**
     * 错误信息
     */
    private String errorMessage;
    /**
     * 返回信息
     */
    private String responseValue;
    /**
     * 操作结束时间
     */
    private Date endTime;
    /**
     * 请求类型
     */
    private String type;
    /**
     * 日志标题
     */
    private String title;
    /**
     * 请求参数
     */
    private String params;
    /**
     * 请求地址
     */
    private String uri;
    /**
     * 操作浏览器
     */
    private String brower;
    /**
     * 操作开始时间
     */
    private Date startTime;
    /**
     * HTTP方法
     */
    private String httpMethod;
    /**
     * 软删除
     */
    private Integer isDeleted;
    /**
     * 请求方法
     */
    private String classMethod;
    /**
     * 执行时间
     */
    private Long executeTime;
    /**
     * 请求主机
     */
    private String host;
    /**
     * 请求头
     */
    private String header;
    /**
     * 操作系统
     */
    private String operatingSystem;
    /**
     * 错误码
     */
    private Integer errorCode;
    /**
     * 操作用户
     */
    private String username;


    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }


    public String getResponseValue() {
        return responseValue;
    }

    public void setResponseValue(String responseValue) {
        this.responseValue = responseValue;
    }


    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }


    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    public String getParams() {
        return params;
    }

    public void setParams(String params) {
        this.params = params;
    }


    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }


    public String getBrower() {
        return brower;
    }

    public void setBrower(String brower) {
        this.brower = brower;
    }


    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }


    public String getHttpMethod() {
        return httpMethod;
    }

    public void setHttpMethod(String httpMethod) {
        this.httpMethod = httpMethod;
    }


    public Integer getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Integer isDeleted) {
        this.isDeleted = isDeleted;
    }


    public String getClassMethod() {
        return classMethod;
    }

    public void setClassMethod(String classMethod) {
        this.classMethod = classMethod;
    }


    public Long getExecuteTime() {
        return executeTime;
    }

    public void setExecuteTime(Long executeTime) {
        this.executeTime = executeTime;
    }


    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }


    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }


    public String getOperatingSystem() {
        return operatingSystem;
    }

    public void setOperatingSystem(String operatingSystem) {
        this.operatingSystem = operatingSystem;
    }


    public Integer getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(Integer errorCode) {
        this.errorCode = errorCode;
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return "ErpLog{" +
                "id='" + id + '\'' +
                ", errorMessage='" + errorMessage + '\'' +
                ", responseValue='" + responseValue + '\'' +
                ", endTime=" + endTime +
                ", type='" + type + '\'' +
                ", title='" + title + '\'' +
                ", params='" + params + '\'' +
                ", uri='" + uri + '\'' +
                ", brower='" + brower + '\'' +
                ", startTime=" + startTime +
                ", httpMethod='" + httpMethod + '\'' +
                ", isDeleted=" + isDeleted +
                ", classMethod='" + classMethod + '\'' +
                ", executeTime=" + executeTime +
                ", host='" + host + '\'' +
                ", header='" + header + '\'' +
                ", operatingSystem='" + operatingSystem + '\'' +
                ", errorCode=" + errorCode +
                ", username='" + username + '\'' +
                '}';
    }
}
