package com.ocr.mytest.aop;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;


/**
 * @author liuyanzhao
 */
public class Log implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * ID，自增
     */
    private Long id;


    /**
     * 方法操作名称
     */
    private String name;

    /**
     * 日志类型 0登陆日志 1操作日志
     */
    private String logType;

    /**
     * 请求路径
     */
    private String requestUrl;

    /**
     * 请求类型
     */
    private String requestType;

    /**
     * 请求参数
     */
    private String requestParam;

    /**
     * 请求用户
     */
    private String username;

    /**
     * ip
     */
    private String ip;

    /**
     * ip信息
     */
    private String ipInfo;

    /**
     * 操作系统
     */
    private String system;

    /**
     * 方法名称
     */
    private String requestMethodName;


    /**
     * 花费时间
     */
    private Integer costTime;

    /**
     * 删除状态：1删除，0未删除
     */
    private Integer delFlag = 0;

    /**
     * 创建人用户名
     */
    private String createBy;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
    private String requestTime;
    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新人
     */
    private String updateBy;

    /**
     * 更新时间
     */
    private Date updateTime;

    public String getRequestTime() {
        return requestTime;
    }

    public void setRequestTime(String requestTime) {
        this.requestTime = requestTime;
    }

    public String getRequestMethodName() {
        return requestMethodName;
    }

    public void setRequestMethodName(String requestMethodName) {
        this.requestMethodName = requestMethodName;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLogType() {
        return logType;
    }

    public String getSystem() {
        return system;
    }

    public void setSystem(String system) {
        this.system = system;
    }

    public void setLogType(String logType) {
        this.logType = logType;
    }

    public String getRequestUrl() {
        return requestUrl;
    }

    public void setRequestUrl(String requestUrl) {
        this.requestUrl = requestUrl;
    }

    public String getRequestType() {
        return requestType;
    }

    public void setRequestType(String requestType) {
        this.requestType = requestType;
    }

    public String getRequestParam() {
        return requestParam;
    }

    public void setRequestParam(String requestParam) {
        this.requestParam = requestParam;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getIpInfo() {
        return ipInfo;
    }

    public void setIpInfo(String ipInfo) {
        this.ipInfo = ipInfo;
    }

    public Integer getCostTime() {
        return costTime;
    }

    public void setCostTime(Integer costTime) {
        this.costTime = costTime;
    }

    public Integer getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(Integer delFlag) {
        this.delFlag = delFlag;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        return "Log{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", logType='" + logType + '\'' +
                ", requestUrl='" + requestUrl + '\'' +
                ", requestType='" + requestType + '\'' +
                ", requestParam='" + requestParam + '\'' +
                ", username='" + username + '\'' +
                ", ip='" + ip + '\'' +
                ", ipInfo='" + ipInfo + '\'' +
                ", system='" + system + '\'' +
                ", requestMethodName='" + requestMethodName + '\'' +
                ", costTime=" + costTime +
                ", delFlag=" + delFlag +
                ", createBy='" + createBy + '\'' +
                ", requestTime='" + requestTime + '\'' +
                ", createTime=" + createTime +
                ", updateBy='" + updateBy + '\'' +
                ", updateTime=" + updateTime +
                '}';
    }
}
