package com.oceam.entity;

import java.io.Serializable; /**
 * 返回基类
 *
 * @author itar
 * @email wuhandzy@gmail.com
 * @date 2018-12-19 14:23:50
 * @since jdk 1.8
 */
public class Result<T> implements Serializable {

    private static final long serialVersionUID = 2120267584344923858L;

    private Integer status = 0;

    private String message = null;

    private T data = null;

    public Result() {

    }

    public Result(Integer status, String message, T data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "Result{" +
                "status=" + status +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }
}
