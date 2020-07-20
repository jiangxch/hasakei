package com.github.jiangxch.hasakei.common.exceptions;

import com.github.jiangxch.hasakei.common.model.ResultCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @author: jiangxch
 * @date: 2020/7/20 下午9:09
 */
@Getter
public class HasakeiException extends RuntimeException{
    private ResultCode resultCode;
    public HasakeiException(ResultCode resultCode) {
        super(resultCode.getErrorMessage());
        this.resultCode = resultCode;
    }
}
