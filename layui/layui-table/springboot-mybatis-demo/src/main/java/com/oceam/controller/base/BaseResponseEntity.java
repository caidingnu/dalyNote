package com.oceam.controller.base;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.oceam.controller.constant.Constant;

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
            case 1001:
                this.stateInfo = "暂无数据";
                break;
            case 1002:
                this.stateInfo = "参数错误";
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

    public BaseResponseEntity(Object data) { //正确
        super();
        this.stateInfo = Constant.CODE_OK_MSG;
        this.stateCode = Constant.CODE_OK;
        this.data = data;
        this.dataCount = 1;
    }

    public BaseResponseEntity(Object Data, int DataCount) { //正确
        super();
        this.stateInfo = Constant.CODE_OK_MSG;
        this.stateCode = Constant.CODE_OK;
        this.data = Data;
        this.dataCount = DataCount;
    }

}
