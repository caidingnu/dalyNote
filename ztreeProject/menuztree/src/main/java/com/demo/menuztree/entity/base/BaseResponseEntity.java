package com.demo.menuztree.entity.base;

import com.demo.menuztree.constant.Constant;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public class BaseResponseEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    @JsonProperty("StateInfo")
    private String stateInfo = "OK";
    @JsonProperty("StateCode")
    private int stateCode = 200;
    @JsonProperty("Data")
    private Object data = "";
    @JsonProperty("DataCount")
    private int dataCount = 0;

    public String getStateInfo() {
        return stateInfo;
    }

    public void setStateInfo(String stateInfo) {
        this.stateInfo = stateInfo;
    }

    public int getStateCode() {
        return stateCode;
    }

    public void setStateCode(int stateCode) {
        this.stateCode = stateCode;
        switch (stateCode) {
            case 200:
                this.stateInfo = "OK";
                break;
            case 404:
                this.stateInfo = "接口地址不正确";
                break;
            case 405:
                this.stateInfo = "请求方法不支持,request method not allowed";
                break;
            case 500:
                this.stateInfo = "服务内部错误";
                break;
            default:
                break;
        }
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public int getDataCount() {
        return dataCount;
    }

    public void setDataCount(int dataCount) {
        this.dataCount = dataCount;
    }

    @Override
    public String toString() {
        return "BaseResponseEntity [stateInfo=" + stateInfo + ", stateCode=" + stateCode
                + ", data=" + data + ", dataCount=" + dataCount + "]";
    }


    public BaseResponseEntity(int stateCode) {
        this.stateCode = stateCode;
        switch (stateCode) {
            case 404:
                this.stateInfo = "接口地址不正确";
                break;
            case 405:
                this.stateInfo = "请求方法不支持,request method not allowed";
                break;
            case 500:
                this.stateInfo = "服务内部错误";
                break;
            default:
                break;
        }
    }

    public BaseResponseEntity(String stateInfo, int stateCode, Object data, int dataCount) {
        super();
        this.stateInfo = stateInfo;
        this.stateCode = stateCode;
        this.data = data;
        this.dataCount = dataCount;
    }

    public BaseResponseEntity() {//暂无数据
        super();
        this.stateInfo = Constant.CODE_EMPTY_MSG;
        this.stateCode = Constant.CODE_EMPTY;
        this.data = "";
        this.dataCount = 0;
    }

    public BaseResponseEntity(boolean isSec) {
        super();
        this.stateInfo = isSec ? Constant.CODE_OK_MSG : Constant.CODE_SYSTEM_ERROR_MSG;
        this.stateCode = isSec ? Constant.CODE_OK : Constant.CODE_SYSTEM_ERROR;
        this.data = "";
        this.dataCount = 0;
    }

    public BaseResponseEntity(Object data) { //正确
        super();
        this.stateInfo = Constant.CODE_OK_MSG;
        this.stateCode = Constant.CODE_OK;
        this.data = data;
        this.dataCount = 0;
    }

    public BaseResponseEntity(Object Data, int DataCount) { //正确
        super();
        this.stateInfo = Constant.CODE_OK_MSG;
        this.stateCode = Constant.CODE_OK;
        this.data = Data;
        this.dataCount = DataCount;
    }

}
