package com.github.jiangxch.hasakei.common.model;

/**
 * @author: jiangxch
 * @date: 2020/6/9 10:11
 */
public enum  ResultCode {
    /**
     * 成功
     */
    SUCCESS(0, "success", "成功"),
    /**
     * 系统错误
     */
    SYSTEM_ERROR(-2, "system error", "系统错误"),


    PARAM_MISSING_OR_ILLEGAL(100, "the parameter %s is missing or illegal", "缺少参数 或参数 不合法"),

    /**
     * ei不存在,或者ei未在灰度配置中
     */
    EI_NOT_EXIST(101, "this is is not exist,or ei not in gray", "ei不存在,或者ei未在灰度配置中"),
    SQL_UNIQUE_ERROR(102, "sql unique error", "sql索引插入不唯一"),
    SOCKET_MESSAGE_TYPE_NOT_SUPPORT(103, "socket receive message type not support", "socket收到的消息消息类型不支持"),
    ;
    /**
     * 错误码
     */
    private int errorCode;
    /**
     * 错误信息
     */
    private String errorMessage;
    /**
     * 错误描叙
     */
    private String description;

    ResultCode(int errorCode, String errorMessage, String description) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
        this.description = description;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public String getDescription() {
        return this.description;
    }
}
