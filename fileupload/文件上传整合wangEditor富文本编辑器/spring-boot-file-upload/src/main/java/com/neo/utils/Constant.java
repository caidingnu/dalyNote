package com.neo.utils;

import org.springframework.http.HttpStatus;

@SuppressWarnings("ALL")
public class Constant {
    /**
     * 暂无数据
     */
    public static final int CODE_EMPTY = 1001;
    public static final String CODE_EMPTY_MSG = "暂无数据";

    /**
     * OK
     */
    public static final int CODE_OK = HttpStatus.OK.value();
    public static final String CODE_OK_MSG = "ok";

    /**
     * 操作失败
     */
    public static final int CODE_DEFALT = 400;
    public static final String CODE_DEFALT_MSG = "操作失败";

    /**
     * 参数错误
     */
    public static final int CODE_CONDITION_ERROR = 1002;
    public static final String CODE_CONDITION_ERROR_MSG = "参数错误";

    /**
     * 系统异常
     */
    public static final int CODE_SYSTEM_ERROR = HttpStatus.INTERNAL_SERVER_ERROR.value();
    public static final String CODE_SYSTEM_ERROR_MSG = "系统异常";

    /**
     * 参数错误
     */
    public static final int CODE_CONDITION_USE = 1100;
    public static final String CODE_CONDITION_USE_MSG = "存在相同信息记录";


}


