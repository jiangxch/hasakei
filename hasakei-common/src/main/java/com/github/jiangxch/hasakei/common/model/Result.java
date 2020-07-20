package com.github.jiangxch.hasakei.common.model;

import lombok.Data;

import java.io.Serializable;

public class Result<T> implements Serializable {
    private static final long serialVersionUID = 1535964835070227503L;
    private int errorCode;
    private String errorMessage;
    private T data;

    //保证Hessian 序列化成功
    public Result() {
    }

    private Result(int errorCode, String errorMessage) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }
    
    private Result(int errorCode, String errorMessage, T data){
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
        this.data = data;
    }

    public static <R> Result<R> newError(int errCode, String errMsg) {
        return new Result<R>(errCode, errMsg);
    }
    
    public static <R> Result<R> newError(int errCode, String errMsg, R data){
        return new Result<>(errCode, errMsg, data);
    }

    public static <R> Result<R> newError(ResultCode resultCode){
        return new Result<>(resultCode.getErrorCode(),resultCode.getErrorMessage());
    }

    public static <R> Result<R> newError(ResultCode resultCode, String errMsg){
        return new Result<>(resultCode.getErrorCode(), errMsg);
    }

    public static <R> Result<R> newSuccess(R data){
        return new Result<>(0, "success", data);
    }

    public static <R> Result<R> newSuccess(){
        return newSuccess(null);
    }

    public boolean hasSuccess() {
        return errorCode == 0;
    }

    public T getData() {
        return this.data;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    @Override
    public String toString() {
        return "Result{" +
                "errorCode=" + errorCode +
                ", errorMessage='" + errorMessage + '\'' +
                ", data=" + data +
                '}';
    }
}
