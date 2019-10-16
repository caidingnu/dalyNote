package com.springboot.demo.utils;

public class MyException extends RuntimeException {

    /**
     * 状态码
     * 99 系统异常
     * 98 业务提示
     * 96 未登录或已超时
     * 00 正常
     */
    private String code;
    /**
     * 异常消息
     */
    private String msg;

    public MyException(String msg, String code){
        super(msg);
        this.code = code;
        this.msg = msg;
    }

    public MyException(String msg){
        super(msg);
        this.code = "98";
        this.msg = msg;
    }

    public MyException(String message, Throwable cause){
        super(message, cause);
        this.code = "98";
        this.msg = message;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

}
