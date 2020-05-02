package com.ex.batisdemo.domain;

import java.io.Serializable; /**
 * 返回基类
 *
 * @author itar
 * @email wuhandzy@gmail.com
 * @date 2020-02-26 04:42:14
 * @since jdk 1.8
 */
public class Result<T> implements Serializable {

    private static final long serialVersionUID = 2120267584344923858L;

    private Integer status = 0;

    private String message = null;

    private Object data = null;

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

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
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
