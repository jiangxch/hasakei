package com.github.jiangxch.hasakei.config.controller;

import com.github.jiangxch.hasakei.common.exceptions.HasakeiException;
import com.github.jiangxch.hasakei.common.model.Result;
import com.github.jiangxch.hasakei.common.model.ResultCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolationException;
import java.sql.SQLIntegrityConstraintViolationException;

@ControllerAdvice
@Slf4j
public class GlobalExceptionController {

    // Spring MVC 参数不正确
    @ResponseBody
    @ExceptionHandler(value = MissingServletRequestParameterException.class)
    public Result missingServletRequestParameterExceptionHandler(HttpServletRequest req, MissingServletRequestParameterException ex) {
        return Result.newError(ResultCode.PARAM_MISSING_OR_ILLEGAL, ResultCode.PARAM_MISSING_OR_ILLEGAL.getErrorMessage() + ":" + ex.getMessage());
    }

    // jsr303 参数校验异常
    @ResponseBody
    @ExceptionHandler(value = ConstraintViolationException.class)
    public Result constraintViolationExceptionHandler(HttpServletRequest req, ConstraintViolationException ex) {
        // 拼接详细报错
        StringBuilder detailMessage = new StringBuilder();
        ex.getConstraintViolations().forEach(constraintViolation -> detailMessage.append(",").append(constraintViolation.getMessage()));
        return Result.newError(ResultCode.PARAM_MISSING_OR_ILLEGAL,
                ResultCode.PARAM_MISSING_OR_ILLEGAL.getErrorMessage() + ":" + detailMessage.toString());
    }


    // 数据库插入数据唯一约束异常 //TODO 该handler无法生效,不知道为什么
    @ResponseBody
    @ExceptionHandler(value = SQLIntegrityConstraintViolationException.class)
    public Result SQLIntegrityConstraintViolationExceptionHandler(HttpServletRequest req, ConstraintViolationException ex) {
        return Result.newError(ResultCode.SQL_UNIQUE_ERROR, ex.getMessage());
    }

    //业务异常
    @ResponseBody
    @ExceptionHandler(value = HasakeiException.class)
    public Result exceptionHandler(HttpServletRequest req, HasakeiException e) {
        return Result.newError(e.getErrorCode(),e.getErrMsg());
    }

    // 其他异常
    @ResponseBody
    @ExceptionHandler(value = Exception.class)
    public Result exceptionHandler(HttpServletRequest req, Exception e) {
        return Result.newError(ResultCode.SYSTEM_ERROR, ResultCode.SYSTEM_ERROR.getErrorMessage() + ":" + e.getMessage());
    }

}