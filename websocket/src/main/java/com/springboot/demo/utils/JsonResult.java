package com.springboot.demo.utils;


import java.io.Serializable;

/**
 * 功能描述：api统一返回数据模型
 *
 * <p> 创建时间：3 5, 2019 11:19:06 AM </p>
 */
public class JsonResult implements Serializable {

    /**
     * 状态码 1表示成功，0表示处理中，-1表示失败
     */
    private String code;
    /**
     * 返回数据
     */
    private Object data;
    /**
     * 返回消息
     */
    private String msg;

    public JsonResult() {
    }

    public JsonResult(String code, Object data, String msg) {
        this.code = code;
        this.data = data;
        this.msg = msg;
    }

    /**
     * 成功00/执行中-1/01-99 失败  返回[自定义消息]，[自定义状态码]，[自定义数据]
     * 如果msg为空，msg=“成功”，如果code为空，code=1
     * @return
     */
    public static JsonResult build(String msg, String code, Object data) throws MyException {
        if(msg==null||msg.trim().length()<1) throw  new MyException("消息为空");
        if(code==null) throw  new MyException("code为空");
        return new JsonResult(code, data, msg);
    }

    /**
     * 成功  返回【自定义数据】，msg=“成功”，code=00
     * @return
     */
    public static JsonResult buildSuccess(Object data) {
        return new JsonResult("00", data, "成功");
    }

    /**
     * 成功  返回【自定义消息】，data=null，code=1
     * @return
     */
    public static JsonResult buildSuccess(String msg) {
        return new JsonResult("00", null, msg);
    }

    /**
     * 成功  返回【自定义数据】，【自定义msg】，code=1
     * @return
     */
    public static JsonResult buildSuccess(Object data, String msg) throws MyException {
        if(msg==null||msg.trim().length()<1) throw  new MyException("消息为空");
        return new JsonResult("00", data, msg);
    }

    /**
     * 失败  返回【自定义数据】，msg=“失败”，code=98
     * @return
     */
    public static JsonResult buildError(Object data) {
        return new JsonResult("99", data, "失败");
    }

    /**
     * 失败  返回【自定义消息】，data=null，code=99
     * @return
     */
    public static JsonResult buildError(String msg) {
        return new JsonResult("99", null, msg);
    }

    /**
     * 失败  返回【自定义数据】，【自定义msg】，code=0
     * @return
     */
    public static JsonResult buildError(Object data, String msg) throws Exception {
        if(msg==null||msg.trim().length()<1) throw  new Exception("消息为空");
        return new JsonResult("99", data, msg);
    }

    /**
     * 业务失败  返回【自定义数据】，【自定义msg】，code=0
     * @return
     */
    public static JsonResult buildFail(String msg) {
        return new JsonResult("98", null, msg);
    }

    /**
     * 执行中  返回【自定义数据】，msg=“执行中...”，code=-1
     * @return
     */
    public static JsonResult buildWorking(Object data) {
        return new JsonResult("-1", data, "执行中...");
    }

    /**
     * 执行中  返回【自定义消息】，data=null，code=0
     * @return
     */
    public static JsonResult buildWorking(String msg) {
        return new JsonResult("-1", null, msg);
    }

    /**
     * 执行中  返回【自定义数据】，【自定义msg】，code=0
     * @return
     */
    public static JsonResult buildWorking(Object data, String msg) throws Exception {
        if(msg==null||msg.trim().length()<1) throw  new Exception("消息为空");
        return new JsonResult("-1", data, msg);
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    /**
     * 结果返回字符串
     * @return
     */
    @Override
    public String toString() {
        return "JsonResult [code=" + code + ", data=" + data + ", msg=" + msg + "]";
    }

}
